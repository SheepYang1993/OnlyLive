package me.sheepyang.onlylive.widget.dialog;

import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Spanned;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
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
    @BindView(R.id.rl_button)
    RelativeLayout rlButton;

    private String mTitle;
    private String mMessage;

    private OnOkClickListener mOkClickListener;
    private OnCancelClickListener mCancelClickListener;
    private OnDismissListener mDismissListener;
    private String mOkText;
    private String mCancelText;
    private Spanned mMessageSpan;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.dialog_message, null);
        ButterKnife.bind(this, view);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));// 设置背景透明
        initView();
        return view;
    }

    private void initView() {
        // 设置标题
        if (!TextUtils.isEmpty(mTitle)) {
            tvTitle.setText(mTitle);
            tvTitle.setVisibility(View.VISIBLE);
        } else {
            tvTitle.setText("");
            tvTitle.setVisibility(View.GONE);
        }
        // 设置内容
        if (!TextUtils.isEmpty(mMessage) || mMessageSpan != null) {
            if (!TextUtils.isEmpty(mMessage)) {
                tvMessage.setText(mMessage);
            }
            if (mMessageSpan != null) {
                tvMessage.setText(mMessageSpan);
            }
            tvMessage.setVisibility(View.VISIBLE);
        } else {
            tvMessage.setText("");
            tvMessage.setVisibility(View.GONE);
        }
        if (mOkClickListener != null) {
            if (!TextUtils.isEmpty(mOkText)) {
                btnOk.setText(mOkText);
            }
            btnOk.setVisibility(View.VISIBLE);
            rlButton.setVisibility(View.VISIBLE);
        }
        if (mCancelClickListener != null) {
            if (!TextUtils.isEmpty(mCancelText)) {
                btnCancel.setText(mCancelText);
            }
            btnCancel.setVisibility(View.VISIBLE);
            rlButton.setVisibility(View.VISIBLE);
        }
    }

    public void setMessage(Spanned spanned) {
        mMessageSpan = spanned;
    }

    public void setMessage(String msg) {
        mMessage = msg;
    }

    public String getMessage() {
        return mMessage;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public OnOkClickListener getOkClickListener() {
        return mOkClickListener;
    }

    public void setOnOkClickListener(String text, OnOkClickListener okClickListener) {
        mOkClickListener = okClickListener;
        mOkText = text;
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        if (mDismissListener != null) {
            mDismissListener.onDismiss(dialog);
        }
    }

    public OnCancelClickListener getCancelClickListener() {
        return mCancelClickListener;
    }

    public void setOnCancelClickListener(String text, OnCancelClickListener cancelClickListener) {
        mCancelClickListener = cancelClickListener;
        mCancelText = text;
    }

    public OnDismissListener getDismissListener() {
        return mDismissListener;
    }

    public void setDismissListener(OnDismissListener dismissListener) {
        mDismissListener = dismissListener;
    }

    @OnClick({R.id.btn_cancel, R.id.btn_ok})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_cancel:
                dismiss();
                if (mCancelClickListener != null) {
                    mCancelClickListener.onClick(view);
                }
                break;
            case R.id.btn_ok:
                dismiss();
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
        void onDismiss(DialogInterface dialog);
    }
}
