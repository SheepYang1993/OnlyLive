package me.sheepyang.onlylive.widget.dialog;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.sheepyang.onlylive.R;
import me.sheepyang.onlylive.adapter.GoodsAdapter;
import me.sheepyang.onlylive.entity.Event;
import me.sheepyang.onlylive.entity.Goods;
import me.sheepyang.onlylive.entity.Number;
import me.sheepyang.onlylive.utils.AppUtil;
import me.sheepyang.onlylive.utils.MathUtil;
import me.sheepyang.onlylive.utils.MyToast;
import me.sheepyang.onlylive.utils.data.EventUtil;
import me.sheepyang.onlylive.utils.data.JoinBadGoodsToEventUtil;
import me.sheepyang.onlylive.utils.data.JoinGoodGoodsToEventUtil;
import me.sheepyang.onlylive.utils.data.NumberUtil;
import me.sheepyang.onlylive.widget.MarqueeTextView;
import me.sheepyang.onlylive.widget.recyclerview.NoAlphaItemAnimator;

/**
 * Created by SheepYang on 2016/11/26 11:15.
 */

public class EventDialog extends BaseDialogFragment implements View.OnClickListener {
    @BindView(R.id.cb_is_good)
    CheckBox cbIsGood;

    @BindView(R.id.ll_select)
    LinearLayout llSelect;
    @BindView(R.id.cb_is_need_select)
    CheckBox cbIsNeedSelect;

    @BindView(R.id.cb_is_need_cash)
    CheckBox cbIsNeedCash;
    @BindView(R.id.ll_cash)
    LinearLayout llCash;

    @BindView(R.id.cb_is_need_debt)
    CheckBox cbIsNeedDebt;
    @BindView(R.id.ll_debt)
    LinearLayout llDebt;

    @BindView(R.id.cb_is_need_deposit)
    CheckBox cbIsNeedDeposit;
    @BindView(R.id.ll_deposit)
    LinearLayout llDeposit;

    @BindView(R.id.cb_is_need_health)
    CheckBox cbIsNeedHealth;
    @BindView(R.id.ll_health)
    LinearLayout llHealth;
    @BindView(R.id.edt_title)
    EditText edtTitle;
    @BindView(R.id.edt_message)
    EditText edtMessage;
    @BindView(R.id.edt_ok_text)
    EditText edtOkText;
    @BindView(R.id.edt_cancel_text)
    EditText edtCancelText;
    @BindView(R.id.edt_ok_good_title)
    EditText edtOkGoodTitle;
    @BindView(R.id.edt_ok_good_msg)
    EditText edtOkGoodMsg;
    @BindView(R.id.edt_cancel_good_title)
    EditText edtCancelGoodTitle;
    @BindView(R.id.edt_cancel_good_msg)
    EditText edtCancelGoodMsg;
    @BindView(R.id.edt_ok_bad_title)
    EditText edtOkBadTitle;
    @BindView(R.id.edt_ok_bad_msg)
    EditText edtOkBadMsg;
    @BindView(R.id.edt_cancel_bad_title)
    EditText edtCancelBadTitle;
    @BindView(R.id.edt_cancel_bad_msg)
    EditText edtCancelBadMsg;

    @BindView(R.id.edt_price)
    EditText edtPrice;
    @BindView(R.id.edt_price_max_percent)
    EditText edtPriceMaxPercent;
    @BindView(R.id.edt_price_min_percent)
    EditText edtPriceMinPercent;

    @BindView(R.id.edt_debt)
    EditText edtDebt;
    @BindView(R.id.edt_debt_max_percent)
    EditText edtDebtMaxPercent;
    @BindView(R.id.edt_debt_min_percent)
    EditText edtDebtMinPercent;

    @BindView(R.id.edt_deposit)
    EditText edtDeposit;
    @BindView(R.id.edt_deposit_max_percent)
    EditText edtDepositMaxPercent;
    @BindView(R.id.edt_deposit_min_percent)
    EditText edtDepositMinPercent;

