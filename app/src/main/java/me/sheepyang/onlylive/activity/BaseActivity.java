package me.sheepyang.onlylive.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.umeng.message.PushAgent;

import me.sheepyang.onlylive.utils.AppManager;
import me.sheepyang.onlylive.utils.MyToast;

public class BaseActivity extends AppCompatActivity implements View.OnClickListener {
    public Context mContext;
    public ProgressDialog mPDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        mPDialog = new ProgressDialog(mContext);
        // 添加Activity到堆栈
        AppManager.getAppManager().addActivity((Activity) mContext);
        // 友盟推送 统计应用启动数据
        PushAgent.getInstance(mContext).onAppStart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 结束Activity&从堆栈中移除
        AppManager.getAppManager().finishActivity((Activity) mContext);
    }

    @Override
    public void onClick(View view) {

    }

    public void showToast(String msg) {
        MyToast.showMessage(mContext, msg);
    }

    public void showPDialog() {
        if (!isFinishing() && mPDialog != null && !mPDialog.isShowing()) {
            mPDialog.show();
        }
    }

    public void dismissPDialog() {
        if (!isFinishing() && mPDialog != null && mPDialog.isShowing()) {
            mPDialog.dismiss();
        }
    }
}
