package com.remair.framework.analytics;

import android.content.Context;

/**
 * 项目名称：heixiu
 * 类描述：页面统计代理类
 * 创建人：LiuJun
 * 创建时间：2017/3/21 14:44
 * 修改人：LiuJun
 * 修改时间：2017/3/21 14:44
 * 修改备注：
 */
public class AnalyticsAgent {

    private static Analyticsable sAnalyticsable;


    public static void setAnalyticsable(Analyticsable analyticsable) {
        sAnalyticsable = analyticsable;
    }


    public static void onResume(Context context) {
        if (sAnalyticsable != null) {
            sAnalyticsable.onResume(context);
        }
    }


    public static void onPause(Context context) {
        if (sAnalyticsable != null) {
            sAnalyticsable.onPause(context);
        }
    }
}
