package me.sheepyang.onlylive.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.sheepyang.onlylive.R;
import me.sheepyang.onlylive.app.Constants;
import me.sheepyang.onlylive.entity.Event;
import me.sheepyang.onlylive.entity.EventGoods;
import me.sheepyang.onlylive.entity.Player;
import me.sheepyang.onlylive.utils.StrUtil;
import me.sheepyang.onlylive.utils.data.EventUtil;
import me.sheepyang.onlylive.utils.data.PlayerUtil;

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
    private AlertDialog mFinishDialog;// 游戏结束对话框
    private AlertDialog mRestartDialog;// 重新开始游戏对话框
    private AlertDialog mQuitDialog;// 退出游戏对话框
    private AlertDialog mHintDialog;
    private AlertDialog mNewsDialog;
    private AlertDialog mEventDialog;// t突发事件对话框
    private AlertDialog mRepayDebtDialog;// 还债对话框
    private AlertDialog mHospitalDialog;// 医院治疗对话框


    private Player mPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        ButterKnife.bind(this);
        initDialog();
        initListener();
        initData();
        checkIsStart();
    }

    private void initData() {
        mPlayer = PlayerUtil.getPlayer();
        if (mPlayer == null) {
            showToast("暂无游戏数据，请开始新的游戏！");
            quitGame();
            return;
        }
        refreshPlayerData();
    }

    /**
     * 退出游戏
     */
    private void quitGame() {
        onBackPressed();
    }

    private void initDialog() {
        if (mFinishDialog == null) {
            mFinishDialog = new AlertDialog.Builder(this)
                    .setTitle("游戏结束")
                    .setCancelable(false)
                    .setPositiveButton("重来", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            restartGame();
                        }
                    })
                    .setNegativeButton("退出", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            quitGame();
                        }
                    })
                    .create();
        }
        if (mRepayDebtDialog == null) {
            mRepayDebtDialog = new AlertDialog.Builder(this)
                    .setTitle("债务")
                    .setPositiveButton("还债", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            toRepayDebt();
                        }
                    })
                    .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    })
                    .create();
        }
        if (mHospitalDialog == null) {
            mHospitalDialog = new AlertDialog.Builder(this)
                    .setTitle("医院")
                    .setPositiveButton("治疗", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            toTreatment();
                        }
                    })
                    .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    })
                    .create();
        }
        if (mRestartDialog == null) {
            mRestartDialog = new AlertDialog.Builder(this)
                    .setTitle("重新开始")
                    .setMessage("现在给你一次再来一次的机会，这将会清除所有数据，确定重来？")
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            restartGame();
                        }
                    })
                    .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    })
                    .create();
        }
        if (mQuitDialog == null) {
            mQuitDialog = new AlertDialog.Builder(this)
                    .setTitle("退出游戏")
                    .setMessage("确认退出游戏？（数据将被保存）")
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            mRestartDialog.dismiss();
                            quitGame();
                        }
                    })
                    .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    })
                    .create();
        }
        if (mHintDialog == null) {
            mHintDialog = new AlertDialog.Builder(this)
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    })
                    .create();
        }
        if (mEventDialog == null) {
            mEventDialog = new AlertDialog.Builder(this)
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

    private void restartGame() {
        PlayerUtil.deletePlayer();
        if (mRestartDialog.isShowing()) {
            mRestartDialog.dismiss();
        }
        if (mFinishDialog.isShowing()) {
            mFinishDialog.dismiss();
        }
        mRestartDialog.dismiss();
        PlayerUtil.initPlayerData();
        refreshPlayerData();
        checkIsStart();
    }

    /**
     * 归还债务
     */
    private void toRepayDebt() {
        if (mPlayer.getDebt() > 0) {
            int debt = mPlayer.getDebt();
            int cash = mPlayer.getCash();
            if (cash < debt) {
                showToast("开什么玩笑，你的钱够吗？\n              来找抽是吧？");
                return;
            }
            mPlayer.setCash(cash - debt);
            mPlayer.setDebt(0);
            PlayerUtil.setPlayer(mPlayer);
            refreshPlayerData();
            mHintDialog.setTitle("债务");
            mHintDialog.setMessage("欠款" + debt + "元已还清，总算松了口气");
            mHintDialog.show();
        } else {
            showToast("你没欠我钱呀，兄弟这是打算请我去大宝剑？");
        }
    }

    /**
     * 去治疗
     */
    private void toTreatment() {
        int newMoney = mPlayer.getCash() - getTreatmentMoney();
        if (newMoney < 0) {
            showToast("没钱治什么病！赶快走！");
            return;
        }
        if (mPlayer.getHealth() == 100) {
            showToast("             没病找抽是吧？\n你现在很健康，不需要治疗！");
            return;
        }
        mPlayer.setCash(newMoney);
        mPlayer.setHealth(100);
        PlayerUtil.setPlayer(mPlayer);
        refreshPlayerData();
    }

    /**
     * 刷新个人数据展示界面
     */
    private void refreshPlayerData() {
        mPlayer = PlayerUtil.getPlayer();
        tvCash.setText(mPlayer.getCash() + "");
        tvDebt.setText(mPlayer.getDebt() + "");
        tvDeposit.setText(mPlayer.getDeposit() + "");
        tvHealth.setText(mPlayer.getHealth() + "");
        tvHouse.setText(mPlayer.getHouse() + "/" + Constants.CONFIG_TOTAL_HOUSE);
        tvWeek.setText(mPlayer.getWeek() + "/" + Constants.CONFIG_TOTAL_WEEK);
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

    @OnClick({R.id.btn_restart, R.id.btn_quit, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4, R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9, R.id.rb_1, R.id.rb_2, R.id.rb_3, R.id.rb_4, R.id.rb_5})
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.btn_restart:
                mRestartDialog.show();
                break;
            case R.id.btn_quit:
                mQuitDialog.show();
                break;
            case R.id.btn1:// 选择城市
            case R.id.btn2:
            case R.id.btn3:
            case R.id.btn4:
            case R.id.btn5:
            case R.id.btn6:
            case R.id.btn7:
            case R.id.btn8:
            case R.id.btn9:
                selectCity();
                break;
            case R.id.rb_1:// 银行
                if (!checkIsStart()) {
                    return;
                }
                showBankDialog();
                break;
            case R.id.rb_2:// 医院
                if (!checkIsStart()) {
                    return;
                }
                showHospitalDialog();
                break;
            case R.id.rb_3:// 还债
                if (!checkIsStart()) {
                    return;
                }
                showRepayDebtDialog();
                break;
            case R.id.rb_4:// 买卖
                if (!checkIsStart()) {
                    return;
                }
                showShopDialog();
                break;
            case R.id.rb_5:// 租房
                if (!checkIsStart()) {
                    return;
                }
                showRentalDialog();
                break;
            default:
                break;
        }
    }

    private void selectCity() {
        if (mPlayer.getIsFirst()) {
            mPlayer.setIsFirst(false);
            PlayerUtil.setPlayer(mPlayer);
        }
        showSurpriseDialog();
//        checkWeekAndState();
    }

    /**
     * 在每次购买物品之后
     * 检查当前游戏状态，判断游戏是否结束
     *
     * @return
     */
    private boolean checkWeekAndState() {
        int week = mPlayer.getWeek() + 1;
        if (week > Constants.CONFIG_TOTAL_WEEK) {
            if (mEventDialog.isShowing()) {
                mEventDialog.dismiss();
            }
            if (mNewsDialog.isShowing()) {
                mNewsDialog.dismiss();
            }
            mFinishDialog.setMessage("当前为第" + mPlayer.getWeek() + "周数。\n当前资产为：" + mPlayer.getCash() + mPlayer.getDeposit() + "\n游戏结束！");
            mFinishDialog.show();
            return false;
        } else {
            mPlayer.setWeek(week);
            PlayerUtil.setPlayer(mPlayer);
            refreshPlayerData();
            return true;
        }
    }

    /**
     * 显示租房对话框
     */
    private void showRentalDialog() {
        showToast("显示租房对话框");
    }

    /**
     * 显示银行对话框
     */
    private void showBankDialog() {
        showToast("显示银行对话框");
    }

    private void showRepayDebtDialog() {
        if (mPlayer.getDebt() <= 0) {
            mRepayDebtDialog.setMessage("兄弟，咱们已经两清了！有空一起去喝喝小酒，把把小妞，吹吹小曲啊！哈哈~");
            mRepayDebtDialog.show();
        } else {
            mRepayDebtDialog.setMessage("你欠我的" + mPlayer.getDebt() + "元，今天能还不？");
            mRepayDebtDialog.show();
        }
    }

    /**
     * 显示医院对话框
     */
    private void showHospitalDialog() {
        if (mPlayer.getHealth() == 100) {
            mHospitalDialog.setMessage("你现在壮的跟头牛似得，不需要治疗！");
        } else {
            mHospitalDialog.setMessage("你当前的健康值" + mPlayer.getHealth() + "，治愈需要花费" + getTreatmentMoney() + "元，需要治疗么？");
        }
        mHospitalDialog.show();
    }

    /**
     * 显示意外事件对话框
     */
    private void showSurpriseDialog() {
        showPDialog();
        Event event = EventUtil.getRandomEvent();
        String msg = event.getMessage();
        List<EventGoods> eventGoodsList = event.getEventGoodsList();
        if (eventGoodsList != null && eventGoodsList.size() > 0) {
            for (int i = 0; i < eventGoodsList.size(); i++) {
                EventGoods eventGoods = eventGoodsList.get(i);
                msg = StrUtil.replaceGoodsChar(i, msg, eventGoods);// 替换掉字符串中的占位符
            }
        }
        if (event.getMoney() != null) {
            msg = StrUtil.replaceMoneyChar(msg, event.getMoney());// 替换掉字符串中的占位符
        }
        mEventDialog.setTitle(event.getTitle());
        mEventDialog.setMessage(msg);
        dismissPDialog();
        mEventDialog.show();
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
        showToast("显示买卖物品对话框");
    }

    /**
     * 检查是否选择了第一个城市并开始游戏
     */
    private boolean checkIsStart() {
        if (mPlayer.getIsFirst() == true) {
            mHintDialog.setTitle("选择城市");
            mHintDialog.setMessage("选择一个城市开始吧");
            mHintDialog.show();
            return false;
        }
        return true;
    }

    /**
     * 获得当前治疗的费用
     *
     * @return
     */
    public int getTreatmentMoney() {
        if (mPlayer.getHealth() > 0 && mPlayer.getHealth() < 100) {
            int treatmentMoney = 2000;
            DecimalFormat df = new DecimalFormat("0.00");
            String s = df.format(1 - (float) mPlayer.getHealth() / 100);
            Double percent = Double.valueOf(s);
            BigDecimal b1 = new BigDecimal(percent.toString());
            BigDecimal b2 = new BigDecimal(treatmentMoney);
            Double result = new Double(b1.multiply(b2).doubleValue());
            return (int) Math.round(result);
        }
        return 0;
    }
}
