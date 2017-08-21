package com.remair.framework.adapter;

import android.support.annotation.NonNull;
import android.support.v7.util.SortedList;
import com.remair.framework.adapter.util.ItemSupplier;
import kale.adapter.item.AdapterItem;

/**
 * 项目名称：heixiu
 * 类描述：
 * 创建人：LiuJun
 * 创建时间：2017/3/10 18:35
 * 修改人：LiuJun
 * 修改时间：2017/3/10 18:35
 * 修改备注：
 */
public class SortedCommonAdapter<T> extends SortedListAdapter<T> {

    private ItemSupplier<T> mItemItemSupplier;


    public SortedCommonAdapter(SortedList<T> data, @NonNull ItemSupplier<T> itemItemSupplier) {
        super(data);
        mItemItemSupplier = itemItemSupplier;
    }


    @Override
    public Object getItemType(T t) {
        return mItemItemSupplier.getItemType(t);
    }


    @NonNull
    @Override
    public AdapterItem<T> createItem(Object o) {
        return mItemItemSupplier.createItem((Integer) o);
    }
}
