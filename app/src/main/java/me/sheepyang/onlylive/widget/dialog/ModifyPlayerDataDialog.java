package me.sheepyang.onlylive.widget.dialog;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.sheepyang.onlylive.R;
import me.sheepyang.onlylive.utils.CacheUtil;
import me.sheepyang.onlylive.utils.MathUtil;
import me.sheepyang.onlylive.utils.MyToast;

/**
 * Created by SheepYang on 2016/11/26 11:15.
 */

public class ModifyPlayerDataDialog extends BaseDialogFragment implements View.OnClickListener {
    @BindView(R.id.edt_cash)
    EditText edtCash;
    @BindView(R.id.edt_debt)
    EditText edtDebt;
    @BindView(R.id.edt_deposit)
    EditText edtDeposit;
    @BindView(R.id.edt_health)
    EditText edtHealth;
    @BindView(R.id.edt_house)
    EditText edtHouse;
    @BindView(R.id.edt_house_total)
    EditText edtHouseTotal;
    @BindView(R.id.edt_week)
    EditText edtWeek;
    @BindView(R.id.edt_week_total)
    EditText edtWeekTotal;
    @BindView(R.id.edt_shop_goods_number)
    EditText edtShopGoodsNumber;
    private SaveListener mSaveListener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.dialog_modify_player_data, null);
        ButterKnife.bind(this, view);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));// 设置背景透明
        return view;
    }

    private void initView() {
        edtCash.setText(CacheUtil.getInitGameCash(getActivity()));
        edtDebt.setText(CacheUtil.getInitGameDebt(getActivity()));
        edtDeposit.setText(CacheUtil.getInitGameDeposit(getActivity()));
        edtHealth.setText(CacheUtil.getInitGameHealth(getActivity()));
        edtHouse.setText(CacheUtil.getInitGameHouse(getActivity()));
        edtHouseTotal.setText(CacheUtil.getInitGameHouseTotal(getActivity()));
        edtWeek.setText(CacheUtil.getInitGameWeek(getActivity()));
        edtWeekTotal.setText(CacheUtil.getInitGameWeekTotal(getActivity()));
        edtShopGoodsNumber.setText(CacheUtil.getInitGameShopGoodsNumber(getActivity()) + "");
    }

    @Override
    @OnClick({R.id.btn_ok, R.id.btn_cancel})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_ok:
                save();
                break;
            case R.id.btn_cancel:
                dismiss();
                break;
            default:
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        initView();
    }

    private void save() {
        String cash = edtCash.getText().toString();
        String debt = edtDebt.getText().toString();
        String deposit = edtDeposit.getText().toString();
        String health = edtHealth.getText().toString();
        String house = edtHouse.getText().toString();
        String houseTotal = edtHouseTotal.getText().toString();
        String week = edtWeek.getText().toString();
        String weekTotal = edtWeekTotal.getText().toString();
        String shopGoodsNumber = edtShopGoodsNumber.getText().toString();

        if (!checkNumber("现金", cash)) {
            return;
        }
        if (!checkNumber("负债", debt)) {
            return;
        }
        if (!checkNumber("存款", deposit)) {
            return;
        }
        if (!checkNumber("健康", health)) {
            return;
        }
        if (!checkNumber("使用空间", house)) {
            return;
        }
        if (!checkNumber("总房子数量", houseTotal)) {
            return;
        }
        if (!checkNumber("当前周数", week)) {
            return;
        }
        if (!checkNumber("游戏总周数", weekTotal)) {
            return;
        }
        if (!checkNumber("出售商品数", shopGoodsNumber)) {
            return;
        }

        if (MathUtil.gt(health, "100")) {
            MyToast.showMessage(getActivity(), "健康 不能大于 100");
            return;
        }
        if (MathUtil.gt(house, houseTotal)) {
            MyToast.showMessage(getActivity(), "使用空间 不能大于 总房子数量");
            return;
        }
        if (MathUtil.le(houseTotal, "0")) {
            MyToast.showMessage(getActivity(), "总房子数量 必须大于 0");
            return;
        }
        if (MathUtil.gt(week, weekTotal)) {
            MyToast.showMessage(getActivity(), "当前周数 不能大于 游戏总周数");
            return;
        }
        if (MathUtil.le(weekTotal, "0")) {
            MyToast.showMessage(getActivity(), "游戏总周数 必须大于 0");
            return;
        }
        if (MathUtil.le(shopGoodsNumber, "0")) {
            MyToast.showMessage(getActivity(), "出售商品数 必须大于 0");
            return;
        }
        if (MathUtil.gt(shopGoodsNumber, "20")) {
            MyToast.showMessage(getActivity(), "出售商品数不能大于 20");
            return;
        }

        CacheUtil.setInitGameCash(getActivity(), MathUtil.getNumber(cash, 0));
        CacheUtil.setInitGameDebt(getActivity(), MathUtil.getNumber(debt, 0));
        CacheUtil.setInitGameDeposit(getActivity(), MathUtil.getNumber(deposit, 0));
        CacheUtil.setInitGameHealth(getActivity(), MathUtil.getNumber(health, 0));
        CacheUtil.setInitGameHouse(getActivity(), MathUtil.getNumber(house, 0));
        CacheUtil.setInitGameHouseTotal(getActivity(), MathUtil.getNumber(houseTotal, 0));
        CacheUtil.setInitGameWeek(getActivity(), MathUtil.getNumber(week, 0));
        CacheUtil.setInitGameWeekTotal(getActivity(), MathUtil.getNumber(weekTotal, 0));
        CacheUtil.setInitGameShopGoodsNumber(getActivity(), Integer.valueOf(MathUtil.getNumber(shopGoodsNumber, 0)));
        if (mSaveListener != null) {
            mSaveListener.onSuccess();
        }
        dismiss();
    }

    private boolean checkNumber(String numberName, String number) {
        if (!MathUtil.checkNumber(number)) {
            MyToast.showMessage(getActivity(), numberName + "  格式不正确，请输入整数");
            return false;
        }
        return true;
    }

    public void setOnSaveListener(SaveListener saveListener) {
        mSaveListener = saveListener;
    }

    public interface SaveListener {
        void onSuccess();
    }
}
