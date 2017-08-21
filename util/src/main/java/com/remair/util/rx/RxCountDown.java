package com.remair.util.rx;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import java.util.concurrent.TimeUnit;

/**
 * 项目名称：Android
 * 类描述：
 * 创建人：wsk
 * 创建时间：2016/10/31 PM8:01
 * 修改人：wsk
 * 修改时间：2016/10/31 PM8:01
 * 修改备注：
 */
public class RxCountDown {

    public static Observable<Integer> countdown(int time) {
        if (time < 0) {
            time = 0;
        }
        final int countTime = time;
        return Observable.interval(0, 1, TimeUnit.SECONDS).observeOn(AndroidSchedulers.mainThread())
                         .map(new Function<Long, Integer>() {
                             @Override
                             public Integer apply(@NonNull Long increaseTime) throws Exception {
                                 return countTime - increaseTime.intValue();
                             }
                         }).take(countTime + 1);
    }
}
