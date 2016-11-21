package me.sheepyang.onlylive.activity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.sheepyang.onlylive.R;
import me.sheepyang.onlylive.app.Constants;
import me.sheepyang.onlylive.entity.Player;
import me.sheepyang.onlylive.utils.MathUtil;
import me.sheepyang.onlylive.utils.MyLog;
import me.sheepyang.onlylive.utils.RandomUtil;
import me.sheepyang.onlylive.utils.data.GoodsUtil;
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
    private MessageDialog mRepayDebtDialog;// 还债对话框
    private MessageDialog mHospitalDialog;// 医院治疗对话框
    private MessageDialog mRentalDialog;// 租房对话框
    private BankDialog mBankDialog;// 银行对话框
    private ShopDialog mShopDialog;// 交易对话框

    private Player mPlayer;
    private String mCity = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
                rb1.setTextColor(getResources().getColor(R.color.word_black));
                rb2.setTextColor(getResources().getColor(R.color.word_black));
                rb3.setTextColor(getResources().getColor(R.color.word_black));
                rb4.setTextColor(getResources().getColor(R.color.word_black));
                rb5.setTextColor(getResources().getColor(R.color.word_black));
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
        onBackPressed();
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
                    PlayerUtil.setPlayer(mPlayer);
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
                    showNewsDialog();
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
                    radioGroup.check(R.id.rb_4);
                    showShopDialog(mPlayer.getCity());
                }
            });
        }
    }

    /**
     * 租房
     */
    private void rentalHouse() {
        String houseNum;
        String houseMoney;
        if (MathUtil.le(mPlayer.getHouseTotal(), Constants.INIT_GAME_HOUSE_TOTAL)) {
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
                PlayerUtil.setPlayer(mPlayer);
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
        PlayerUtil.initPlayerData();
        refreshPlayerData();
        checkIsStart();
    }

    /**
     * 归还债务
     */
    private void toRepayDebt() {
        if (MathUtil.gt(mPlayer.getDebt(), "0")) {
            String debt = mPlayer.getDebt();
            String cash = mPlayer.getCash();
            if (MathUtil.lt(cash, debt)) {
                showToast("开什么玩笑，你的钱够吗？\n              来找抽是吧？");
                return;
            }
            mPlayer.setCash(MathUtil.subtract(cash, debt));
            mPlayer.setDebt("0");
            PlayerUtil.setPlayer(mPlayer);
            refreshPlayerData();
            mHintDialog.setTitle("债务");
            mHintDialog.setMessage("欠款" + debt + "元已还清，总算松了口气");
            dismissAllDialog();
            mHintDialog.show(getSupportFragmentManager(), "hint");
        } else {
            showToast("你没欠我钱呀，兄弟这是打算请我去大宝剑？");
        }
    }

    /**
     * 去治疗
     */
    private void toTreatment() {
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
        PlayerUtil.setPlayer(mPlayer);
        refreshPlayerData();
        mHintDialog.setTitle("医院");
        mHintDialog.setMessage("你喝下了神仙姐姐的药水，体力全满，一口气可以上5层楼!");
        dismissAllDialog();
        mHintDialog.show(getSupportFragmentManager(), "hint");
    }

    /**
     * 刷新 个人数据 同时刷新 展示界面
     */
    private void refreshPlayerData() {
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
                dismissAllDialog();
                mQuitDialog.show(getSupportFragmentManager(), "QuitDialog");
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

    private void selectCity(String city) {
        mCity = city;
        if (city.equals(mPlayer.getCity())) {
            showToast("你现在就在" + city + "，换个地方逛逛吧");
            return;
        } else {
            boolean isFirst = true;
            if (mPlayer.getIsFirst()) {
                isFirst = false;
                mPlayer.setIsFirst(isFirst);
            }
            if (checkWeek()) {
                String percent = MathUtil.divide(RandomUtil.getRandomNum(15, 11) + "", "10", 2);
                String debt = MathUtil.multiply(mPlayer.getDebt(), percent);
                mPlayer.setDebt(debt);// 设置当前负债，算法是 当前负债 *（1.1~1.5）倍，后期再优化利息的算法
                mPlayer.setCity(city);// 设置当前所在城市
                PlayerUtil.setPlayer(mPlayer);
                refreshPlayerData();
                mShopDialog.setShopGoodsList(ShopGoodsUtil.getShopGoodsList(GoodsUtil.getRandomList(20)));// 设置商店物品，仅有切换过城市，商店物品价格才会变化
                showSurpriseDialog(isFirst);
            }
        }
    }

    private void dismissAllDialog() {
        if (mFinishDialog.isVisible()) {
            mFinishDialog.dismiss();
        }
        if (mRestartDialog.isVisible()) {
            mRestartDialog.dismiss();
        }
        if (mQuitDialog.isVisible()) {
            mQuitDialog.dismiss();
        }
        if (mHintDialog.isVisible()) {
            mHintDialog.dismiss();
        }
        if (mNewsDialog.isVisible()) {
            mNewsDialog.dismiss();
        }
        if (mEventDialog.isVisible()) {
            mEventDialog.dismiss();
        }
        if (mRepayDebtDialog.isVisible()) {
            mRepayDebtDialog.dismiss();
        }
        if (mHospitalDialog.isVisible()) {
            mHospitalDialog.dismiss();
        }
        if (mRentalDialog.isVisible()) {
            mRentalDialog.dismiss();
        }
        if (mBankDialog.isShowing()) {
            mBankDialog.dismiss();
        }
        if (mShopDialog.isVisible()) {
            mShopDialog.dismiss();
        }
    }

    /**
     * 在每次购买物品之后
     * 检查当前游戏周数，判断游戏是否结束
     *
     * @return
     */
    private boolean checkWeek() {
        String totalMoney = MathUtil.subtract(MathUtil.add(mPlayer.getCash(), mPlayer.getDeposit()), mPlayer.getDebt());// 现金 + 存款 - 负债
        MyLog.i("当前为第" + mPlayer.getWeek() + "周。\n总资产为：" + totalMoney);
        if (MathUtil.ge(mPlayer.getWeek(), mPlayer.getWeekTotal())) {
            mFinishDialog.setMessage("当前为第" + mPlayer.getWeek() + "周。\n总资产为：" + totalMoney + "\n游戏结束！");
            dismissAllDialog();
            mFinishDialog.show(getSupportFragmentManager(), "FinishDialog");
            return false;
        } else {
            mPlayer.setWeek(MathUtil.add(mPlayer.getWeek(), "1"));
            PlayerUtil.setPlayer(mPlayer);
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
        if (MathUtil.le(mPlayer.getHouseTotal(), Constants.INIT_GAME_HOUSE_TOTAL)) {
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
     *
     * @param isFirst 是否新开始游戏，是的话则触发好事件
     */
    private void showSurpriseDialog(boolean isFirst) {
        dismissAllDialog();
//        showPDialog();
//        Event event;
//        if (isFirst) {
//            event = EventUtil.getRandomEvent(true);// 获取好事件中随机的一个
//        } else {
//            event = EventUtil.getRandomEvent(null);// 获取所有事件中随机的一个
//        }
//        if (event != null) {
//            String msg = event.getMessage();
//            List<EventGoods> eventGoodsList = event.getEventGoodsList();
//            if (eventGoodsList != null && eventGoodsList.size() > 0) {
//                for (int i = 0; i < eventGoodsList.size(); i++) {
//                    EventGoods eventGoods = eventGoodsList.get(i);
//                    msg = StrUtil.replaceGoodsChar(i, msg, eventGoods);// 替换掉字符串中的占位符
//                }
//            }
//            if (event.getMoney() != null) {
//                msg = StrUtil.replaceMoneyChar(msg, event.getMoney());// 替换掉字符串中的占位符
//            }
//            if (event.getIsSelect()) {// 该事件需要进行选择
//                mEventDialog.setOnOkClickListener(event.getSelectYes(), new MessageDialog.OnOkClickListener() {
//                    @Override
//                    public void onClick(View view) {
//
//                    }
//                });
//                mEventDialog.setOnCancelClickListener(event.getSelectNo(), new MessageDialog.OnCancelClickListener() {
//                    @Override
//                    public void onClick(View view) {
//
//                    }
//                });
//            }
        mEventDialog.setTitle("突发事件标题");
        mEventDialog.setMessage("突发事件内容");
//        dismissPDialog();
        mEventDialog.show(getSupportFragmentManager(), "EventDialog");
//        }
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
        if (mPlayer.getIsFirst() == true) {
            mHintDialog.setTitle("选择地区");
            mHintDialog.setMessage("选择一个地区开始吧");
            mHintDialog.show(getSupportFragmentManager(), "hint");
            return false;
        }
        return true;
    }

    /**
     * 获得当前治疗的费用
     *
     * @return
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
