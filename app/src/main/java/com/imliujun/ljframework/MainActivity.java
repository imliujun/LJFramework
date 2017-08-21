package com.imliujun.ljframework;

import android.os.Bundle;
import android.support.annotation.StringRes;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import com.remair.framework.base.BaseMVPActivity;
import com.remair.framework.mvp.IView;
import com.remair.framework.mvp.MVPMessage;
import com.remair.util.rx.RxViewUtil;
import io.reactivex.functions.Consumer;

public class MainActivity extends BaseMVPActivity<MainPresenter> implements IView {

    @BindView(R.id.btn_get_data) Button mBtnGetData;
    @BindView(R.id.text) TextView mText;
    @BindView(R.id.btn_get_err) Button mBtnGetErr;


    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }


    @Override
    protected void initData(Bundle savedInstanceState) {
        RxViewUtil.viewBindClick(mBtnGetData, new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                MVPMessage mvpMessage = MVPMessage.obtain(MainActivity.this);
                mvpMessage.arg1 = 101;
                mvpMessage.str = "小明";
                mPresenter.getData(mvpMessage);
            }
        });
        RxViewUtil.viewBindClick(mBtnGetErr, new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                MVPMessage mvpMessage = MVPMessage.obtain(MainActivity.this);
                mvpMessage.arg1 = 101;
                mPresenter.getErr(mvpMessage);
            }
        });
    }


    @Override
    protected MainPresenter getPresenter() {
        return new MainPresenter();
    }


    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void showMessage(@StringRes int message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void handleMessage(MVPMessage message) {
        if (message.what == MainPresenter.GET_DATA) {
            mText.setText(message.str);
            showMessage("获取成功");
        }
    }
}
