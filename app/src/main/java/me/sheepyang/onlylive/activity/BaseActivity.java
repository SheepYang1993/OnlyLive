package me.sheepyang.onlylive.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.MenuItem;
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
        //以下代码用于去除阴影
        if (Build.VERSION.SDK_INT >= 21) {
            if (getSupportActionBar() != null) {
                getSupportActionBar().setElevation(0);
            }

        }
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

    public void setBackBarTitle(String title) {
        setBarTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void setBarTitle(String title) {
        if (!TextUtils.isEmpty(title)) {
            getSupportActionBar().setTitle(title);
        }
    }

    public void dismissPDialog() {
        if (!isFinishing() && mPDialog != null && mPDialog.isShowing()) {
            mPDialog.dismiss();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:// 点击返回图标事件
                onBackPressed();
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
