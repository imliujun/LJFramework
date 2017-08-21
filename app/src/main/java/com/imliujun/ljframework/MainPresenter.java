package com.imliujun.ljframework;

import android.text.TextUtils;
import com.remair.framework.mvp.BasePresenter;
import com.remair.framework.mvp.MVPMessage;

/**
 * 项目名称：LJFramework
 * 类描述：
 * 创建人：liujun
 * 创建时间：2017/8/21 16:41
 * 修改人：liujun
 * 修改时间：2017/8/21 16:41
 * 修改备注：
 */
public class MainPresenter extends BasePresenter {

    public static final int GET_DATA = 1001;


    public void getData(MVPMessage mvpMessage) {
        //模拟网络请求，获取数据
        String builder = "网络请求到的数据：userId:" + mvpMessage.arg1 + ",name:" + mvpMessage.str;
        mvpMessage.what = GET_DATA;
        mvpMessage.str = builder;
        mvpMessage.handleMessageToTarget();
    }


    public void getErr(MVPMessage mvpMessage) {
        //模拟请求出错
        if (TextUtils.isEmpty(mvpMessage.str)) {
            mvpMessage.getTarget().showMessage("缺少参数");
            mvpMessage.recycle();
            return;
        }
        mvpMessage.what = GET_DATA;
        mvpMessage.str = "请求成功";
        mvpMessage.handleMessageToTarget();
    }
}
