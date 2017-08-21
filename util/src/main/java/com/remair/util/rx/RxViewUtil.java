package com.remair.util.rx;

import android.support.annotation.Nullable;
import android.view.View;
import com.jakewharton.rxbinding2.view.RxView;
import com.remair.log.LogUtils;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import java.util.concurrent.TimeUnit;

/**
 * 项目名称：Android
 * 类描述：
 * 创建人：LiuJun
 * 创建时间：16/8/10 17:39
 * 修改人：LiuJun
 * 修改时间：16/8/10 17:39
 * 修改备注：
 */
public class RxViewUtil {

    @Nullable
    public static Disposable viewBindClick(final View view, final View.OnClickListener onClickListener) {
        if (nullCheck(view, onClickListener)) {
            return null;
        }
        return viewBindClick(view, 1, onClickListener);
    }


    /**
     * View绑定点击事件
     *
     * @param windowDuration 单位时间内,只响应一次点击事件。单位:秒
     */
    @Nullable
    public static Disposable viewBindClick(final View view, long windowDuration, final View.OnClickListener onClickListener) {
        if (nullCheck(view, onClickListener)) {
            return null;
        }
        return viewBindClick(view, windowDuration, new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                if (onClickListener != null) {
                    onClickListener.onClick(view);
                }
            }
        });
    }


    @Nullable
    public static Disposable viewBindClick(View view, Consumer<Object> action1) {
        return viewBindClick(view, 1, action1);
    }


    /**
     * View绑定点击事件
     *
     * @param windowDuration 单位时间内,只响应一次点击事件。单位:秒
     */
    @Nullable
    public static Disposable viewBindClick(View view, long windowDuration, Consumer<Object> action1) {
        if (nullCheck(view, action1)) {
            return null;
        }
        return RxView.clicks(view).throttleFirst(windowDuration, TimeUnit.SECONDS)
                     .subscribe(action1, new Consumer<Throwable>() {
                         @Override
                         public void accept(Throwable throwable) throws Exception {
                             LogUtils.e(throwable);
                         }
                     });
    }


    private static boolean nullCheck(View view, Object onClickListener) {
        return view == null || onClickListener == null;
    }
}
