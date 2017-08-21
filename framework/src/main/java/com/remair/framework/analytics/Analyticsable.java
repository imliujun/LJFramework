package com.remair.framework.analytics;

import android.content.Context;

/**
 * 项目名称：heixiu
 * 类描述：页面统计接口
 * 创建人：LiuJun
 * 创建时间：2017/3/21 14:53
 * 修改人：LiuJun
 * 修改时间：2017/3/21 14:53
 * 修改备注：
 */
public interface Analyticsable {

    void onResume(Context context);

    void onPause(Context context);
}
