package com.remair.framework.base;

import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.remair.framework.R;
import com.remair.framework.mvp.IPresenter;
import com.remair.util.ActivityUtils;
import com.remair.util.StatusBarUtil;
import icepick.Icepick;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import java.util.concurrent.TimeUnit;
import org.simple.eventbus.EventBus;

/**
 * 项目名称：heixiu
 * 类描述：
 * 创建人：LiuJun
 * 创建时间：2017/3/21 15:54
 * 修改人：LiuJun
 * 修改时间：2017/3/21 15:54
 * 修改备注：
 */
public abstract class BaseMVPDialogFragment<P extends IPresenter> extends DialogFragment {
    protected View mRootView;
    protected final String TAG = this.getClass().getSimpleName();
    protected P mPresenter;
    private Unbinder mUnbinder;
    private CompositeDisposable mCompositeDisposable;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = getPresenter();
        Icepick.restoreInstanceState(this, savedInstanceState);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = initView(inflater, container);
        mUnbinder = ButterKnife.bind(this, mRootView);
        return mRootView;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (useEventBus()) {
            EventBus.getDefault().register(this);
        }
        initData();
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Icepick.saveInstanceState(this, outState);
    }


    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if (mPresenter == null) {
            mPresenter = getPresenter();
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (useEventBus()) {
            EventBus.getDefault().unregister(this);
        }
        dispose();
        if (mUnbinder != null && mUnbinder != Unbinder.EMPTY) {
            mUnbinder.unbind();
            mUnbinder = null;
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.onDestroy();//释放资源
        }
        this.mPresenter = null;
        this.mRootView = null;
    }


    protected void dispose() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable.dispose();
            mCompositeDisposable = null;
        }
    }


    protected void addDisposable(Disposable s) {
        if (s == null) {
            return;
        }
        if (this.mCompositeDisposable == null) {
            this.mCompositeDisposable = new CompositeDisposable();
        }
        this.mCompositeDisposable.add(s);
    }


    /**
     * 几秒钟后自动关闭
     *
     * @param seconds 延迟时间，单位：秒
     */
    public void autoClose(int seconds) {
        addDisposable(Observable.timer(seconds, TimeUnit.SECONDS)
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(new Consumer<Long>() {
                                    @Override
                                    public void accept(Long aLong) throws Exception {
                                        dismissAllowingStateLoss();
                                    }
                                }));
    }


    /**
     * 沉浸式状态栏，需要的话在initData中调用
     * 适用于图片作为背景的界面,此时需要图片填充到状态栏
     */
    protected void initImgStatusBar() {
        Window window = getDialog().getWindow();
        if (window == null) {
            return;
        }
        if (!ActivityUtils.isFullScreen(window) && getDialog() != null &&
                getDialog().getWindow() != null) {
            StatusBarUtil.setStatusBarFromImg(getDialog().getWindow(), ContextCompat
                    .getColor(getActivity(), android.R.color.transparent));
        }
    }


    /**
     * 沉浸式状态栏，需要的话在initData中调用
     */
    protected void initStatusBar() {
        Window window = getDialog().getWindow();
        if (window == null) {
            return;
        }
        if (!ActivityUtils.isFullScreen(window) && getDialog() != null &&
                getDialog().getWindow() != null) {
            StatusBarUtil.setColor(this, getDialog().getWindow(), ContextCompat
                    .getColor(getActivity(), R.color.colorPrimary), 0);
        }
    }


    /**
     * 沉浸式状态栏，需要的话在initData中调用
     */
    protected void initStatusBar(@ColorRes int color) {
        Window window = getDialog().getWindow();
        if (window == null) {
            return;
        }
        if (!ActivityUtils.isFullScreen(window) && getDialog() != null &&
                getDialog().getWindow() != null) {
            StatusBarUtil.setColor(this, getDialog().getWindow(), ContextCompat
                    .getColor(getActivity(), color), 0);
        }
    }


    /**
     * 是否使用eventBus,默认为不使用(false)，
     */
    protected boolean useEventBus() {
        return false;
    }


    protected abstract View initView(LayoutInflater inflater, ViewGroup container);

    protected abstract void initData();

    protected abstract P getPresenter();
}
