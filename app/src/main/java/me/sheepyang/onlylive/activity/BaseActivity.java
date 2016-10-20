package me.sheepyang.onlylive.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import me.sheepyang.onlylive.utils.MyToast;

public class BaseActivity extends AppCompatActivity implements View.OnClickListener {
    public Context mContext;
    public ProgressDialog mPDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        mPDialog = new ProgressDialog(mContext);
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
