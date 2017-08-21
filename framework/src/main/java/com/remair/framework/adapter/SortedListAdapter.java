package com.remair.framework.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.util.SortedList;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.remair.framework.adapter.util.ISortedAdapter;
import kale.adapter.item.AdapterItem;
import kale.adapter.util.ItemTypeUtil;

/**
 * 项目名称：heixiu_2.3
 * 类描述：
 * 创建人：LiuJun
 * 创建时间：16/10/10 16:15
 * 修改人：LiuJun
 * 修改时间：16/10/10 16:15
 * 修改备注：
 */
@SuppressWarnings("VisibleForTests")
public abstract class SortedListAdapter<T> extends RecyclerView.Adapter<SortedListAdapter.RcvAdapterItem<T>> implements ISortedAdapter<T> {

    private SortedList<T> mDataList;
    private Object mType;
    private ItemTypeUtil mUtil;
    private int currentPos;


    public SortedListAdapter(@Nullable SortedList<T> data) {
        this.mDataList = data;
        this.mUtil = new ItemTypeUtil();
    }


    public int getItemCount() {
        return this.mDataList == null ? 0 : this.mDataList.size();
    }


    @Override
    public void setData(@NonNull SortedList<T> data) {
        this.mDataList = data;
    }


    public SortedList<T> getData() {
        return this.mDataList;
    }


    public long getItemId(int position) {
        return (long) position;
    }


    /** @deprecated  */
    @Deprecated
    public int getItemViewType(int position) {
        this.currentPos = position;
        this.mType = this.getItemType(this.mDataList.get(position));
        return this.mUtil.getIntType(this.mType);
    }


    public Object getItemType(T t) {
        return -1;
    }


    @Override
    public RcvAdapterItem<T> onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RcvAdapterItem<>(parent.getContext(), parent, this
                .createItem(this.mType));
    }


    @Override
    public void onBindViewHolder(RcvAdapterItem<T> holder, int position) {
        holder.item.handleData(mDataList.get(position), position);
    }


    @NonNull
    @Override
    public Object getConvertedData(T data, Object type) {
        return data;
    }


    public int getCurrentPosition() {
        return this.currentPos;
    }


    static class RcvAdapterItem<T> extends RecyclerView.ViewHolder {
        protected AdapterItem<T> item;


        RcvAdapterItem(Context context, ViewGroup parent, AdapterItem<T> item) {
            super(LayoutInflater.from(context)
                                .inflate(item.getLayoutResId(), parent, false));
            this.item = item;
            this.item.bindViews(this.itemView);
            this.item.setViews();
        }
    }
}
