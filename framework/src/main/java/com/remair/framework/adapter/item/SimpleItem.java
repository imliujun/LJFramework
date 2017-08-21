package com.remair.framework.adapter.item;

import android.view.View;
import butterknife.ButterKnife;
import kale.adapter.item.AdapterItem;

/**
 * 项目名称：Android
 * 类描述：
 * 创建人：LiuJun
 * 创建时间：16/8/26 22:44
 * 修改人：LiuJun
 * 修改时间：16/8/26 22:44
 * 修改备注：
 */
public abstract class SimpleItem<T> implements AdapterItem<T> {

    protected View mRoot;


    @Override
    public void bindViews(View root) {
        mRoot = root;
        ButterKnife.bind(this, root);
    }


    @Override
    public void setViews() {

    }
}
