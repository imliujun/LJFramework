package com.remair.framework.adapter.util;

/**
 * 项目名称：heixiu_2.3
 * 类描述：
 * 创建人：LiuJun
 * 创建时间：2016/10/20 14:59
 * 修改人：LiuJun
 * 修改时间：2016/10/20 14:59
 * 修改备注：
 */
public abstract class SimpleItemSupplier<T> implements ItemSupplier<T> {

    @Override
    public int getItemType(T t) {
        return -1;
    }
}
