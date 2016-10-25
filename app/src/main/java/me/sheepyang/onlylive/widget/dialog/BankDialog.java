package me.sheepyang.onlylive.widget.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.sheepyang.onlylive.R;
import me.sheepyang.onlylive.widget.MarqueeTextView;

/**
 * Created by SheepYang on 2016/10/25 20:58.
 */

public class BankDialog extends Dialog {
    public static final int TYPE_SAVE_MONEY = 1111;
    public static final int TYPE_GET_MONEY = 2222;
    @BindView(R.id.tv_title)
    MarqueeTextView tvTitle;
    @BindView(R.id.tvSaveMoney)
    TextView tvSaveMoney;
    @BindView(R.id.tvGetMoney)
    TextView tvGetMoney;
    @BindView(R.id.rlSaveMoney)
    RelativeLayout rlSaveMoney;
    @BindView(R.id.rlGetMoney)
    RelativeLayout rlGetMoney;
    @BindView(R.id.seekbar)
    SeekBar seekbar;
    @BindView(R.id.tvHint)
    TextView tvHint;
    @BindView(R.id.tvMoney)
    TextView tvMoney;
    private Context mContext;
    private long mCash;
    private long mDeposit;
    private int mType;
    private int mMoney;
    private OnClickListener mListener;

    public BankDialog(Context context) {
        super(context);
        init(context);
    }

    public BankDialog(Context context, int themeResId) {
        super(context, themeResId);
        init(context);
    }

    protected BankDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        init(context);
    }

    private void init(Context context) {
        mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_bank);
        ButterKnife.bind(this);
        getWindow().setBackgroundDrawable(new ColorDrawable());
        setCanceledOnTouchOutside(true);
        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mMoney = progress;
                tvMoney.setText(mMoney + "");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public void setSelectType(int type) {
        mType = type;
        switch (type) {
            case TYPE_SAVE_MONEY:// 存钱
                rlSaveMoney.setBackgroundResource(R.drawable.bg_white_shape);
                rlGetMoney.setBackgroundResource(R.drawable.bg_black_shape);
                tvSaveMoney.setTextColor(mContext.getResources().getColor(R.color.text_black));
                tvGetMoney.setTextColor(mContext.getResources().getColor(R.color.text_white));
                tvHint.setText("存入：");
                seekbar.setMax((int) mCash);
                seekbar.setProgress((int) mCash);
                break;
            case TYPE_GET_MONEY:// 取钱
                rlSaveMoney.setBackgroundResource(R.drawable.bg_black_shape);
                rlGetMoney.setBackgroundResource(R.drawable.bg_white_shape);
                tvSaveMoney.setTextColor(mContext.getResources().getColor(R.color.text_white));
                tvGetMoney.setTextColor(mContext.getResources().getColor(R.color.text_black));
                tvHint.setText("取出：");
                seekbar.setMax((int) mDeposit);
                seekbar.setProgress((int) mDeposit);
                break;
            default:
                break;
        }
    }

    @Override
    public void setTitle(CharSequence title) {
        super.setTitle(title);
        tvTitle.setText(title);
    }

    @Override
    public void show() {
        super.show();
        WindowManager windowManager = ((Activity) mContext).getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.width = (int) (display.getWidth()); // 设置宽度
        getWindow().setAttributes(lp);
        setSelectType(TYPE_SAVE_MONEY);
    }

    @OnClick({R.id.rlSaveMoney, R.id.rlGetMoney, R.id.btn_cancel, R.id.btn_ok})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rlSaveMoney:
                setSelectType(TYPE_SAVE_MONEY);
                break;
            case R.id.rlGetMoney:
                setSelectType(TYPE_GET_MONEY);
                break;
            case R.id.btn_cancel:
                dismiss();
                break;
            case R.id.btn_ok:
                if (mListener != null) {
                    mListener.click(this, mType, mMoney);
                }
                dismiss();
                break;
            default:
                break;
        }
    }

    public void setCash(long cash) {
        mCash = cash;
    }

    public void setDeposit(long deposit) {
        mDeposit = deposit;
    }

    public void setOnClickListener(OnClickListener listener) {
        mListener = listener;
    }

    public interface OnClickListener {
        void click(Dialog dialog, int tpye, int money);
    }
}
