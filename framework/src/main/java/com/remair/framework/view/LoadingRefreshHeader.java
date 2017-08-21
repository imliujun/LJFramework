package com.remair.framework.view;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.remair.framework.R;
import com.remair.framework.R2;
import com.remair.util.UiUtil;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrUIHandler;
import in.srain.cube.views.ptr.indicator.PtrIndicator;

/**
 * 项目名称：heixiu
 * 类描述：菊花加载
 * 创建人：JinWei
 * 创建时间：2016/12/30
 * 修改人：
 * 修改时间：2016/12/30
 * 修改备注：
 */

public class LoadingRefreshHeader extends FrameLayout implements PtrUIHandler {
    @BindView(R2.id.img_loading) ImageView mImgLoading;

    private static final int mLoadingTime = 1500;

    private Animator mLoadingAnimator;


    @Override
    public void onUIReset(PtrFrameLayout frame) {
    }


    @Override
    public void onUIRefreshPrepare(PtrFrameLayout frame) {
        if (mLoadingAnimator != null) {
            mLoadingAnimator.start();
        }
    }


    @Override
    public void onUIRefreshBegin(PtrFrameLayout frame) {
    }


    @Override
    public void onUIRefreshComplete(PtrFrameLayout frame) {
        if (mLoadingAnimator != null) {
            mLoadingAnimator.cancel();
        }
    }


    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (mLoadingAnimator != null) {
            mLoadingAnimator.cancel();
        }
    }


    @Override
    public void onUIPositionChange(PtrFrameLayout frame, boolean isUnderTouch, byte status, PtrIndicator ptrIndicator) {
    }


    public LoadingRefreshHeader(Context context) {
        this(context, null);
    }


    public LoadingRefreshHeader(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }


    public LoadingRefreshHeader(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }


    private void initView() {
        inflate(getContext(), R.layout.header_loading_refresh, this);
        ButterKnife.bind(this);
        int height = UiUtil.dip2px(getContext(), 60);
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        if (layoutParams == null) {
            layoutParams = new ViewGroup.LayoutParams(LayoutParams.MATCH_PARENT, height);
        } else {
            layoutParams.width = LayoutParams.MATCH_PARENT;
            layoutParams.height = height;
        }
        setLayoutParams(layoutParams);
        mLoadingAnimator = createLoadingAnimator();
    }


    private Animator createLoadingAnimator() {
        ObjectAnimator rotation = ObjectAnimator
                .ofFloat(mImgLoading, "rotation", 0, 360);
        //RotateAnimation animation = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotation.setRepeatCount(ObjectAnimator.INFINITE);
        rotation.setRepeatMode(ObjectAnimator.RESTART);
        rotation.setInterpolator(new LinearInterpolator());
        rotation.setDuration(mLoadingTime);
        return rotation;
    }
}
