package com.remair.framework.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.remair.framework.mvp.IPresenter;
import icepick.Icepick;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
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
public abstract class BaseMVPFragment<P extends IPresenter> extends Fragment {
    protected View mRootView;
    protected final String TAG = this.getClass().getSimpleName();
    protected P mPresenter;
    private Unbinder mUnbinder;
    protected boolean mUserVisible;//Fragment是否能被用户看见
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
        mUserVisible = false;
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
            mPresenter = null;
        }
        this.mRootView = null;
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        mUserVisible = isVisibleToUser;
    }


    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        mUserVisible = !hidden;
    }


    @Override
    public void onResume() {
        super.onResume();
        mUserVisible = checkVisible();
    }


    @Override
    public void onPause() {
        super.onPause();
        mUserVisible = false;
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
     * 是否使用eventBus,默认为不使用(false)，
     */
    protected boolean useEventBus() {
        return false;
    }


    protected abstract View initView(LayoutInflater inflater, ViewGroup container);

    protected abstract void initData();

    protected abstract P getPresenter();


    /**
     * 在{@link #onResume()}中调用，判断当前Fragment是否显示。
     * 主要是Fragment在ViewPager中的时候，判断ViewPager当前显示的Fragment是不是自己
     * 如果没有在ViewPager中使用，默认为显示
     *
     * @return true 显示，false 不显示
     */
    protected boolean checkVisible() {
        return true;
    }
}
