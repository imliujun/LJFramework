package com.remair.util.rx;

import com.remair.log.LogUtils;
import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import java.util.concurrent.TimeUnit;

/**
 * 项目名称：heixiu_2.3
 * 类描述：
 * 创建人：LiuJun
 * 创建时间：2016/11/12 11:05
 * 修改人：LiuJun
 * 修改时间：2016/11/12 11:05
 * 修改备注：
 */
public class RetryWithDelay implements Function<Observable<? extends Throwable>, Observable<?>> {

    private final int maxRetries;
    private final int retryDelayMillis;
    private int retryCount;


    /**
     * @param maxRetries 最大重试次数
     * @param retryDelayMillis 重试间隔时间，毫秒
     */
    public RetryWithDelay(int maxRetries, int retryDelayMillis) {
        this.maxRetries = maxRetries;
        this.retryDelayMillis = retryDelayMillis;
    }


    @Override
    public Observable<?> apply(@NonNull Observable<? extends Throwable> attempts) throws Exception {
        return attempts.flatMap(new Function<Throwable, Observable<?>>() {
            @Override
            public Observable<?> apply(@NonNull Throwable throwable) throws Exception {
                if (++retryCount <= maxRetries) {
                    // When this Observable calls onNext, the original Observable will be retried (i.e. re-subscribed).
                    LogUtils.e("get error, it will try after " + retryDelayMillis +
                            " millisecond, retry count " + retryCount);
                    return Observable.timer(retryDelayMillis, TimeUnit.MILLISECONDS);
                }
                // Max retries hit. Just pass the error along.
                return Observable.error(throwable);
            }
        });
    }
}
