package me.sheepyang.onlylive.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.sheepyang.onlylive.R;
import me.sheepyang.onlylive.app.Constant;
import me.sheepyang.onlylive.entity.Player;
import me.sheepyang.onlylive.utils.DataUtil;

/**
 * Created by SheepYang on 2016/10/8 22:01.
 */

public class GameActivity extends BaseActivity {
    @BindView(R.id.radioGroup)
    RadioGroup radioGroup;
    @BindView(R.id.rb_1)
    RadioButton rb1;
    @BindView(R.id.rb_2)
    RadioButton rb2;
    @BindView(R.id.rb_3)
    RadioButton rb3;
    @BindView(R.id.rb_4)
    RadioButton rb4;
    @BindView(R.id.rb_5)
    RadioButton rb5;
    @BindView(R.id.tv_cash)
    TextView tvCash;
    @BindView(R.id.tv_health)
    TextView tvHealth;
    @BindView(R.id.tv_debt)
    TextView tvDebt;
    @BindView(R.id.tv_house)
    TextView tvHouse;
    @BindView(R.id.tv_deposit)
    TextView tvDeposit;
    @BindView(R.id.tv_week)
    TextView tvWeek;
    private boolean isStart;// 是否选择第一个城市并开始游戏
    private AlertDialog mHintDialog;
    private AlertDialog mNewsDialog;
    private AlertDialog mMessageDialog;
    private Player mPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        ButterKnife.bind(this);
        initView();
        initListener();
        initData();
    }

    private void initData() {
        mPlayer = DataUtil.getPlayerData();
        if (mPlayer == null) {
            showToast("暂无游戏数据，请开始新的游戏！");
            onBackPressed();
            return;
        }
        tvCash.setText(mPlayer.getCash() + "");
        tvDebt.setText(mPlayer.getDebt() + "");
        tvDeposit.setText(mPlayer.getDeposit() + "");
        tvHealth.setText(mPlayer.getHealth() + "");
        tvHouse.setText(mPlayer.getHouse() + "/" + Constant.CONFIG_TOTAL_HOUSE);
        tvWeek.setText(mPlayer.getWeek() + "/" + Constant.CONFIG_TOTAL_WEEK);
    }

    private void initView() {
        if (mHintDialog == null) {
            mHintDialog = new AlertDialog.Builder(this)
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    })
                    .create();
        }
        if (mMessageDialog == null) {
            mMessageDialog = new AlertDialog.Builder(this)
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            showNewsDialog();
                        }
                    })
                    .setOnCancelListener(new DialogInterface.OnCancelListener() {
                        @Override
                        public void onCancel(DialogInterface dialog) {
                            showNewsDialog();
                        }
                    })
                    .create();
        }
        if (mNewsDialog == null) {
            mNewsDialog = new AlertDialog.Builder(this)
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            showShopDialog();
                        }
                    })
                    .setOnCancelListener(new DialogInterface.OnCancelListener() {
                        @Override
                        public void onCancel(DialogInterface dialog) {
                            showShopDialog();
                        }
                    })
                    .create();
        }
    }

    private void initListener() {
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_1:
                        break;
                    case R.id.rb_2:
                        break;
                    case R.id.rb_3:
                        break;
                    case R.id.rb_4:
                        break;
                    case R.id.rb_5:
                        break;
                    default:
                        break;
                }
            }
        });
    }

    @OnClick({R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4, R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9, R.id.rb_1, R.id.rb_2, R.id.rb_3, R.id.rb_4, R.id.rb_5})
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.btn1:
            case R.id.btn2:
            case R.id.btn3:
            case R.id.btn4:
            case R.id.btn5:
            case R.id.btn6:
            case R.id.btn7:
            case R.id.btn8:
            case R.id.btn9:
                isStart = true;
                showSurpriseDialog();
                break;
            case R.id.rb_1:
                if (!checkIsStart()) {
                    return;
                }
                break;
            case R.id.rb_2:
                if (!checkIsStart()) {
                    return;
                }
                break;
            case R.id.rb_3:
                if (!checkIsStart()) {
                    return;
                }
                break;
            case R.id.rb_4:// 买卖
                if (!checkIsStart()) {
                    return;
                }
                showShopDialog();
                break;
            case R.id.rb_5:
                if (!checkIsStart()) {
                    return;
                }
                break;
            default:
                break;
        }
    }

    /**
     * 显示意外事件对话框
     */
    private void showSurpriseDialog() {
        mMessageDialog.setTitle("老乡见老乡，两眼泪汪汪");
        mMessageDialog.setMessage("老乡送你2盒劣质化妆品。\n\n劣质化妆品x2");
        mMessageDialog.show();
    }

    /**
     * 显示新闻对话框
     */
    private void showNewsDialog() {
        mNewsDialog.setTitle("《民生观察》");
        mNewsDialog.setMessage("今日发生多起恶犬伤人事件，请市民自购装备，注意自身安全！");
        mNewsDialog.show();
    }

    /**
     * 显示买卖物品对话框
     */
    private void showShopDialog() {

    }

    /**
     * 检查是否选择了第一个城市并开始游戏
     */
    private boolean checkIsStart() {
        if (isStart == false) {
            mHintDialog.setTitle("选择城市");
            mHintDialog.setMessage("选择一个城市开始吧");
            mHintDialog.show();
            return false;
        }
        return true;
    }
}
