package com.remair.framework.mvp;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import org.simple.eventbus.EventBus;

/**
 * 项目名称：heixiu
 * 类描述：
 * 创建人：LiuJun
 * 创建时间：16/4/28 16:36
 * 修改人：LiuJun
 * 修改时间：16/4/28 16:36
 * 修改备注：
 */
public class BasePresenter implements IPresenter {

    protected CompositeDisposable mCompositeDisposable;


    public BasePresenter() {
        onStart();
    }


    public void onStart() {
        if (useEventBus()) {
            EventBus.getDefault().register(this);//注册eventbus
        }
    }


    @Override
    public void onDestroy() {
        if (useEventBus()) {
            EventBus.getDefault().unregister(this);//解除注册eventbus
        }
        dispose();//解除订阅
    }


    /**
     * 是否使用eventBus,默认为不使用(false)，
     */
    protected boolean useEventBus() {
        return false;
    }


    protected void addDisposable(Disposable disposable) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(disposable);//将所有subscription放入,集中处理
    }


    protected void dispose() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable.dispose();//保证activity结束时取消所有正在执行的订阅
            mCompositeDisposable = null;
        }
    }
}