    @BindView(R.id.edt_health)
    EditText edtHealth;
    @BindView(R.id.edt_health_max_percent)
    EditText edtHealthMaxPercent;
    @BindView(R.id.edt_health_min_percent)
    EditText edtHealthMinPercent;
    @BindView(R.id.tv_title)
    MarqueeTextView tvTitle;
    @BindView(R.id.tv_hint)
    TextView tvHint;
    @BindView(R.id.recycler_view_good_goods)
    RecyclerView recyclerViewGoodGoods;
    @BindView(R.id.recycler_view_bad_goods)
    RecyclerView recyclerViewBadGoods;
    @BindView(R.id.btn_add_good_goods)
    Button btnAddGoodGoods;
    @BindView(R.id.ll_bad_goods)
    LinearLayout llBadGoods;
    private SaveListener mSaveListener;

    private String mHint;
    private String mTitle;
    private Event mEvent;

    private GoodsAdapter mGoodAdapter;
    private MessageDialog mGoodDeleteDialog;
    private GoodsListDialog mGoodGoodsListDialog;
    private List<Goods> mGoodDatas;
    private Goods mGoodGoods;

    private GoodsAdapter mBadAdapter;
    private MessageDialog mBadDeleteDialog;
    private GoodsListDialog mBadGoodsListDialog;
    private List<Goods> mBadDatas;
    private Goods mBadGoods;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.dialog_event, null);
        ButterKnife.bind(this, view);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));// 设置背景透明
        initView();
        initListener();
        return view;
    }

    private void initView() {
        mGoodDeleteDialog = new MessageDialog();
        mGoodDeleteDialog.setTitle("删除物品");
        mBadDeleteDialog = new MessageDialog();
        mBadDeleteDialog.setTitle("删除物品");
        mGoodGoodsListDialog = new GoodsListDialog();
        //设置布局管理器
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(getActivity());
        linearLayoutManager1.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewGoodGoods.setLayoutManager(linearLayoutManager1);
        // 解决notifyItem闪烁的问题
        recyclerViewGoodGoods.setItemAnimator(new NoAlphaItemAnimator());
        //设置适配器
        mGoodAdapter = new GoodsAdapter(getActivity(), mGoodDatas);
        recyclerViewGoodGoods.setAdapter(mGoodAdapter);

        mBadGoodsListDialog = new GoodsListDialog();
        //设置布局管理器
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(getActivity());
        linearLayoutManager2.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewBadGoods.setLayoutManager(linearLayoutManager2);
        // 解决notifyItem闪烁的问题
        recyclerViewBadGoods.setItemAnimator(new NoAlphaItemAnimator());
        //设置适配器
        mBadAdapter = new GoodsAdapter(getActivity(), mBadDatas);
        recyclerViewBadGoods.setAdapter(mBadAdapter);
    }

    private void initListener() {
        mGoodDeleteDialog.setOnOkClickListener("删除", new MessageDialog.OnOkClickListener() {
            @Override
            public void onClick(View view) {
                deleteGoodGoods();
            }
        });
        mGoodDeleteDialog.setOnCancelClickListener("取消", new MessageDialog.OnCancelClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        mBadDeleteDialog.setOnOkClickListener("删除", new MessageDialog.OnOkClickListener() {
            @Override
            public void onClick(View view) {
                deleteBadGoods();
            }
        });
        mBadDeleteDialog.setOnCancelClickListener("取消", new MessageDialog.OnCancelClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        mGoodAdapter.setOnItemClickListener(new GoodsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                MyToast.showMessage(getActivity(), "长按删除");
            }
        });
        mGoodAdapter.setOnItemLongClickListener(new GoodsAdapter.OnItemLongClickListener() {
            @Override
            public void onItemLongClick(View view, int position) {
                mGoodGoods = mGoodDatas.get(position);
                mGoodDeleteDialog.setMessage(Html.fromHtml("<br/>确定要删除 <font color='#ff435f'>" + mGoodGoods.getName() + "</font> 吗？<br/>"));
                mGoodDeleteDialog.show(getChildFragmentManager(), "DeleteDialog");
            }
        });
        mGoodGoodsListDialog.setOnSelectListener(new GoodsListDialog.OnSelectListener() {
            @Override
            public void onSelect(Goods goods, int position) {
                if (goods != null) {
                    if (mGoodDatas.contains(goods)) {
                        MyToast.showMessage(getActivity(), goods.getName() + " 已经添加了");
                    } else {
                        mGoodDatas.add(goods);
                        mGoodAdapter.update(mGoodDatas);
                    }
                }
            }
        });
        mBadAdapter.setOnItemClickListener(new GoodsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                MyToast.showMessage(getActivity(), "长按删除");
            }
        });
        mBadAdapter.setOnItemLongClickListener(new GoodsAdapter.OnItemLongClickListener() {
            @Override
            public void onItemLongClick(View view, int position) {
                mBadGoods = mBadDatas.get(position);
                mBadDeleteDialog.setMessage(Html.fromHtml("<br/>确定要删除 <font color='#ff435f'>" + mBadGoods.getName() + "</font> 吗？<br/>"));
                mBadDeleteDialog.show(getChildFragmentManager(), "DeleteDialog");
            }
        });
        mBadGoodsListDialog.setOnSelectListener(new GoodsListDialog.OnSelectListener() {
            @Override
            public void onSelect(Goods goods, int position) {
                if (goods != null) {
                    if (mBadDatas.contains(goods)) {
                        MyToast.showMessage(getActivity(), goods.getName() + " 已经添加了");
                    } else {
                        mBadDatas.add(goods);
                        mBadAdapter.update(mBadDatas);
                    }
                }
            }
        });
        cbIsNeedSelect.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (llSelect.getVisibility() == View.GONE) {
                    animateOpen(llSelect, AppUtil.dip2px(getActivity(), 400));
                    btnAddGoodGoods.setText("添加好结果物品");
                    llBadGoods.setVisibility(View.VISIBLE);
                } else {
                    animateClose(llSelect);
                    btnAddGoodGoods.setText("添加物品");
                    llBadGoods.setVisibility(View.GONE);
                }
            }
        });
        cbIsNeedCash.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (llCash.getVisibility() == View.GONE) {
                    animateOpen(llCash, AppUtil.dip2px(getActivity(), 135));
                } else {
                    animateClose(llCash);
                }
            }
        });
        cbIsNeedDebt.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (llDebt.getVisibility() == View.GONE) {
                    animateOpen(llDebt, AppUtil.dip2px(getActivity(), 135));
                } else {
                    animateClose(llDebt);
                }
            }
        });
        cbIsNeedDeposit.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (llDeposit.getVisibility() == View.GONE) {
                    animateOpen(llDeposit, AppUtil.dip2px(getActivity(), 135));
                } else {
                    animateClose(llDeposit);
                }
            }
        });
        cbIsNeedHealth.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (llHealth.getVisibility() == View.GONE) {
                    animateOpen(llHealth, AppUtil.dip2px(getActivity(), 135));
                } else {
                    animateClose(llHealth);
                }
            }
        });
    }

    private void deleteGoodGoods() {
        if (mGoodGoods != null) {
            mGoodDatas.remove(mGoodGoods);
            mGoodGoods = null;
            mGoodAdapter.update(mGoodDatas);
        }
    }

    private void deleteBadGoods() {
        if (mBadGoods != null) {
            mBadDatas.remove(mBadGoods);
            mBadGoods = null;
            mBadAdapter.update(mBadDatas);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        initData();
    }

    private void initData() {
        mGoodDatas = new ArrayList<>();
        mBadDatas = new ArrayList<>();
        if (!TextUtils.isEmpty(mTitle)) {
            tvTitle.setText(mTitle);
        }
        if (!TextUtils.isEmpty(mHint)) {
            tvHint.setText(mHint);
        }

        if (mEvent != null) {
            edtTitle.setText(mEvent.getTitle());
            edtMessage.setText(mEvent.getMessage());

            Number cash = mEvent.getCash();
            Number debt = mEvent.getDebt();
            Number deposit = mEvent.getDeposit();
            Number health = mEvent.getHealth();

            if (mEvent.getIsNeedSelect()) {
                if (!TextUtils.isEmpty(mEvent.getBtnOk())) {
                    edtOkText.setText(mEvent.getBtnOk());
                }
                if (!TextUtils.isEmpty(mEvent.getBtnCancel())) {
                    edtCancelText.setText(mEvent.getBtnCancel());
                }

                edtOkGoodTitle.setText(mEvent.getResultOKGoodTitle());
                edtOkGoodMsg.setText(mEvent.getResultOKGoodMsg());
                edtCancelGoodTitle.setText(mEvent.getResultCancelGoodTitle());
                edtCancelGoodMsg.setText(mEvent.getResultCancelGoodMsg());

                edtOkBadTitle.setText(mEvent.getResultOKBadTitle());
                edtOkBadMsg.setText(mEvent.getResultOKBadMsg());
                edtCancelBadTitle.setText(mEvent.getResultCancelBadTitle());
                edtCancelBadMsg.setText(mEvent.getResultCancelBadMsg());

                if (mEvent.getBadGoodsList() != null && mEvent.getBadGoodsList().size() > 0) {
                    for (Goods goods :
                            mEvent.getBadGoodsList()) {
                        mBadDatas.add(goods);
                    }
                }
            } else {
                edtOkText.setText("");
                edtCancelText.setText("");

                edtOkGoodTitle.setText("");
                edtOkGoodMsg.setText("");
                edtCancelGoodTitle.setText("");
                edtCancelGoodMsg.setText("");

                edtOkBadTitle.setText("");
                edtOkBadMsg.setText("");
                edtCancelBadTitle.setText("");
                edtCancelBadMsg.setText("");
            }
            cbIsNeedSelect.setChecked(mEvent.getIsNeedSelect());

            if (cash != null) {
                edtPrice.setText(cash.getNumber());
                edtPriceMaxPercent.setText(MathUtil.multiply("100", cash.getMaxPercent()));
                edtPriceMinPercent.setText(MathUtil.multiply("100", cash.getMinPercent()));
                cbIsNeedCash.setChecked(true);
            } else {
                cbIsNeedCash.setChecked(false);
            }

            if (debt != null) {
                edtDebt.setText(debt.getNumber());
                edtDebtMaxPercent.setText(MathUtil.multiply("100", debt.getMaxPercent()));
                edtDebtMinPercent.setText(MathUtil.multiply("100", debt.getMinPercent()));
                cbIsNeedDebt.setChecked(true);
            } else {
                cbIsNeedDebt.setChecked(false);
            }

            if (deposit != null) {
                edtDeposit.setText(deposit.getNumber());
                edtDepositMaxPercent.setText(MathUtil.multiply("100", deposit.getMaxPercent()));
                edtDepositMinPercent.setText(MathUtil.multiply("100", deposit.getMinPercent()));
                cbIsNeedDeposit.setChecked(true);
            } else {
                cbIsNeedDeposit.setChecked(false);
            }

            if (health != null) {
                edtHealth.setText(health.getNumber());
                edtHealthMaxPercent.setText(MathUtil.multiply("100", health.getMaxPercent()));
                edtHealthMinPercent.setText(MathUtil.multiply("100", health.getMinPercent()));
                cbIsNeedHealth.setChecked(true);
            } else {
                cbIsNeedHealth.setChecked(false);
            }

            cbIsGood.setChecked(mEvent.getIsGood());
            cbIsNeedSelect.setChecked(mEvent.getIsNeedSelect());

            if (mEvent.getGoodGoodsList() != null && mEvent.getGoodGoodsList().size() > 0) {
                for (Goods goods :
                        mEvent.getGoodGoodsList()) {
                    mGoodDatas.add(goods);
                }
            }
        } else {
            edtTitle.setText("");
            edtMessage.setText("");

            edtOkText.setText("");
            edtCancelText.setText("");

            edtOkGoodTitle.setText("");
            edtOkGoodMsg.setText("");
            edtCancelGoodTitle.setText("");
            edtCancelGoodMsg.setText("");

            edtOkBadTitle.setText("");
            edtOkBadMsg.setText("");
            edtCancelBadTitle.setText("");
            edtCancelBadMsg.setText("");

            edtPrice.setText("");
            edtPriceMaxPercent.setText("");
            edtPriceMinPercent.setText("");

            edtDebt.setText("");
            edtDebtMaxPercent.setText("");
            edtDebtMinPercent.setText("");

            edtDeposit.setText("");
            edtDepositMaxPercent.setText("");
            edtDepositMinPercent.setText("");

            edtHealth.setText("");
            edtHealthMaxPercent.setText("");
            edtHealthMinPercent.setText("");

            cbIsGood.setChecked(false);
            cbIsNeedSelect.setChecked(false);
            cbIsNeedCash.setChecked(false);
            cbIsNeedDebt.setChecked(false);
            cbIsNeedDeposit.setChecked(false);
            cbIsNeedHealth.setChecked(false);
        }
        mGoodAdapter.update(mGoodDatas);
        mBadAdapter.update(mBadDatas);
    }

    public void setEvent(Event event) {
        mEvent = event;
    }

    @Override
    @OnClick({R.id.btn_add_good_goods, R.id.btn_add_bad_goods, R.id.btn_cancel, R.id.btn_ok, R.id.ll_is_need_select, R.id.ll_is_need_cash, R.id.ll_is_need_debt, R.id.ll_is_need_deposit, R.id.ll_is_need_health, R.id.ll_is_good})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_ok:
                save();
                break;
            case R.id.btn_cancel:
                dismiss();
                break;
            case R.id.btn_add_good_goods:
                mGoodGoodsListDialog.show(getChildFragmentManager(), "GoodGoodsListDialog");
                break;
            case R.id.btn_add_bad_goods:
                mBadGoodsListDialog.show(getChildFragmentManager(), "BadGoodsListDialog");
                break;
            case R.id.ll_is_need_select:
                cbIsNeedSelect.setChecked(!cbIsNeedSelect.isChecked());
                break;
            case R.id.ll_is_need_cash:
                cbIsNeedCash.setChecked(!cbIsNeedCash.isChecked());
                break;
            case R.id.ll_is_need_debt:
                cbIsNeedDebt.setChecked(!cbIsNeedDebt.isChecked());
                break;
            case R.id.ll_is_need_deposit:
                cbIsNeedDeposit.setChecked(!cbIsNeedDeposit.isChecked());
                break;
            case R.id.ll_is_need_health:
                cbIsNeedHealth.setChecked(!cbIsNeedHealth.isChecked());
                break;
            case R.id.ll_is_good:
                cbIsGood.setChecked(!cbIsGood.isChecked());
                break;
            default:
                break;
        }
    }

    private void save() {
        EventUtil.Builder builder = new EventUtil.Builder();
        String title = edtTitle.getText().toString();
        String message = edtMessage.getText().toString();
        if (TextUtils.isEmpty(title)) {
            MyToast.showMessage(getActivity(), "事件标题不能为空");
            return;
        }
        if (TextUtils.isEmpty(message)) {
            MyToast.showMessage(getActivity(), "事件内容不能为空");
            return;
        }
        builder = builder
                .setTitle(title)
                .setMessage(message);
        if (cbIsGood.isChecked()) {
            builder = builder.setIsGood(true);
        }

        // 需要进行选择
        if (cbIsNeedSelect.isChecked()) {
            String okText = edtOkText.getText().toString();
            String cancelText = edtCancelText.getText().toString();

            String okGoodTitle = edtOkGoodTitle.getText().toString();
            String okGoodMsg = edtOkGoodMsg.getText().toString();
            String cancelGoodTitle = edtCancelGoodTitle.getText().toString();
            String cancelGoodMsg = edtCancelGoodMsg.getText().toString();

            String okBadTitle = edtOkBadTitle.getText().toString();
            String okBadMsg = edtOkBadMsg.getText().toString();
            String cancelBadTitle = edtCancelBadTitle.getText().toString();
            String cancelBadMsg = edtCancelBadMsg.getText().toString();
            if (!TextUtils.isEmpty(okText)) {
                builder = builder.setOkText(okText);
            }
            if (!TextUtils.isEmpty(cancelText)) {
                builder = builder.setCancelText(cancelText);
            }

            if (TextUtils.isEmpty(okGoodTitle)) {
                MyToast.showMessage(getActivity(), "确定后标题1不能为空");
                return;
            }
            if (TextUtils.isEmpty(okGoodMsg)) {
                MyToast.showMessage(getActivity(), "确定后内容1不能为空");
                return;
            }
            if (TextUtils.isEmpty(cancelGoodTitle)) {
                MyToast.showMessage(getActivity(), "取消后标题1不能为空");
                return;
            }
            if (TextUtils.isEmpty(cancelGoodMsg)) {
                MyToast.showMessage(getActivity(), "取消后内容1不能为空");
                return;
            }

            if (TextUtils.isEmpty(okBadTitle)) {
                MyToast.showMessage(getActivity(), "确定后标题2不能为空");
                return;
            }
            if (TextUtils.isEmpty(okBadMsg)) {
                MyToast.showMessage(getActivity(), "确定后内容2不能为空");
                return;
            }
            if (TextUtils.isEmpty(cancelBadTitle)) {
                MyToast.showMessage(getActivity(), "取消后标题2不能为空");
                return;
            }
            if (TextUtils.isEmpty(cancelBadMsg)) {
                MyToast.showMessage(getActivity(), "取消后内容2不能为空");
                return;
            }
            builder = builder
                    .setIsNeedSelect(true)
                    .setResultOKGoodTitle(okGoodTitle)
                    .setResultOKGoodMsg(okGoodMsg)
                    .setResultCancelGoodTitle(cancelGoodTitle)
                    .setResultCancelGoodMsg(cancelGoodMsg)
                    .setResultOKBadTitle(okBadTitle)
                    .setResultOKBadMsg(okBadMsg)
                    .setResultCancelBadTitle(cancelText)
                    .setResultCancelBadMsg(cancelBadMsg);
            if (mEvent != null) {
                JoinBadGoodsToEventUtil.deleteAllGoods(mEvent.getId());
            }
            if (mBadDatas != null && mBadDatas.size() > 0) {
                for (Goods goods :
                        mBadDatas) {
                    builder = builder.addBadGoods(goods);
                }
            }
        }

        // 有现金
        if (cbIsNeedCash.isChecked()) {
            String price = edtPrice.getText().toString();
            String priceMaxPercent = edtPriceMaxPercent.getText().toString();
            String priceMinPercent = edtPriceMinPercent.getText().toString();

            if (!checkNumber("价格", price, priceMaxPercent, priceMinPercent)) {
                return;
            }
            priceMaxPercent = MathUtil.divide(priceMaxPercent, "100", 2);
            priceMinPercent = MathUtil.divide(priceMinPercent, "100", 2);

            if (mEvent != null) {
                builder = builder.setCash(NumberUtil.create(mEvent.getCashId(), price, priceMaxPercent, priceMinPercent));
            } else {
                builder = builder.setCash(NumberUtil.create(price, priceMaxPercent, priceMinPercent));
            }
        }

        // 有负债
        if (cbIsNeedDebt.isChecked()) {
            String debt = edtDebt.getText().toString();
            String debtMaxPercent = edtDebtMaxPercent.getText().toString();
            String debtMinPercent = edtDebtMinPercent.getText().toString();

            if (!checkNumber("负债", debt, debtMaxPercent, debtMinPercent)) {
                return;
            }
            debtMaxPercent = MathUtil.divide(debtMaxPercent, "100", 2);
            debtMinPercent = MathUtil.divide(debtMinPercent, "100", 2);

            if (mEvent != null) {
                builder = builder.setDebt(NumberUtil.create(mEvent.getDebtId(), debt, debtMaxPercent, debtMinPercent));
            } else {
                builder = builder.setDebt(NumberUtil.create(debt, debtMaxPercent, debtMinPercent));
            }
        }

        // 有存款
        if (cbIsNeedDeposit.isChecked()) {
            String deposit = edtDeposit.getText().toString();
            String depositMaxPercent = edtDepositMaxPercent.getText().toString();
            String depositMinPercent = edtDepositMinPercent.getText().toString();

            if (!checkNumber("存款", deposit, depositMaxPercent, depositMinPercent)) {
                return;
            }
            depositMaxPercent = MathUtil.divide(depositMaxPercent, "100", 2);
            depositMinPercent = MathUtil.divide(depositMinPercent, "100", 2);

            if (mEvent != null) {
                builder = builder.setDeposit(NumberUtil.create(mEvent.getDepositId(), deposit, depositMaxPercent, depositMinPercent));
            } else {
                builder = builder.setDeposit(NumberUtil.create(deposit, depositMaxPercent, depositMinPercent));
            }
        }

        // 有健康
        if (cbIsNeedHealth.isChecked()) {
            String health = edtHealth.getText().toString();
            String healthMaxPercent = edtHealthMaxPercent.getText().toString();
            String healthMinPercent = edtHealthMinPercent.getText().toString();

            if (!checkNumber("健康", health, healthMaxPercent, healthMinPercent)) {
                return;
            }
            if (MathUtil.le(health, "0")) {
                MyToast.showMessage(getActivity(), "健康 必须大于 0");
                return;
            }
            if (MathUtil.gt(health, "100")) {
                MyToast.showMessage(getActivity(), "健康 不能大于 100");
                return;
            }
            healthMaxPercent = MathUtil.divide(healthMaxPercent, "100", 2);
            healthMinPercent = MathUtil.divide(healthMinPercent, "100", 2);

            if (mEvent != null) {
                builder = builder.setHealth(NumberUtil.create(mEvent.getHealthId(), health, healthMaxPercent, healthMinPercent));
            } else {
                builder = builder.setHealth(NumberUtil.create(health, healthMaxPercent, healthMinPercent));
            }
        }

        if (mEvent != null) {
            builder = builder.setId(mEvent.getId());
            JoinGoodGoodsToEventUtil.deleteAllGoods(mEvent.getId());
        }

        if (mGoodDatas != null && mGoodDatas.size() > 0) {
            for (Goods goods :
                    mGoodDatas) {
                builder = builder.addGoods(goods);
            }
        }

        builder.create();
        dismiss();
        if (mSaveListener != null) {
            mSaveListener.onSuccess();
        }
    }

    private void animateOpen(View v, int height) {
        v.setVisibility(View.VISIBLE);
        ValueAnimator animator = createDropAnimator(v, 0, height);
        animator.start();

    }

    private void animateClose(final View view) {
        int origHeight = view.getHeight();
        ValueAnimator animator = createDropAnimator(view, origHeight, 0);
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                view.setVisibility(View.GONE);
            }

        });
        animator.start();
    }

    private ValueAnimator createDropAnimator(final View v, int start, int end) {
        ValueAnimator animator = ValueAnimator.ofInt(start, end);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator arg0) {
                int value = (int) arg0.getAnimatedValue();
                ViewGroup.LayoutParams layoutParams = v.getLayoutParams();
                layoutParams.height = value;
                v.setLayoutParams(layoutParams);

            }
        });
        return animator;
    }

    private boolean checkNumber(String numberName, String number, String maxPercent, String minPercent) {
        if (TextUtils.isEmpty(number)) {
            MyToast.showMessage(getActivity(), numberName + "不能为空");
            return false;
        }
        if (TextUtils.isEmpty(maxPercent)) {
            MyToast.showMessage(getActivity(), numberName + "浮动上限不能为空");
            return false;
        }
        if (TextUtils.isEmpty(minPercent)) {
            MyToast.showMessage(getActivity(), numberName + "浮动下限不能为空");
            return false;
        }
        if (!MathUtil.checkNumber(number)) {
            MyToast.showMessage(getActivity(), numberName + "  格式不正确，请输入整数");
            return false;
        }
        if (!MathUtil.checkNumber(maxPercent)) {
            MyToast.showMessage(getActivity(), numberName + "浮动上限  格式不正确，请输入整数");
            return false;
        }
        if (!MathUtil.checkNumber(minPercent)) {
            MyToast.showMessage(getActivity(), numberName + "浮动下限  格式不正确，请输入整数");
            return false;
        }

//        if (MathUtil.le(number, "0")) {
//            MyToast.showMessage(getActivity(), numberName + " 必须大于 0");
//            return false;
//        }
//        if (MathUtil.le(maxPercent, "0")) {
//            MyToast.showMessage(getActivity(), numberName + "浮动上限 必须大于 0");
//            return false;
//        }
//        if (MathUtil.le(minPercent, "0")) {
//            MyToast.showMessage(getActivity(), numberName + "浮动下限 必须大于 0");
//            return false;
//        }
        if (MathUtil.le(maxPercent, minPercent)) {
            MyToast.showMessage(getActivity(), numberName + "浮动上限 必须大于 " + numberName + "浮动下限");
            return false;
        }
        return true;
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        mEvent = null;
        mBadDatas = null;
        mGoodDatas = null;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public void setHint(String hint) {
        mHint = hint;
    }

    public void setOnSaveListener(SaveListener saveListener) {
        mSaveListener = saveListener;
    }

    public interface SaveListener {
        void onSuccess();
    }
}
