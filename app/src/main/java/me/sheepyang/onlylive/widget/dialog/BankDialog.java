package me.sheepyang.onlylive.widget.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
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
import me.sheepyang.onlylive.utils.MathUtil;
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
    private String mCash;
    private String mDeposit;
    private int mType;
    private String mMoney;
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
                String percent = MathUtil.divide(progress + "", seekBar.getMax() + "", 2);// 百分比
                String cash = null;
                if (mType == TYPE_SAVE_MONEY) {
                    cash = mCash;
                } else if (mType == TYPE_GET_MONEY) {
                    cash = mDeposit;
                }
                if (cash != null) {
                    mMoney = MathUtil.multiply(cash, percent);
                    tvMoney.setText(mMoney);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        setSelectType(TYPE_SAVE_MONEY);
    }

    public void setSelectType(int type) {
        mType = type;
        switch (type) {
            case TYPE_SAVE_MONEY:// 存钱
                mMoney = mCash;
                rlSaveMoney.setBackgroundResource(R.drawable.bg_white2_shape);
                rlGetMoney.setBackgroundResource(R.drawable.bg_black_shape);
                tvSaveMoney.setTextColor(mContext.getResources().getColor(R.color.word_black));
                tvGetMoney.setTextColor(mContext.getResources().getColor(R.color.word_white));
                tvHint.setText("存入：");
                tvMoney.setText(mCash + "");
                seekbar.setProgress(seekbar.getMax());
                break;
            case TYPE_GET_MONEY:// 取钱
                mMoney = mDeposit;
                rlSaveMoney.setBackgroundResource(R.drawable.bg_black_shape);
                rlGetMoney.setBackgroundResource(R.drawable.bg_white2_shape);
                tvSaveMoney.setTextColor(mContext.getResources().getColor(R.color.word_white));
                tvGetMoney.setTextColor(mContext.getResources().getColor(R.color.word_black));
                tvHint.setText("取出：");
                tvMoney.setText(mDeposit + "");
                seekbar.setProgress(seekbar.getMax());
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

    public void setCash(String cash) {
        mCash = cash;
    }

    public void setDeposit(String deposit) {
        mDeposit = deposit;
    }

    public void setOnClickListener(OnClickListener listener) {
        mListener = listener;
    }

    public interface OnClickListener {
        void click(Dialog dialog, int tpye, String money);
    }
}
