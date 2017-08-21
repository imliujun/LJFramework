package com.remair.framework.common;

/**
 * 项目名称：heixiu
 * 类描述：
 * 创建人：LiuJun
 * 创建时间：2017/5/19 12:30
 * 修改人：LiuJun
 * 修改时间：2017/5/19 12:30
 * 修改备注：
 */
public interface ResultCallback {

    void onSuccess(Object object);

    void onError(Object err);
}
