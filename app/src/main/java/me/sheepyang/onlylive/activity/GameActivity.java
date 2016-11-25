package me.sheepyang.onlylive.activity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.sheepyang.onlylive.R;
import me.sheepyang.onlylive.entity.Event;
import me.sheepyang.onlylive.entity.Goods;
import me.sheepyang.onlylive.entity.Player;
import me.sheepyang.onlylive.entity.PlayerGoods;
import me.sheepyang.onlylive.utils.CacheUtil;
import me.sheepyang.onlylive.utils.MathUtil;
import me.sheepyang.onlylive.utils.MyLog;
import me.sheepyang.onlylive.utils.RandomUtil;
import me.sheepyang.onlylive.utils.data.EventUtil;
import me.sheepyang.onlylive.utils.data.GoodsUtil;
import me.sheepyang.onlylive.utils.data.PlayerGoodsUtil;
import me.sheepyang.onlylive.utils.data.PlayerUtil;
import me.sheepyang.onlylive.utils.data.ShopGoodsUtil;
import me.sheepyang.onlylive.widget.dialog.BankDialog;
import me.sheepyang.onlylive.widget.dialog.MessageDialog;
import me.sheepyang.onlylive.widget.dialog.ShopDialog;

import static me.sheepyang.onlylive.widget.dialog.BankDialog.TYPE_GET_MONEY;
import static me.sheepyang.onlylive.widget.dialog.BankDialog.TYPE_SAVE_MONEY;

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
    @BindView(R.id.btn1)
    Button btn1;
    @BindView(R.id.btn2)
    Button btn2;
    @BindView(R.id.btn3)
    Button btn3;
    @BindView(R.id.btn4)
    Button btn4;
    @BindView(R.id.btn5)
    Button btn5;
    @BindView(R.id.btn6)
    Button btn6;
    @BindView(R.id.btn7)
    Button btn7;
    @BindView(R.id.btn8)
    Button btn8;
    @BindView(R.id.btn9)
    Button btn9;
    private MessageDialog mFinishDialog;// 游戏结束对话框
    private MessageDialog mRestartDialog;// 重新开始游戏对话框
    private MessageDialog mQuitDialog;// 退出游戏对话框
    private MessageDialog mHintDialog;
    private MessageDialog mNewsDialog;
    private MessageDialog mEventDialog;// 突发事件对话框
    private MessageDialog mSelectEventDialog;// 需要选择的突发事件对话框
    private MessageDialog mSelectResultDialog;// 选择后结果展示的对话框
    private MessageDialog mRepayDebtDialog;// 还债对话框
    private MessageDialog mHospitalDialog;// 医院治疗对话框
    private MessageDialog mRentalDialog;// 租房对话框
    private BankDialog mBankDialog;// 银行对话框
    private ShopDialog mShopDialog;// 交易对话框
    private Player mPlayer;
    private boolean isShowNews = false;// 设置是否显示新闻事件
    private long mCurrentTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_game);
        ButterKnife.bind(this);
        initListener();
        initDialog();
        initData();
        checkIsStart();
    }

    private void initListener() {
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int color = getResources().getColor(R.color.word_black);
                rb1.setTextColor(color);
                rb2.setTextColor(color);
                rb3.setTextColor(color);
                rb4.setTextColor(color);
                rb5.setTextColor(color);
                switch (checkedId) {
                    case R.id.rb_1:
                        rb1.setTextColor(getResources().getColor(R.color.white));
                        break;
                    case R.id.rb_2:
                        rb2.setTextColor(getResources().getColor(R.color.white));
                        break;
                    case R.id.rb_3:
                        rb3.setTextColor(getResources().getColor(R.color.white));
                        break;
                    case R.id.rb_4:
                        rb4.setTextColor(getResources().getColor(R.color.white));
                        break;
                    case R.id.rb_5:
                        rb5.setTextColor(getResources().getColor(R.color.white));
                        break;
                    default:
                        break;
                }
            }
        });
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
        finish();
    }

    private void initDialog() {
        if (mShopDialog == null) {
            mShopDialog = new ShopDialog();
            mShopDialog.setOnShopListener(new ShopDialog.OnShopListener() {
                @Override
                public void onBuySuccess() {
                    refreshPlayerData();
                    showToast("购买成功");
                }

                @Override
                public void onBuyError(String msg) {
                    showToast(msg);
                }

                @Override
                public void onSellSuccess() {
                    refreshPlayerData();
                    showToast("出售成功");
                }

                @Override
                public void onSellError(String msg) {
                    showToast(msg);
                }
            });
        }
        if (mBankDialog == null) {
            mBankDialog = new BankDialog(mContext);
            mBankDialog.setOnClickListener(new BankDialog.OnClickListener() {
                @Override
                public void click(Dialog dialog, int tpye, String money) {
                    MyLog.i("tpye:" + tpye + "money:" + money);
                    String cash = mPlayer.getCash();
                    String deposit = mPlayer.getDeposit();
                    switch (tpye) {
                        case TYPE_SAVE_MONEY:
                            MyLog.i("" + TYPE_SAVE_MONEY);
                            mPlayer.setCash(MathUtil.subtract(cash, money));
                            mPlayer.setDeposit(MathUtil.add(deposit, money));
                            break;
                        case TYPE_GET_MONEY:
                            MyLog.i("" + TYPE_GET_MONEY);
                            mPlayer.setCash(MathUtil.add(cash, money));
                            mPlayer.setDeposit(MathUtil.subtract(deposit, money));
                            break;
                        default:
                            break;
                    }
                    refreshPlayerData();
                }
            });
        }
        if (mRentalDialog == null) {
            mRentalDialog = new MessageDialog();
            mRentalDialog.setTitle("房屋中介");
            mRentalDialog.setOnOkClickListener("租房", new MessageDialog.OnOkClickListener() {
                @Override
                public void onClick(View view) {
                    rentalHouse();
                }
            });
            mRentalDialog.setOnCancelClickListener("取消", new MessageDialog.OnCancelClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
        }
        if (mFinishDialog == null) {
            mFinishDialog = new MessageDialog();
            mFinishDialog.setCancelable(false);
            mFinishDialog.setTitle("游戏结束！");
            mFinishDialog.setOnOkClickListener("重来", new MessageDialog.OnOkClickListener() {
                @Override
                public void onClick(View view) {
                    restartGame();
                }
            });
            mFinishDialog.setOnCancelClickListener("退出", new MessageDialog.OnCancelClickListener() {
                @Override
                public void onClick(View view) {
                    quitGame();
                }
            });
        }
        if (mRepayDebtDialog == null) {
            mRepayDebtDialog = new MessageDialog();
            mRepayDebtDialog.setTitle("债务");
            mRepayDebtDialog.setOnOkClickListener("还债", new MessageDialog.OnOkClickListener() {
                @Override
                public void onClick(View view) {
                    toRepayDebt();
                }
            });
            mRepayDebtDialog.setOnCancelClickListener("取消", new MessageDialog.OnCancelClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
        }
        if (mHospitalDialog == null) {
            mHospitalDialog = new MessageDialog();
            mHospitalDialog.setTitle("医院");
            mHospitalDialog.setOnOkClickListener("治疗", new MessageDialog.OnOkClickListener() {
                @Override
                public void onClick(View view) {
                    toTreatment();
                }
            });
            mHospitalDialog.setOnCancelClickListener("取消", new MessageDialog.OnCancelClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
        }
        if (mRestartDialog == null) {
            mRestartDialog = new MessageDialog();
            mRestartDialog.setTitle("重新开始");
            mRestartDialog.setMessage("现在给你一次再来一次的机会，这将会清除所有数据，确定重来？");
            mRestartDialog.setOnOkClickListener("确定", new MessageDialog.OnOkClickListener() {
                @Override
                public void onClick(View view) {
                    restartGame();
                }
            });
            mRestartDialog.setOnCancelClickListener("取消", new MessageDialog.OnCancelClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
        }
        if (mQuitDialog == null) {
            mQuitDialog = new MessageDialog();
            mQuitDialog.setTitle("退出游戏");
            mQuitDialog.setMessage("确认退出游戏？（数据将被保存）");
            mQuitDialog.setOnOkClickListener("确定", new MessageDialog.OnOkClickListener() {
                @Override
                public void onClick(View view) {
                    quitGame();
                }
            });
            mQuitDialog.setOnCancelClickListener("取消", new MessageDialog.OnCancelClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
        }
        if (mHintDialog == null) {
            mHintDialog = new MessageDialog();
            mHintDialog.setOnOkClickListener("确定", new MessageDialog.OnOkClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
        }
        if (mEventDialog == null) {
            mEventDialog = new MessageDialog();
            mEventDialog.setOnOkClickListener("确定", new MessageDialog.OnOkClickListener() {
                @Override
                public void onClick(View view) {
                    dismissAllDialog();
                }
            });
            mEventDialog.setDismissListener(new MessageDialog.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {
                    if (MathUtil.le(mPlayer.getHealth(), "0")) {// 生命值小于等于0，游戏结束
                        showFinishDialog();
                    } else {
                        if (isShowNews) {
                            showNewsDialog();
                        } else {
                            radioGroup.check(R.id.rb_4);
                            showShopDialog(mPlayer.getCity());
                        }
                    }
                }
            });
        }
        if (mSelectEventDialog == null) {
            mSelectEventDialog = new MessageDialog();
            mSelectEventDialog.setCancelable(false);
        }
        if (mSelectResultDialog == null) {
            mSelectResultDialog = new MessageDialog();
            mSelectResultDialog.setOnOkClickListener("确定", new MessageDialog.OnOkClickListener() {
                @Override
                public void onClick(View view) {
                    dismissAllDialog();
                }
            });
            mSelectResultDialog.setDismissListener(new MessageDialog.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {
                    if (MathUtil.le(mPlayer.getHealth(), "0")) {// 生命值小于等于0，游戏结束
                        showFinishDialog();
                    } else {
                        if (isShowNews) {
                            showNewsDialog();
                        } else {
                            radioGroup.check(R.id.rb_4);
                            showShopDialog(mPlayer.getCity());
                        }
                    }
                }
            });
        }
        if (mNewsDialog == null) {
            mNewsDialog = new MessageDialog();
            mNewsDialog.setOnOkClickListener("确定", new MessageDialog.OnOkClickListener() {
                @Override
                public void onClick(View view) {
                    dismissAllDialog();
                }
            });
            mNewsDialog.setDismissListener(new MessageDialog.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {
                    if (MathUtil.le(mPlayer.getHealth(), "0")) {// 生命值小于等于0，游戏结束
                        showFinishDialog();
                    } else {
                        radioGroup.check(R.id.rb_4);
                        showShopDialog(mPlayer.getCity());
                    }
                }
            });
        }
    }

    /**
     * 显示游戏结束对话框
     */
    private void showFinishDialog() {
        String totalMoney = MathUtil.subtract(MathUtil.add(mPlayer.getCash(), mPlayer.getDeposit()), mPlayer.getDebt());// 现金 + 存款 - 负债
        String msg = "";
        MyLog.i("当前为第" + mPlayer.getWeek() + "周。\n总资产为：" + totalMoney);
        if (MathUtil.le(mPlayer.getHealth(), "0")) {
            msg += "英年早逝，不过十八年后又是一条好汉\n";
        }
        msg += "当前为第" + mPlayer.getWeek() + "周。\n总资产为：" + totalMoney + "\n游戏结束！";
        mFinishDialog.setMessage(msg);
        dismissAllDialog();
        mFinishDialog.show(getSupportFragmentManager(), "FinishDialog");
    }

    /**
     * 租房
     */
    private void rentalHouse() {
        String houseNum;
        String houseMoney;
        if (MathUtil.le(mPlayer.getHouseTotal(), CacheUtil.getInitGameHouseTotal(mContext))) {
            houseNum = "120";
            houseMoney = "800000";
        } else if (MathUtil.le(mPlayer.getHouseTotal(), "120")) {
            houseNum = "160";
            houseMoney = "1200000";
        } else if (MathUtil.le(mPlayer.getHouseTotal(), "160")) {
            houseNum = "200";
            houseMoney = "1800000";
        } else {
            houseNum = "240";
            houseMoney = "3600000";
        }
        if (MathUtil.lt(mPlayer.getHouseTotal(), "240")) {
            String cash = mPlayer.getCash();
            if (MathUtil.ge(cash, houseMoney)) {
                mPlayer.setCash(MathUtil.subtract(cash, houseMoney));
                mPlayer.setHouseTotal(houseNum);
                refreshPlayerData();
            } else {
                showToast("你带的现金不够！下次带好钱再过来吧！");
            }
        } else {
            showToast("土豪，您已经买光我们所有的房屋了！");
        }
    }

    private void restartGame() {
        PlayerUtil.deletePlayer();
        dismissAllDialog();
        PlayerUtil.initPlayerData(mContext);
        refreshPlayerData();
        checkIsStart();
    }

    /**
     * 归还债务
     */
    private void toRepayDebt() {
        dismissAllDialog();
        if (MathUtil.gt(mPlayer.getDebt(), "0")) {
            String debt = mPlayer.getDebt();
            String cash = mPlayer.getCash();
            if (MathUtil.lt(cash, debt)) {
                showToast("开什么玩笑，你的钱够吗？\n              来找抽是吧？");
                return;
            }
            mPlayer.setCash(MathUtil.subtract(cash, debt));
            mPlayer.setDebt("0");
            refreshPlayerData();
            mHintDialog.setTitle("债务");
            mHintDialog.setMessage("欠款" + debt + "元已还清，总算松了口气");
            mHintDialog.show(getSupportFragmentManager(), "hint");
        } else {
            showToast("你没欠我钱呀，兄弟这是打算请我去大宝剑？");
        }
    }

    /**
     * 去治疗
     */
    private void toTreatment() {
        dismissAllDialog();
        String newMoney = MathUtil.subtract(mPlayer.getCash(), getTreatmentMoney());
        if (MathUtil.lt(newMoney, "0")) {
            showToast("没钱治什么病！赶快走！");
            return;
        }
        if (MathUtil.eq(mPlayer.getHealth(), "100")) {
            showToast("             没病找抽是吧？\n你现在很健康，不需要治疗！");
            return;
        }
        mPlayer.setCash(newMoney);
        mPlayer.setHealth("100");
        refreshPlayerData();
        mHintDialog.setTitle("医院");
        mHintDialog.setMessage("你喝下了神仙姐姐的药水，体力全满，一口气可以上5层楼!");
        mHintDialog.show(getSupportFragmentManager(), "hint");
    }

    /**
     * 刷新 个人数据 同时刷新 展示界面
     */
    private void refreshPlayerData() {
        PlayerUtil.setPlayer(mPlayer);
        mPlayer = PlayerUtil.getPlayer();
        tvCash.setText(mPlayer.getCash() + "");
        tvDebt.setText(mPlayer.getDebt() + "");
        tvDeposit.setText(mPlayer.getDeposit() + "");
        tvHealth.setText(mPlayer.getHealth() + "");
        tvHouse.setText(mPlayer.getHouse() + "/" + mPlayer.getHouseTotal());
        tvWeek.setText(mPlayer.getWeek() + "/" + mPlayer.getWeekTotal());
    }

    @OnClick({R.id.btn_restart, R.id.btn_quit, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4, R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9, R.id.rb_1, R.id.rb_2, R.id.rb_3, R.id.rb_4, R.id.rb_5})
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.btn_restart:
                dismissAllDialog();
                mRestartDialog.show(getSupportFragmentManager(), "RestartDialog");
                break;
            case R.id.btn_quit:
                showQuitDialog();
                break;
            case R.id.btn1:// 选择城市
                selectCity(btn1.getText().toString());
                break;
            case R.id.btn2:
                selectCity(btn2.getText().toString());
                break;
            case R.id.btn3:
                selectCity(btn3.getText().toString());
                break;
            case R.id.btn4:
                selectCity(btn4.getText().toString());
                break;
            case R.id.btn5:
                selectCity(btn5.getText().toString());
                break;
            case R.id.btn6:
                selectCity(btn6.getText().toString());
                break;
            case R.id.btn7:
                selectCity(btn7.getText().toString());
                break;
            case R.id.btn8:
                selectCity(btn8.getText().toString());
                break;
            case R.id.btn9:
                selectCity(btn9.getText().toString());
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
                showShopDialog(mPlayer.getCity());
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

    private void showQuitDialog() {
        dismissAllDialog();
        mQuitDialog.show(getSupportFragmentManager(), "QuitDialog");
    }

    private void selectCity(String city) {
        if (city.equals(mPlayer.getCity())) {
            showToast("你现在就在" + city + "，换个地方逛逛吧");
        } else {
            if (checkWeek()) {
                String percent = MathUtil.divide(RandomUtil.getRandomNum(15, 13) + "", "10", 2);
                String debt = MathUtil.multiply(mPlayer.getDebt(), percent);
                mPlayer.setDebt(debt);// 设置当前负债，算法是 当前负债 *（1.3~1.5）倍，后期再优化利息的算法
                mPlayer.setCity(city);// 设置当前所在城市
                refreshPlayerData();
                mShopDialog.setShopGoodsList(ShopGoodsUtil.getShopGoodsList(GoodsUtil.getRandomList(CacheUtil.getInitGameShopGoodsNumber(mContext))));// 设置商店物品，仅有切换过城市，商店物品价格才会变化
                showSurpriseDialog();
            }
        }
    }

    private void dismissAllDialog() {
        if (mFinishDialog != null && mFinishDialog.isVisible()) {
            mFinishDialog.dismiss();
        }
        if (mRestartDialog != null && mRestartDialog.isVisible()) {
            mRestartDialog.dismiss();
        }
        if (mQuitDialog != null && mQuitDialog.isVisible()) {
            mQuitDialog.dismiss();
        }
        if (mHintDialog != null && mHintDialog.isVisible()) {
            mHintDialog.dismiss();
        }
        if (mNewsDialog != null && mNewsDialog.isVisible()) {
            mNewsDialog.dismiss();
        }
        if (mEventDialog != null && mEventDialog.isVisible()) {
            mEventDialog.dismiss();
        }
        if (mRepayDebtDialog != null && mRepayDebtDialog.isVisible()) {
            mRepayDebtDialog.dismiss();
        }
        if (mHospitalDialog != null && mHospitalDialog.isVisible()) {
            mHospitalDialog.dismiss();
        }
        if (mRentalDialog != null && mRentalDialog.isVisible()) {
            mRentalDialog.dismiss();
        }
        if (mBankDialog != null && mBankDialog.isShowing()) {
            mBankDialog.dismiss();
        }
        if (mShopDialog != null && mShopDialog.isVisible()) {
            mShopDialog.dismiss();
        }
    }

    /**
     * 在每次购买物品之后
     * 检查当前游戏周数，判断游戏是否结束
     *
     * @return 游戏是否结束，false结束，true未结束
     */
    private boolean checkWeek() {
        if (MathUtil.ge(mPlayer.getWeek(), mPlayer.getWeekTotal())) {
            showFinishDialog();
            return false;
        } else {
            mPlayer.setWeek(MathUtil.add(mPlayer.getWeek(), "1"));
            refreshPlayerData();
            return true;
        }
    }

    /**
     * 显示租房对话框
     */
    private void showRentalDialog() {
        dismissAllDialog();
        int houseNum;
        int houseMoney;
        if (MathUtil.le(mPlayer.getHouseTotal(), CacheUtil.getInitGameHouseTotal(mContext))) {
            houseNum = 120;
            houseMoney = 800000;
        } else if (MathUtil.le(mPlayer.getHouseTotal(), "120")) {
            houseNum = 160;
            houseMoney = 1200000;
        } else if (MathUtil.le(mPlayer.getHouseTotal(), "160")) {
            houseNum = 200;
            houseMoney = 1800000;
        } else {
            houseNum = 240;
            houseMoney = 3600000;
        }
        if (MathUtil.lt(mPlayer.getHouseTotal(), "240")) {
            mRentalDialog.setMessage("您当前房屋容量为" + mPlayer.getHouseTotal() + "。\n我们有一套容量为" + houseNum + "的新房要出租，价格是" + houseMoney + "元，您要租下这套房子吗？");
        } else {
            mRentalDialog.setMessage("我们这边已经没有房屋出租了哦！您当前已经是最大容量的豪宅了！");
        }
        mRentalDialog.show(getSupportFragmentManager(), "RentalDialog");
    }

    /**
     * 显示银行对话框
     */
    private void showBankDialog() {
        dismissAllDialog();
        mBankDialog.setCash(mPlayer.getCash());
        mBankDialog.setDeposit(mPlayer.getDeposit());
        mBankDialog.show();
    }

    private void showRepayDebtDialog() {
        dismissAllDialog();
        if (MathUtil.le(mPlayer.getDebt(), "0")) {
            mRepayDebtDialog.setMessage("兄弟，咱们已经两清了！有空一起去喝喝小酒，把把小妞，吹吹小曲啊！哈哈~");
            mRepayDebtDialog.show(getSupportFragmentManager(), "RepayDebtDialog");
        } else {
            mRepayDebtDialog.setMessage("你欠我的" + mPlayer.getDebt() + "元，今天能还不？");
            mRepayDebtDialog.show(getSupportFragmentManager(), "RepayDebtDialog");
        }
    }

    /**
     * 显示医院对话框
     */
    private void showHospitalDialog() {
        dismissAllDialog();
        if (MathUtil.eq(mPlayer.getHealth(), "100")) {
            mHospitalDialog.setMessage("你现在壮的跟头牛似得，不需要治疗！");
        } else {
            mHospitalDialog.setMessage("你当前的健康值" + mPlayer.getHealth() + "，治愈需要花费" + getTreatmentMoney() + "元，需要治疗么？");
        }
        mHospitalDialog.show(getSupportFragmentManager(), "HospitalDialog");
    }

    /**
     * 显示意外事件对话框
     */
    private void showSurpriseDialog() {
        dismissAllDialog();
        Event event;
        if (mPlayer.getIsFirst()) {// 是否新开始游戏，是的话则触发好事件
            mPlayer.setIsFirst(false);
            event = EventUtil.getRandomGoodEvent(true);// 获取好事件中随机的一个
        } else {
            event = EventUtil.getRandomEvent();// 获取所有事件中随机的一个
        }
        if (event != null) {
            if (event.getIsNeedSelect()) {// 需要选择判断的事件
                showSelectEventDialog(event);
            } else {// 不需要选择判断的事件
                showUnselectEventDialog(event);
            }
        } else {
            MyLog.i("突发事件为空");
        }
    }

    private void showSelectEventDialog(final Event event) {
        String title = "";
        String msg = "";
        if (!TextUtils.isEmpty(event.getTitle())) {
            title = event.getTitle();
        }
        if (!TextUtils.isEmpty(event.getMessage())) {
            msg = event.getMessage();
        }
        mSelectEventDialog.setOnOkClickListener(event.getBtnOk(), new MessageDialog.OnOkClickListener() {
            @Override
            public void onClick(View view) {
                showSelectResultDialog(true, event);

            }
        });
        mSelectEventDialog.setOnCancelClickListener(event.getBtnCancel(), new MessageDialog.OnCancelClickListener() {
            @Override
            public void onClick(View view) {
                showSelectResultDialog(false, event);
            }
        });
        mSelectEventDialog.setTitle(title);
        mSelectEventDialog.setMessage(Html.fromHtml(msg));
        mSelectEventDialog.show(getSupportFragmentManager(), "mSelectEventDialog");
    }

    private void showSelectResultDialog(boolean isOk, Event event) {
        boolean isGood = RandomUtil.getRandomNum(1, 0) == 0;// 选择结果 0 -> 好
        String title = "";
        String msg = "";
        if (isGood) {// 好事件
            if (isOk) {// 点击了确定
                if (!TextUtils.isEmpty(event.getResultOKGoodTitle())) {
                    title = event.getResultOKGoodTitle();
                }
                if (!TextUtils.isEmpty(event.getResultOKGoodMsg())) {
                    msg = event.getResultOKGoodMsg();
                }
            } else {// 点击了取消
                if (!TextUtils.isEmpty(event.getResultCancelGoodTitle())) {
                    title = event.getResultCancelGoodTitle();
                }
                if (!TextUtils.isEmpty(event.getResultCancelGoodMsg())) {
                    msg = event.getResultCancelGoodMsg();
                }
                msg = addEventMessage(true, event, msg);
            }
        } else {// 坏事件
            if (isOk) {// 点击了确定
                if (!TextUtils.isEmpty(event.getResultOKBadTitle())) {
                    title = event.getResultOKBadTitle();
                }
                if (!TextUtils.isEmpty(event.getResultOKBadMsg())) {
                    msg = event.getResultOKBadMsg();
                }
            } else {// 点击了取消
                if (!TextUtils.isEmpty(event.getResultCancelBadTitle())) {
                    title = event.getResultCancelBadTitle();
                }
                if (!TextUtils.isEmpty(event.getResultCancelBadMsg())) {
                    msg = event.getResultCancelBadMsg();
                }
            }
            msg = addEventMessage(false, event, msg);
        }
        refreshPlayerData();
        mSelectResultDialog.setTitle(title);
        mSelectResultDialog.setMessage(Html.fromHtml(msg));
        mSelectResultDialog.show(getSupportFragmentManager(), "mSelectResultDialog");
    }

    @NonNull
    private void showUnselectEventDialog(Event event) {
        String title = "";
        String msg = "";
        if (!TextUtils.isEmpty(event.getTitle())) {
            title = event.getTitle();
        }
        if (!TextUtils.isEmpty(event.getMessage())) {
            msg = event.getMessage();
        }
        msg = addEventMessage(null, event, msg);

        refreshPlayerData();
        mEventDialog.setTitle(title);
        mEventDialog.setMessage(Html.fromHtml(msg));
        mEventDialog.show(getSupportFragmentManager(), "EventDialog");
    }

    /**
     * 添加事件中，玩家获得的各种属性
     *
     * @param isGood
     * @param event
     * @param msg
     * @return
     */
    @NonNull
    private String addEventMessage(Boolean isGood, Event event, String msg) {
        msg += "<br/>";
        msg = addEventGoods(isGood, event, msg);
        msg = addEventCash(isGood, event, msg);
        msg = addEventDebt(isGood, event, msg);
        msg = addEventDeposit(isGood, event, msg);
        msg = addEventHealth(isGood, event, msg);
        return msg;
    }

    /**
     * 添加健康
     *
     * @param isGood
     * @param event
     * @param msg
     * @return
     */
    private String addEventHealth(Boolean isGood, Event event, String msg) {
        if (isGood == null) {// 非选择事件
            if (event.getHealth() != null && !TextUtils.isEmpty(event.getHealth().getNumber()) && !TextUtils.isEmpty(event.getHealth().getMaxPercent()) && !TextUtils.isEmpty(event.getHealth().getMinPercent())) {
                String tempHealth = RandomUtil.getRandomNum(event.getHealth());
                String resultHealth = MathUtil.add(mPlayer.getHealth(), tempHealth);
                if (MathUtil.lt(resultHealth, "0")) {// 身上健康不够扣
                    msg += "<br/><font color='#646464'>健康 <font color='#ff435f'>-</font>" + MathUtil.abs(mPlayer.getHealth()) + "</font>";
                    mPlayer.setHealth("0");
                } else {
                    if (MathUtil.ge(tempHealth, "0")) {
                        if (MathUtil.le(resultHealth, "100")) {
                            msg += "<br/><font color='#646464'>健康 <font color='#5da8ba'>+</font>" + MathUtil.abs(tempHealth) + "</font>";
                        } else {
                            resultHealth = "100";
                        }
                    } else {
                        msg += "<br/><font color='#646464'>健康 <font color='#ff435f'>-</font>" + MathUtil.abs(tempHealth) + "</font>";
                    }
                    mPlayer.setHealth(resultHealth);
                }
            }
        } else {// 选择事件
            if (isGood) {// 好结果
                if (event.getHealth() != null && !TextUtils.isEmpty(event.getHealth().getNumber()) && !TextUtils.isEmpty(event.getHealth().getMaxPercent()) && !TextUtils.isEmpty(event.getHealth().getMinPercent())) {
                    String tempHealth = MathUtil.abs(RandomUtil.getRandomNum(event.getHealth()));
                    msg += "<br/><font color='#646464'>健康 <font color='#5da8ba'>+</font>" + MathUtil.abs(tempHealth) + "</font>";
                    mPlayer.setHealth(MathUtil.add(mPlayer.getHealth(), tempHealth));
                }
            } else {// 坏结果
                String tempHealth = "-" + MathUtil.abs(RandomUtil.getRandomNum(event.getHealth()));
                String resultHealth = MathUtil.add(mPlayer.getHealth(), tempHealth);
                if (MathUtil.lt(resultHealth, "0")) {// 身上存款不够扣
                    msg += "<br/><font color='#646464'>健康 <font color='#ff435f'>-</font>" + MathUtil.abs(mPlayer.getHealth()) + "</font>";
                    mPlayer.setHealth("0");
                } else {
                    msg += "<br/><font color='#646464'>健康 <font color='#ff435f'>-</font>" + MathUtil.abs(tempHealth) + "</font>";
                    mPlayer.setHealth(resultHealth);
                }
            }
        }
        return msg;
    }

    @Override
    public void onBackPressed() {
        showQuitDialog();
    }

    /**
     * 添加存款
     *
     * @param isGood
     * @param event
     * @param msg
     * @return
     */
    private String addEventDeposit(Boolean isGood, Event event, String msg) {
        if (isGood == null) {// 非选择事件
            if (event.getDeposit() != null && !TextUtils.isEmpty(event.getDeposit().getNumber()) && !TextUtils.isEmpty(event.getDeposit().getMaxPercent()) && !TextUtils.isEmpty(event.getDeposit().getMinPercent())) {
                String tempDeposit = RandomUtil.getRandomNum(event.getDeposit());
                String resultDeposit = MathUtil.add(mPlayer.getDeposit(), tempDeposit);
                if (MathUtil.lt(resultDeposit, "0")) {// 身上存款不够扣
                    msg += "<br/><font color='#646464'>存款 <font color='#ff435f'>-</font>" + MathUtil.abs(mPlayer.getDeposit()) + "</font>";
                    mPlayer.setDeposit("0");
                } else {
                    if (MathUtil.ge(tempDeposit, "0")) {
                        msg += "<br/><font color='#646464'>存款 <font color='#5da8ba'>+</font>" + MathUtil.abs(tempDeposit) + "</font>";
                    } else {
                        msg += "<br/><font color='#646464'>存款 <font color='#ff435f'>-</font>" + MathUtil.abs(tempDeposit) + "</font>";
                    }
                    mPlayer.setDeposit(resultDeposit);
                }
            }
        } else {// 选择事件
            if (isGood) {// 好结果
                if (event.getDeposit() != null && !TextUtils.isEmpty(event.getDeposit().getNumber()) && !TextUtils.isEmpty(event.getDeposit().getMaxPercent()) && !TextUtils.isEmpty(event.getDeposit().getMinPercent())) {
                    String tempDeposit = MathUtil.abs(RandomUtil.getRandomNum(event.getDeposit()));
                    msg += "<br/><font color='#646464'>存款 <font color='#5da8ba'>+</font>" + MathUtil.abs(tempDeposit) + "</font>";
                    mPlayer.setDeposit(MathUtil.add(mPlayer.getDeposit(), tempDeposit));
                }
            } else {// 坏结果
                String tempDeposit = "-" + MathUtil.abs(RandomUtil.getRandomNum(event.getDeposit()));
                String resultDeposit = MathUtil.add(mPlayer.getDeposit(), tempDeposit);
                if (MathUtil.lt(resultDeposit, "0")) {// 身上存款不够扣
                    msg += "<br/><font color='#646464'>存款 <font color='#ff435f'>-</font>" + MathUtil.abs(mPlayer.getDeposit()) + "</font>";
                    mPlayer.setDeposit("0");
                } else {
                    msg += "<br/><font color='#646464'>存款 <font color='#ff435f'>-</font>" + MathUtil.abs(tempDeposit) + "</font>";
                    mPlayer.setDeposit(resultDeposit);
                }
            }
        }
        return msg;
    }

    /**
     * 添加负债
     *
     * @param isGood
     * @param event
     * @param msg
     * @return
     */
    private String addEventDebt(Boolean isGood, Event event, String msg) {
        if (isGood == null) {// 非选择事件
            if (event.getDebt() != null && !TextUtils.isEmpty(event.getDebt().getNumber()) && !TextUtils.isEmpty(event.getDebt().getMaxPercent()) && !TextUtils.isEmpty(event.getDebt().getMinPercent())) {
                String tempDebt = RandomUtil.getRandomNum(event.getDebt());
                String resultDebt = MathUtil.add(mPlayer.getDebt(), tempDebt);
                if (MathUtil.lt(resultDebt, "0")) {// 身上负债不够扣
                    msg += "<br/><font color='#646464'>负债 <font color='#ff435f'>-</font>" + MathUtil.abs(mPlayer.getDebt()) + "</font>";
                    mPlayer.setDebt("0");
                } else {
                    if (MathUtil.ge(tempDebt, "0")) {
                        msg += "<br/><font color='#646464'>负债 <font color='#5da8ba'>+</font>" + MathUtil.abs(tempDebt) + "</font>";
                    } else {
                        msg += "<br/><font color='#646464'>负债 <font color='#ff435f'>-</font>" + MathUtil.abs(tempDebt) + "</font>";
                    }
                    mPlayer.setDebt(resultDebt);
                }
            }
        } else {// 选择事件
            if (isGood) {// 好结果
                if (event.getDebt() != null && !TextUtils.isEmpty(event.getDebt().getNumber()) && !TextUtils.isEmpty(event.getDebt().getMaxPercent()) && !TextUtils.isEmpty(event.getDebt().getMinPercent())) {
                    String tempDebt = MathUtil.abs(RandomUtil.getRandomNum(event.getDebt()));
                    msg += "<br/><font color='#646464'>负债 <font color='#5da8ba'>+</font>" + MathUtil.abs(tempDebt) + "</font>";
                    mPlayer.setDebt(MathUtil.add(mPlayer.getDebt(), tempDebt));
                }
            } else {// 坏结果
                String tempDebt = "-" + MathUtil.abs(RandomUtil.getRandomNum(event.getDebt()));
                String resultDebt = MathUtil.add(mPlayer.getDebt(), tempDebt);
                if (MathUtil.lt(resultDebt, "0")) {// 身上负债不够扣
                    msg += "<br/><font color='#646464'>负债 <font color='#ff435f'>-</font>" + MathUtil.abs(mPlayer.getDebt()) + "</font>";
                    mPlayer.setDebt("0");
                } else {
                    msg += "<br/><font color='#646464'>负债 <font color='#ff435f'>-</font>" + MathUtil.abs(tempDebt) + "</font>";
                    mPlayer.setDebt(resultDebt);
                }
            }
        }
        return msg;
    }

    /**
     * 添加现金
     *
     * @param isGood
     * @param event
     * @param msg
     * @return
     */
    private String addEventCash(Boolean isGood, Event event, String msg) {
        if (isGood == null) {// 非选择事件
            if (event.getCash() != null && !TextUtils.isEmpty(event.getCash().getNumber()) && !TextUtils.isEmpty(event.getCash().getMaxPercent()) && !TextUtils.isEmpty(event.getCash().getMinPercent())) {
                String tempCash = RandomUtil.getRandomNum(event.getCash());
                String resultCash = MathUtil.add(mPlayer.getCash(), tempCash);
                if (MathUtil.lt(resultCash, "0")) {// 身上现金不够扣
                    msg += "<br/><font color='#646464'>现金 <font color='#ff435f'>-</font>" + MathUtil.abs(mPlayer.getCash()) + "</font>";
                    mPlayer.setCash("0");
                } else {
                    if (MathUtil.ge(tempCash, "0")) {
                        msg += "<br/><font color='#646464'>现金 <font color='#5da8ba'>+</font>" + MathUtil.abs(tempCash) + "</font>";
                    } else {
                        msg += "<br/><font color='#646464'>现金 <font color='#ff435f'>-</font>" + MathUtil.abs(tempCash) + "</font>";
                    }
                    mPlayer.setCash(resultCash);
                }
            }
        } else {// 选择事件
            if (isGood) {// 好结果
                if (event.getCash() != null && !TextUtils.isEmpty(event.getCash().getNumber()) && !TextUtils.isEmpty(event.getCash().getMaxPercent()) && !TextUtils.isEmpty(event.getCash().getMinPercent())) {
                    String tempCash = MathUtil.abs(RandomUtil.getRandomNum(event.getCash()));
                    msg += "<br/><font color='#646464'>现金 <font color='#5da8ba'>+</font>" + MathUtil.abs(tempCash) + "</font>";
                    mPlayer.setCash(MathUtil.add(mPlayer.getCash(), tempCash));
                }
            } else {// 坏结果
                String tempCash = "-" + MathUtil.abs(RandomUtil.getRandomNum(event.getCash()));
                String resultCash = MathUtil.add(mPlayer.getCash(), tempCash);
                if (MathUtil.lt(resultCash, "0")) {// 身上现金不够扣
                    msg += "<br/><font color='#646464'>现金 <font color='#ff435f'>-</font>" + MathUtil.abs(mPlayer.getCash()) + "</font>";
                    mPlayer.setCash("0");
                } else {
                    msg += "<br/><font color='#646464'>现金 <font color='#ff435f'>-</font>" + MathUtil.abs(tempCash) + "</font>";
                    mPlayer.setCash(resultCash);
                }
            }
        }
        return msg;
    }

    /**
     * 添加物品
     *
     * @param isGood
     * @param event
     * @param msg
     * @return
     */
    private String addEventGoods(Boolean isGood, Event event, String msg) {
        if (isGood == null || isGood) {// 非选择事件 或者 选择事件的好结果
            List<Goods> goodsList = event.getGoodGoodsList();
            String[] goodsNum = new String[goodsList.size()];
            String goodsTotalNum = "0";
            if (goodsList != null && goodsList.size() > 0) {
                for (int i = 0; i < goodsList.size(); i++) {
                    // 随机获得1到5件物品
                    goodsNum[i] = RandomUtil.getRandomNum(5, 1) + "";
                    goodsTotalNum = MathUtil.add(goodsTotalNum, goodsNum[i]);
                }
                for (int i = 0; i < goodsList.size(); i++) {
                    if (i == 0) {
                        msg += "<br/><font color='#ff435f'>获得：</font><br/>";
                    }
                    msg += "<font color='#646464'>" + goodsList.get(i).getName() + " x" + goodsNum[i] + goodsList.get(i).getUnit() + "</font><br/>";

                    if (MathUtil.le(goodsTotalNum, MathUtil.subtract(mPlayer.getHouseTotal(), mPlayer.getHouse()))) {// 房间有足够空间放下赠品
                        PlayerGoodsUtil.addPlayGoods(goodsList.get(i), goodsNum[i]);
                    }
                }
                if (MathUtil.gt(goodsTotalNum, MathUtil.subtract(mPlayer.getHouseTotal(), mPlayer.getHouse()))) {// 房间放不下赠品
                    msg += "<br/><font color='#ff435f'>但是你的房间已经放不下了，只能把这些东西丢在路边了</font><br/>";
                }
            }
        } else {// 选择事件坏结果
            List<Goods> goodsList = event.getBadGoodsList();
            String[] goodsNum = new String[goodsList.size()];
            String goodsTotalNum = "0";
            if (goodsList != null && goodsList.size() > 0) {
                String[] goodsName = new String[goodsList.size()];
                String[] goodsUnit = new String[goodsList.size()];
                for (int i = 0; i < goodsList.size(); i++) {
                    PlayerGoods playGoods = PlayerGoodsUtil.getPlayerGoods(goodsList.get(i).getName());
                    if (playGoods != null) {
                        goodsNum[i] = playGoods.getNumber();
                        goodsName[i] = playGoods.getName();
                        goodsUnit[i] = goodsList.get(i).getUnit();
                        goodsTotalNum = MathUtil.add(goodsTotalNum, goodsNum[i]);
                        PlayerGoodsUtil.delete(playGoods);
                    }
                }
                if (MathUtil.gt(goodsTotalNum, "0")) {
                    msg += "<br/><font color='#ff435f'>损失：</font><br/>";
                    for (int i = 0; i < goodsList.size(); i++) {
                        if (!TextUtils.isEmpty(goodsNum[i])) {
                            msg += "<font color='#646464'>" + goodsName[i] + " x" + goodsNum[i] + goodsUnit[i] + "</font><br/>";
                        }
                    }
                }
            }
        }
        return msg;
    }

    /**
     * 显示新闻对话框
     */
    private void showNewsDialog() {
        dismissAllDialog();
        mNewsDialog.setTitle("《民生观察》");
        mNewsDialog.setMessage("今日发生多起恶犬伤人事件，请市民自购装备，注意自身安全！");
        mNewsDialog.show(getSupportFragmentManager(), "NewsDialog");
    }

    /**
     * 显示买卖物品对话框
     */
    private void showShopDialog(String city) {
        dismissAllDialog();
        mShopDialog.setCity(city);
        mShopDialog.show(getSupportFragmentManager(), "ShopDialog");
    }

    /**
     * 检查是否选择了第一个城市并开始游戏
     */
    private boolean checkIsStart() {
        dismissAllDialog();
        if (mPlayer.getIsFirst()) {
            mHintDialog.setTitle("选择地区");
            mHintDialog.setMessage("选择一个地区开始吧");
            mHintDialog.show(getSupportFragmentManager(), "hint");
            return false;
        }
        return true;
    }

    public boolean isShowNews() {
        return isShowNews;
    }

    public void setShowNews(boolean showNews) {
        isShowNews = showNews;
    }

    /**
     * @return 当前治疗的费用
     */
    public String getTreatmentMoney() {
        if (MathUtil.gt(mPlayer.getHealth(), "0") && MathUtil.lt(mPlayer.getHealth(), "100")) {
            String treatmentMoney = "2000";
            String percent = MathUtil.divide(MathUtil.subtract("1", mPlayer.getHealth()), "100", 2);// 需治疗的生命占总生命 百分比
            return MathUtil.multiply(percent, treatmentMoney);
        }
        return "0";
    }
}
