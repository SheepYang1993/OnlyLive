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

import butterknife.ButterKnife;
import butterknife.OnClick;
import me.sheepyang.onlylive.R;

/**
 * Created by SheepYang on 2016/10/8 19:50.
 */

public class SelectGameModeDialog extends Dialog implements View.OnClickListener {
    public static final int MODE_RESUME = 3000;// 练习模式
    public static final int MODE_NEW_GAME = 3001;// 冲榜模式
    private Context mContext;
    private OnSelectListener mListener;

    public SelectGameModeDialog(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {
        mContext = context;
    }

    public SelectGameModeDialog(Context context, int themeResId) {
        super(context, themeResId);
        init(context);
    }

    protected SelectGameModeDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        init(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_select_game_mode);
        ButterKnife.bind(this);
        getWindow().setBackgroundDrawable(new ColorDrawable());
        setCanceledOnTouchOutside(true);
    }

    @Override
    public void show() {
        super.show();
        WindowManager windowManager = ((Activity) mContext).getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.width = (int) (display.getWidth()); // 设置宽度
        getWindow().setAttributes(lp);
    }

    public void setOnSelectListener(OnSelectListener listener) {
        mListener = listener;
    }

    @OnClick({R.id.btn_mode1, R.id.btn_mode2})
    public void onClick(View v) {
        dismiss();
        switch (v.getId()) {
            case R.id.btn_mode1:
                if (mListener != null) {
                    mListener.OnSelect(v, MODE_NEW_GAME);
                }
                break;
            case R.id.btn_mode2:
                if (mListener != null) {
                    mListener.OnSelect(v, MODE_RESUME);
                }
                break;
            default:
                break;
        }
    }

    public interface OnSelectListener {
        void OnSelect(View view, int mode);
    }
}
