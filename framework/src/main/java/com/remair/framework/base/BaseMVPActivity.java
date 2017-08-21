package com.remair.framework.base;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.remair.framework.R;
import com.remair.framework.analytics.AnalyticsAgent;
import com.remair.framework.mvp.IPresenter;
import com.remair.util.ActivityUtils;
import com.remair.util.StatusBarUtil;
import icepick.Icepick;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subscriber;

/**
 * 项目名称：heixiu
 * 类描述：
 * 创建人：LiuJun
 * 创建时间：2017/2/28 15:03
 * 修改人：LiuJun
 * 修改时间：2017/2/28 15:03
 * 修改备注：
 */
public abstract class BaseMVPActivity<P extends IPresenter> extends AppCompatActivity {

    public static final String ACTIVITY_LOGOUT = "all_activity_logout";

    protected Unbinder mUnbinder;

    protected P mPresenter;

    private CompositeDisposable mCompositeDisposable;

    protected BaseMVPActivity mActivity;


    public BaseMVPActivity() {
        mActivity = this;
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //使用Icepick自动恢复 @State 注解的数据
        Icepick.restoreInstanceState(this, savedInstanceState);
        mPresenter = getPresenter();
        EventBus.getDefault().register(this);//注册到事件主线
        setContentView(getLayoutResId());
        mUnbinder = ButterKnife.bind(this);
        initData(savedInstanceState);
        initStatusBar();
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Icepick.saveInstanceState(this, outState);
    }


    @Override
    protected void onResume() {
        super.onResume();
        AnalyticsAgent.onResume(this);
    }


    @Override
    protected void onPause() {
        super.onPause();
        AnalyticsAgent.onPause(this);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        dispose();
        if (mPresenter != null) {
            mPresenter.onDestroy();//释放资源
            mPresenter = null;
        }
        if (mUnbinder != null && mUnbinder != Unbinder.EMPTY) {
            mUnbinder.unbind();
            mUnbinder = null;
        }
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


    @Subscriber(tag = ACTIVITY_LOGOUT)
    protected void logout(String b) {
        finish();
    }


    protected void initStatusBar() {
        if (!ActivityUtils.isFullScreen(this)) {
            StatusBarUtil.setColor(this, ContextCompat.getColor(this, R.color.colorPrimary), 0);
        }
    }


    protected void initStatusBar(View v) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT ||
                ActivityUtils.isFullScreen(this)) {
            return;
        }
        StatusBarUtil.setStatusBarFromImg(this, ContextCompat
                .getColor(this, android.R.color.transparent));
        StatusBarUtil.setViewAddStatusPadding(v, getResources()
                .getDimensionPixelSize(R.dimen.titlebar_height));
    }


    @LayoutRes
    protected abstract int getLayoutResId();

    protected abstract void initData(Bundle savedInstanceState);

    protected abstract P getPresenter();
}
