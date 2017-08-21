package com.remair.framework.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.remair.framework.adapter.util.ItemSupplier;
import java.util.List;
import kale.adapter.CommonRcvAdapter;
import kale.adapter.item.AdapterItem;

/**
 * 项目名称：Android
 * 类描述：
 * 创建人：LiuJun
 * 创建时间：16/8/26 17:55
 * 修改人：LiuJun
 * 修改时间：16/8/26 17:55
 * 修改备注：
 */
public class HXCommonAdapter<T> extends CommonRcvAdapter<T> {
    private ItemSupplier<T> mItemItemSupplier;


    public HXCommonAdapter(@Nullable List<T> data, @NonNull ItemSupplier<T> itemItemSupplier) {
        super(data);
        mItemItemSupplier = itemItemSupplier;
    }


    @Override
    public Object getItemType(T t) {
        return mItemItemSupplier.getItemType(t);
    }


    @NonNull
    @Override
    public AdapterItem createItem(Object o) {
        return mItemItemSupplier.createItem((Integer) o);
    }
}
