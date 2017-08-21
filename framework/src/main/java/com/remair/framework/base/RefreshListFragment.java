package com.remair.framework.base;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.Unbinder;
import com.remair.framework.R;
import com.remair.framework.R2;
import com.remair.framework.adapter.HXCommonAdapter;
import com.remair.framework.adapter.util.ItemSupplier;
import com.remair.framework.adapter.util.OnRcvScrollListener;
import com.remair.framework.constant.Constants;
import com.remair.framework.mvp.IPresenter;
import com.remair.framework.view.LoadingRefreshHeader;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import java.util.ArrayList;
import java.util.List;
import kale.adapter.RcvAdapterWrapper;

/**
 * 项目名称：heixiu
 * 类描述：
 * 创建人：LiuJun
 * 创建时间：2017/4/14 16:25
 * 修改人：LiuJun
 * 修改时间：2017/4/14 16:25
 * 修改备注：
 */
public abstract class RefreshListFragment<M, P extends IPresenter> extends BaseMVPFragment<P> {
    @Nullable @BindView(R2.id.ptrFrameLayout) protected PtrFrameLayout mPtrFrameLayout;
    @BindView(R2.id.recycleView) protected RecyclerView mRecycleView;

    protected RcvAdapterWrapper mAdapter;

    protected List<M> mList = new ArrayList<>();

    protected int mPage;//当前页码
    protected boolean hasMore = true;//是否能够加载下一页
    protected boolean isClearList = false;//是否清空列表
    protected boolean isLoading = false;//当前是否正在加载中

    Unbinder mViewBinding;


    /**
     * 获取页面布局文件
     * 需要实现不同的界面效果可以重写此方法
     * 只要将RecyclerView 和PtrFrameLayout控件的ID设置为对应的ID就可以
     *
     * @return layout id
     */
    @LayoutRes
    protected int getLayoutResId() {
        return R.layout.fragment_refresh_default;
    }


    protected abstract RecyclerView.LayoutManager getLayoutManager();

    protected abstract ItemSupplier<M> getAdapterItemSupplier();


    /**
     * 获取列表头部
     */
    protected View getHeaderView() {
        return null;
    }


    /**
     * 获取列表空视图
     */
    protected View getEmptyView() {
        return null;
    }


    /**
     * 请求列表的数据，将数据回调到{{@link #onDataSuccess(List)}}进行列表刷新
     *
     * @param getMore 是否可以加载更多
     */
    protected abstract void requestData(boolean getMore);


    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(getLayoutResId(), container, false);
        mViewBinding = new RefreshListFragment_ViewBinding(this, view);

        if (mPtrFrameLayout != null) {
            mPtrFrameLayout.setResistance(1.7f);
            mPtrFrameLayout.setRatioOfHeaderHeightToRefresh(1.2f);
            mPtrFrameLayout.setDurationToClose(200);
            mPtrFrameLayout.setDurationToCloseHeader(1000);
            mPtrFrameLayout.setKeepHeaderWhenRefresh(true);
            mPtrFrameLayout.setPullToRefresh(false);
            mPtrFrameLayout.disableWhenHorizontalMove(true);
            LoadingRefreshHeader refreshHeader = new LoadingRefreshHeader(mPtrFrameLayout
                    .getContext());
            mPtrFrameLayout.setHeaderView(refreshHeader);
            mPtrFrameLayout.addPtrUIHandler(refreshHeader);
            mPtrFrameLayout.setPtrHandler(new PtrDefaultHandler() {
                @Override
                public void onRefreshBegin(PtrFrameLayout frame) {
                    hasMore = true;
                    loadData(false);
                }
            });
        }

        mRecycleView.addOnScrollListener(new OnRcvScrollListener(0) {

            @Override
            public void onBottom() {
                if (hasMore) {
                    loadData(true);
                }
            }
        });
        mRecycleView.setLayoutManager(getLayoutManager());

        ItemSupplier<M> itemSupplier = getAdapterItemSupplier();
        if (itemSupplier == null) {
            return view;
        }
        HXCommonAdapter<M> myAdapter = new HXCommonAdapter<>(mList, itemSupplier);
        mAdapter = new RcvAdapterWrapper(myAdapter, mRecycleView.getLayoutManager());
        mRecycleView.setAdapter(mAdapter);
        View headerView = getHeaderView();
        if (null != headerView) {
            mAdapter.setHeaderView(headerView);
        }
        View emptyView = getEmptyView();
        if (emptyView != null) {
            mAdapter.setEmptyView(emptyView, mRecycleView);
        }

        return view;
    }


    protected void loadData(boolean getMore) {
        if (isLoading) {
            return;
        }
        isLoading = true;
        isClearList = !getMore;
        if (getMore) {
            mPage = mList.size() / Constants.PAGE_LIMIT_20;
            if (mList.size() % Constants.PAGE_LIMIT_20 != 0) {
                mPage += 1;
            }
            mPage += 1;
        } else {
            mPage = 1;
        }
        requestData(getMore);
    }


    /**
     * requestData方法请求数据成功后将数据集传入本方法进行刷新列表
     */
    protected void onDataSuccess(@Nullable List<M> newData) {
        if (mAdapter == null) {
            return;
        }
        isLoading = false;
        if (isClearList) {
            mList.clear();
            if (mAdapter != null) {
                mAdapter.notifyDataSetChanged();
            }
        }
        if (null != mPtrFrameLayout) {
            mPtrFrameLayout.refreshComplete();
        }
        hasMore = checkLoadMore(newData);
        if (null != newData && newData.size() > 0) {
            int positionStart = mList.size();
            mList.addAll(newData);
            mAdapter.notifyItemRangeInserted(
                    positionStart + mAdapter.getHeaderCount(), newData.size());
        }
    }


    /**
     * 判断是否能够加载下一页
     *
     * @param newData 本次数据源
     */
    protected boolean checkLoadMore(@Nullable List<M> newData) {
        return null != newData && newData.size() >= Constants.PAGE_LIMIT_20;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mViewBinding != null && mViewBinding != Unbinder.EMPTY) {
            mViewBinding.unbind();
            mViewBinding = null;
        }
        mAdapter = null;
    }


    @Override
    protected P getPresenter() {
        return null;
    }
}
