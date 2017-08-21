package com.remair.framework.adapter.util;

import kale.adapter.item.AdapterItem;

/**
 * 项目名称：Android
 * 类描述：一个类,它可以提供一个类型的对象。
 * 创建人：LiuJun
 * 创建时间：16/8/26 18:08
 * 修改人：LiuJun
 * 修改时间：16/8/26 18:08
 * 修改备注：
 */
public interface ItemSupplier<T> {

    /**
     * @param t list中的一条数据
     * @return 强烈建议返回string, int, bool类似的基础对象做type
     */
    int getItemType(T t);

    /**
     * 当缓存中无法得到所需item时才会调用
     *
     * @param type 通过{@link #getItemType(Object)}得到的type
     * @return 任意类型的 AdapterItem
     */
    AdapterItem<T> createItem(int type);
}
