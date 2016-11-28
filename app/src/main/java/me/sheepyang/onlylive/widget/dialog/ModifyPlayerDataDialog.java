package me.sheepyang.onlylive.widget.dialog;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
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
    @BindView(R.id.edt_max_percent)
    EditText edtMaxPercent;
    @BindView(R.id.edt_min_percent)
    EditText edtMinPercent;
    @BindView(R.id.edt_goods_number)
    EditText edtGoodsNumber;
    @BindView(R.id.edt_health_cost)
    EditText edtHealthCost;
    @BindView(R.id.edt_house_level1)
    EditText edtHouseLevel1;
    @BindView(R.id.edt_house_level2)
    EditText edtHouseLevel2;
    @BindView(R.id.edt_house_level3)
    EditText edtHouseLevel3;
    @BindView(R.id.edt_house_level4)
    EditText edtHouseLevel4;
    @BindView(R.id.edt_house_level1_cost)
    EditText edtHouseLevel1Cost;
    @BindView(R.id.edt_house_level2_cost)
    EditText edtHouseLevel2Cost;
    @BindView(R.id.edt_house_level3_cost)
    EditText edtHouseLevel3Cost;
    @BindView(R.id.edt_house_level4_cost)
    EditText edtHouseLevel4Cost;
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

    private void initData() {
        edtCash.setText(CacheUtil.getInitGameCash(getActivity()));
        edtDebt.setText(CacheUtil.getInitGameDebt(getActivity()));
        edtDeposit.setText(CacheUtil.getInitGameDeposit(getActivity()));
        edtHealth.setText(CacheUtil.getInitGameHealth(getActivity()));
        edtHouse.setText(CacheUtil.getInitGameHouse(getActivity()));
        edtHouseTotal.setText(CacheUtil.getInitGameHouseTotal(getActivity()));
        edtWeek.setText(CacheUtil.getInitGameWeek(getActivity()));
        edtWeekTotal.setText(CacheUtil.getInitGameWeekTotal(getActivity()));
        edtShopGoodsNumber.setText(CacheUtil.getInitGameShopGoodsNumber(getActivity()));
        edtMaxPercent.setText(MathUtil.multiply("100", CacheUtil.getInitGameDebtRateMax(getActivity())));
        edtMinPercent.setText(MathUtil.multiply("100", CacheUtil.getInitGameDebtRateMin(getActivity())));
        edtGoodsNumber.setText(CacheUtil.getInitGameGoodsNumber(getActivity()));
        edtHealthCost.setText(CacheUtil.getInitGameHealthCost(getActivity()));
        edtHouseLevel1.setText(CacheUtil.getInitGameHouseLevel1(getActivity()));
        edtHouseLevel2.setText(CacheUtil.getInitGameHouseLevel2(getActivity()));
        edtHouseLevel3.setText(CacheUtil.getInitGameHouseLevel3(getActivity()));
        edtHouseLevel4.setText(CacheUtil.getInitGameHouseLevel4(getActivity()));
        edtHouseLevel1Cost.setText(CacheUtil.getInitGameHouseLevel1Cost(getActivity()));
        edtHouseLevel2Cost.setText(CacheUtil.getInitGameHouseLevel2Cost(getActivity()));
        edtHouseLevel3Cost.setText(CacheUtil.getInitGameHouseLevel3Cost(getActivity()));
        edtHouseLevel4Cost.setText(CacheUtil.getInitGameHouseLevel4Cost(getActivity()));
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
        initData();
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
        String goodsNumber = edtGoodsNumber.getText().toString();
        String maxPercent = edtMaxPercent.getText().toString();
        String minPercent = edtMinPercent.getText().toString();
        String healthCost = edtHealthCost.getText().toString();
        String houseLevel1 = edtHouseLevel1.getText().toString();
        String houseLevel2 = edtHouseLevel2.getText().toString();
        String houseLevel3 = edtHouseLevel3.getText().toString();
        String houseLevel4 = edtHouseLevel4.getText().toString();
        String houseLevel1Cost = edtHouseLevel1Cost.getText().toString();
        String houseLevel2Cost = edtHouseLevel2Cost.getText().toString();
        String houseLevel3Cost = edtHouseLevel3Cost.getText().toString();
        String houseLevel4Cost = edtHouseLevel4Cost.getText().toString();

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
        if (!checkNumber("利息最高倍率", maxPercent)) {
            return;
        }
        if (!checkNumber("利息最低倍率", minPercent)) {
            return;
        }
        if (!checkNumber("物品获得数", goodsNumber)) {
            return;
        }
        if (!checkNumber("恢复健康费用", healthCost)) {
            return;
        }
        if (!checkNumber("房子1数量", houseLevel1)) {
            return;
        }
        if (!checkNumber("房子2数量", houseLevel2)) {
            return;
        }
        if (!checkNumber("房子3数量", houseLevel3)) {
            return;
        }
        if (!checkNumber("房子4数量", houseLevel4)) {
            return;
        }
        if (!checkNumber("房子1花费", houseLevel1Cost)) {
            return;
        }
        if (!checkNumber("房子2花费", houseLevel2Cost)) {
            return;
        }
        if (!checkNumber("房子3花费", houseLevel3Cost)) {
            return;
        }
        if (!checkNumber("房子4花费", houseLevel4Cost)) {
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
            MyToast.showMessage(getActivity(), "出售商品数 不能大于 20");
            return;
        }
        if (MathUtil.le(goodsNumber, "0")) {
            MyToast.showMessage(getActivity(), "物品最多获得个数 必须大于 0");
            return;
        }
        if (MathUtil.le(maxPercent, "0")) {
            MyToast.showMessage(getActivity(), "利息最高倍率 必须大于 0");
            return;
        }
        if (MathUtil.le(minPercent, "0")) {
            MyToast.showMessage(getActivity(), "利息最低倍率 必须大于 0");
            return;
        }
        if (MathUtil.le(maxPercent, minPercent)) {
            MyToast.showMessage(getActivity(), "利息最高倍率 必须大于 利息最低倍率");
            return;
        }
        maxPercent = MathUtil.divide(maxPercent, "100", 2);
        minPercent = MathUtil.divide(minPercent, "100", 2);
        if (MathUtil.le(healthCost, "0")) {
            MyToast.showMessage(getActivity(), "恢复健康费用 必须大于 0");
            return;
        }
        if (MathUtil.le(houseLevel1, houseTotal)) {
            MyToast.showMessage(getActivity(), "房子1数量 必须大于 总房子数量");
            return;
        }
        if (MathUtil.le(houseLevel2, houseLevel1)) {
            MyToast.showMessage(getActivity(), "房子2数量 必须大于 房子1数量");
            return;
        }
        if (MathUtil.le(houseLevel3, houseLevel2)) {
            MyToast.showMessage(getActivity(), "房子3数量 必须大于 房子2数量");
            return;
        }
        if (MathUtil.le(houseLevel4, houseLevel3)) {
            MyToast.showMessage(getActivity(), "房子4数量 必须大于 房子3数量");
            return;
        }
        if (MathUtil.le(houseLevel1Cost, "0")) {
            MyToast.showMessage(getActivity(), "房子1花费 必须大于 0");
            return;
        }
        if (MathUtil.le(houseLevel2Cost, "0")) {
            MyToast.showMessage(getActivity(), "房子2花费 必须大于 0");
            return;
        }
        if (MathUtil.le(houseLevel3Cost, "0")) {
            MyToast.showMessage(getActivity(), "房子3花费 必须大于 0");
            return;
        }
        if (MathUtil.le(houseLevel4Cost, "0")) {
            MyToast.showMessage(getActivity(), "房子4花费 必须大于 0");
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
        CacheUtil.setInitGameShopGoodsNumber(getActivity(), MathUtil.getNumber(shopGoodsNumber, 0));
        CacheUtil.setInitGameGoodsNumber(getActivity(), MathUtil.getNumber(goodsNumber, 0));
        CacheUtil.setInitGameDebtRateMax(getActivity(), MathUtil.getNumber(maxPercent, 2));
        CacheUtil.setInitGameDebtRateMin(getActivity(), MathUtil.getNumber(minPercent, 2));
        CacheUtil.setInitGameHealthCost(getActivity(), MathUtil.getNumber(healthCost, 0));
        CacheUtil.setInitGameHouseLevel1(getActivity(), MathUtil.getNumber(houseLevel1, 0));
        CacheUtil.setInitGameHouseLevel2(getActivity(), MathUtil.getNumber(houseLevel2, 0));
        CacheUtil.setInitGameHouseLevel3(getActivity(), MathUtil.getNumber(houseLevel3, 0));
        CacheUtil.setInitGameHouseLevel4(getActivity(), MathUtil.getNumber(houseLevel4, 0));
        CacheUtil.setInitGameHouseLevel1Cost(getActivity(), MathUtil.getNumber(houseLevel1Cost, 0));
        CacheUtil.setInitGameHouseLevel2Cost(getActivity(), MathUtil.getNumber(houseLevel2Cost, 0));
        CacheUtil.setInitGameHouseLevel3Cost(getActivity(), MathUtil.getNumber(houseLevel3Cost, 0));
        CacheUtil.setInitGameHouseLevel4Cost(getActivity(), MathUtil.getNumber(houseLevel4Cost, 0));
        dismiss();
        if (mSaveListener != null) {
            mSaveListener.onSuccess();
        }
    }

    private boolean checkNumber(String numberName, String number) {
        if (TextUtils.isEmpty(number)) {
            MyToast.showMessage(getActivity(), numberName + "不能为空");
            return false;
        }
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
