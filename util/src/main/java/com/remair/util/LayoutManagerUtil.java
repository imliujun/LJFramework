package com.remair.util;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.StaggeredGridLayoutManager;

/**
 * 项目名称：WeSing
 * 类描述：
 * 创建人：LiuJun
 * 创建时间：15/11/30 13:00
 * 修改人：LiuJun
 * 修改时间：15/11/30 13:00
 * 修改备注：
 */
public class LayoutManagerUtil {

    /**
     * 获取LinearLayoutManager，默认的ListView样式
     */
    public static LinearLayoutManager getLinearLayoutManager(Context context) {
        return getLinearLayoutManager(context, true, false);
    }


    /**
     * 获取LinearLayoutManager
     *
     * @param isVertical 是否为垂直模式
     * @param reverseLayout 是否逆向布局（聊天界面数据从下往上就是逆向布局）
     */
    public static LinearLayoutManager getLinearLayoutManager(Context context, boolean isVertical, boolean reverseLayout) {
        LinearLayoutManager layoutManager;
        if (isVertical) {
            layoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, reverseLayout);
        } else {
            layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, reverseLayout);
        }
        return layoutManager;
    }


    /**
     * 获取GridLayoutManager，默认的GridView样式
     *
     * @param spanCount 列数
     */
    public static GridLayoutManager getGridLayoutManager(Context context, int spanCount) {
        return getGridLayoutManager(context, spanCount, true, false);
    }


    /**
     * 获取GridLayoutManager
     *
     * @param spanCount 如果是垂直方向,为列数。如果方向是水平的,为行数。
     * @param isVertical 是否为垂直模式
     * @param reverseLayout 是否逆向布局（聊天界面数据从下往上就是逆向布局）
     */
    public static GridLayoutManager getGridLayoutManager(Context context, int spanCount, boolean isVertical, boolean reverseLayout) {
        GridLayoutManager layoutManager;
        if (isVertical) {
            layoutManager = new GridLayoutManager(context, spanCount, GridLayoutManager.VERTICAL, reverseLayout);
        } else {
            layoutManager = new GridLayoutManager(context, spanCount, GridLayoutManager.HORIZONTAL, reverseLayout);
        }
        return layoutManager;
    }


    /**
     * 获取StaggeredGridLayoutManager，默认的瀑布流样式
     *
     * @param spanCount 列数
     */
    public static StaggeredGridLayoutManager getStaggeredGridLayoutManager(int spanCount) {
        return getStaggeredGridLayoutManager(spanCount, true);
    }


    /**
     * 获取StaggeredGridLayoutManager(瀑布流)
     *
     * @param spanCount 如果是垂直方向,为列数。如果方向是水平的,为行数。
     * @param isVertical 是否为垂直模式
     */
    public static StaggeredGridLayoutManager getStaggeredGridLayoutManager(int spanCount, boolean isVertical) {
        StaggeredGridLayoutManager layoutManager;
        if (isVertical) {
            layoutManager = new StaggeredGridLayoutManager(spanCount, StaggeredGridLayoutManager.VERTICAL);
        } else {
            layoutManager = new StaggeredGridLayoutManager(spanCount, StaggeredGridLayoutManager.HORIZONTAL);
        }
        return layoutManager;
    }
}
