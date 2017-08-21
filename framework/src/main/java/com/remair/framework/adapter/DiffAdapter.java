package com.remair.framework.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;
import com.remair.framework.adapter.util.ItemSupplier;
import java.util.List;

/**
 * 项目名称：heixiu
 * 类描述：使用DiffUtil的Adapter
 * 创建人：liujun
 * 创建时间：2017/7/4 10:53
 * 修改人：liujun
 * 修改时间：2017/7/4 10:53
 * 修改备注：
 */
public abstract class DiffAdapter<T> extends HXCommonAdapter<T> {

    public DiffAdapter(@Nullable List<T> data, @NonNull ItemSupplier<T> itemItemSupplier) {
        super(data, itemItemSupplier);
    }


    @Override
    public void setData(@NonNull final List<T> data) {
        DiffUtil.calculateDiff(new DiffUtil.Callback() {
            @Override
            public int getOldListSize() {
                return getItemCount();
            }


            @Override
            public int getNewListSize() {
                return data.size();
            }


            /**
             * 检测是否是相同的item
             */
            @Override
            public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                return isItemSame(getData().get(oldItemPosition), data.get(newItemPosition));
            }


            /**
             * 检测是否是相同的数据
             * 这个方法仅仅在areItemsTheSame()返回true时，才调用。
             */
            @Override
            public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                return isContentSame(getData().get(oldItemPosition), data.get(newItemPosition));
            }
        }).dispatchUpdatesTo(this);
        super.setData(data);
    }


    /**
     * 检测是否是相同的item
     */
    protected abstract boolean isItemSame(T oldItemData, T newItemData);

    /**
     * 检测是否是相同的数据
     * 这个方法仅仅在 isItemSame()返回true时，才调用。
     */
    protected abstract boolean isContentSame(T oldItemData, T newItemData);
}
