package me.sheepyang.onlylive.widget.dialog;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.sheepyang.onlylive.R;
import me.sheepyang.onlylive.widget.MarqueeTextView;

/**
 * Created by SheepYang on 2016/10/25 20:58.
 */

public class MessageDialog extends BaseDialogFragment {
    @BindView(R.id.tv_title)
    MarqueeTextView tvTitle;
    @BindView(R.id.tv_message)
    TextView tvMessage;
    @BindView(R.id.btn_cancel)
    Button btnCancel;
    @BindView(R.id.btn_ok)
    Button btnOk;
    @BindView(R.id.ll_button)
    LinearLayout llButton;

    private String mTitle;
    private String mMessage;

    private OnOkClickListener mOkClickListener;
    private OnCancelClickListener mCancelClickListener;
    private OnDismissListener mOnDismissListener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.dialog_message, null);
        ButterKnife.bind(this, view);
        getDialog().setCanceledOnTouchOutside(true);//点击边际可消失
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));// 设置背景透明
        initView();
        return view;
    }

    private void initView() {
        setTitle(mTitle);
        setMessage(mMessage);
    }

    private void setMessage(String msg) {
        mMessage = msg;
        if (!TextUtils.isEmpty(mMessage)) {
            tvMessage.setText(mMessage);
            tvMessage.setVisibility(View.VISIBLE);
        } else {
            tvMessage.setText("");
            tvMessage.setVisibility(View.GONE);
        }
    }

    public String getMessage() {
        return mMessage;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
        if (!TextUtils.isEmpty(mTitle)) {
            tvTitle.setText(mTitle);
            tvTitle.setVisibility(View.VISIBLE);
        } else {
            tvTitle.setText("");
            tvTitle.setVisibility(View.GONE);
        }
    }

    public OnOkClickListener getOkClickListener() {
        return mOkClickListener;
    }

    public void setOkClickListener(String text, OnOkClickListener okClickListener) {
        mOkClickListener = okClickListener;
        if (!TextUtils.isEmpty(text)) {
            btnOk.setText(text);
        } else {
            btnOk.setText("确定");
        }
        btnOk.setVisibility(View.VISIBLE);
        llButton.setVisibility(View.VISIBLE);
    }

    @Override
    public void dismiss() {
        super.dismiss();
        if (mOnDismissListener != null) {
            mOnDismissListener.onDismiss();
        }
    }

    public OnCancelClickListener getCancelClickListener() {
        return mCancelClickListener;
    }

    public void setCancelClickListener(String text, OnCancelClickListener cancelClickListener) {
        mCancelClickListener = cancelClickListener;
        if (!TextUtils.isEmpty(text)) {
            btnCancel.setText(text);
        } else {
            btnCancel.setText("取消");
        }
        btnCancel.setVisibility(View.VISIBLE);
        llButton.setVisibility(View.VISIBLE);
    }

    public OnDismissListener getOnDismissListener() {
        return mOnDismissListener;
    }

    public void setOnDismissListener(OnDismissListener onDismissListener) {
        mOnDismissListener = onDismissListener;
    }

    @OnClick({R.id.btn_cancel, R.id.btn_ok})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_cancel:
                if (mCancelClickListener != null) {
                    mCancelClickListener.onClick(view);
                }
                break;
            case R.id.btn_ok:
                if (mOkClickListener != null) {
                    mOkClickListener.onClick(view);
                }
                break;
            default:
                break;
        }
    }

    public interface OnOkClickListener {
        void onClick(View view);
    }

    public interface OnCancelClickListener {
        void onClick(View view);
    }

    public interface OnDismissListener {
        void onDismiss();
    }
}
