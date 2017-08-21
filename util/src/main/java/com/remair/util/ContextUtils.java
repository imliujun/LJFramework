package com.remair.util;

import android.annotation.SuppressLint;
import android.content.Context;

/**
 * 项目名称：heixiu
 * 类描述：
 * 创建人：LiuJun
 * 创建时间：2017/3/20 19:37
 * 修改人：LiuJun
 * 修改时间：2017/3/20 19:37
 * 修改备注：
 */
public class ContextUtils {

    @SuppressLint("StaticFieldLeak") private static Context sContext;


    private ContextUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }


    /**
     * 初始化工具类
     *
     * @param context 上下文
     */
    public static void init(Context context) {
        ContextUtils.sContext = context.getApplicationContext();
    }


    /**
     * 获取ApplicationContext
     *
     * @return ApplicationContext
     */
    public static Context getContext() {
        if (sContext != null) {
            return sContext;
        }
        throw new NullPointerException("u should init first");
    }
}
