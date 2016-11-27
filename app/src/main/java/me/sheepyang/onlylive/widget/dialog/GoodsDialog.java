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
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.sheepyang.onlylive.R;
import me.sheepyang.onlylive.entity.Goods;
import me.sheepyang.onlylive.utils.MathUtil;
import me.sheepyang.onlylive.utils.MyToast;
import me.sheepyang.onlylive.utils.data.GoodsUtil;
import me.sheepyang.onlylive.utils.data.NumberUtil;
import me.sheepyang.onlylive.widget.MarqueeTextView;

import static android.R.attr.name;

/**
 * Created by SheepYang on 2016/11/26 11:15.
 */

public class GoodsDialog extends BaseDialogFragment implements View.OnClickListener {
    @BindView(R.id.edt_name)
    EditText edtName;
    @BindView(R.id.edt_unit)
    EditText edtUnit;
    @BindView(R.id.edt_price)
    EditText edtPrice;
    @BindView(R.id.edt_max_percent)
    EditText edtMaxPercent;
    @BindView(R.id.edt_min_percent)
    EditText edtMinPercent;
    @BindView(R.id.tv_title)
    MarqueeTextView tvTitle;
    @BindView(R.id.tv_hint)
    TextView tvHint;
    private SaveListener mSaveListener;
    private Goods mGoods;
    private String mHint;
    private String mTitle;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.dialog_goods, null);
        ButterKnife.bind(this, view);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));// 设置背景透明
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        initData();
    }

    private void initData() {
        if (!TextUtils.isEmpty(mTitle)) {
            tvTitle.setText(mTitle);
        }
        if (!TextUtils.isEmpty(mHint)) {
            tvHint.setText(mHint);
        }
        if (mGoods != null) {
            edtName.setText(mGoods.getName());
            edtUnit.setText(mGoods.getUnit());
            edtPrice.setText(mGoods.getPrice().getNumber());
            edtMaxPercent.setText(MathUtil.multiply(mGoods.getPrice().getMaxPercent(), "100"));
            edtMinPercent.setText(MathUtil.multiply(mGoods.getPrice().getMinPercent(), "100"));
        } else {
            edtName.setText("");
            edtUnit.setText("");
            edtPrice.setText("");
            edtMaxPercent.setText("");
            edtMinPercent.setText("");
        }
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

    public void setGoods(Goods goods) {
        mGoods = goods;
    }

    private void save() {
        GoodsUtil.Builder builder = new GoodsUtil.Builder();
        String name = edtName.getText().toString();
        String unit = edtUnit.getText().toString();
        String price = edtPrice.getText().toString();
        String maxPercent = edtMaxPercent.getText().toString();
        String minPercent = edtMinPercent.getText().toString();

        if (TextUtils.isEmpty(name)) {
            MyToast.showMessage(getActivity(), "物品名称不能为空");
            return;
        }

        if (TextUtils.isEmpty(unit)) {
            MyToast.showMessage(getActivity(), "物品单位不能为空");
            return;
        }

        if (!checkNumber("价格", price)) {
            return;
        }
        if (!checkNumber("价格浮动上限", maxPercent)) {
            return;
        }
        if (!checkNumber("价格浮动下限", minPercent)) {
            return;
        }
        if (MathUtil.le(price, "0")) {
            MyToast.showMessage(getActivity(), "价格 必须大于 0");
            return;
        }
        if (MathUtil.le(maxPercent, "0")) {
            MyToast.showMessage(getActivity(), "价格浮动上限 必须大于 0");
            return;
        }
        if (MathUtil.le(minPercent, "0")) {
            MyToast.showMessage(getActivity(), "价格浮动下限 必须大于 0");
            return;
        }
        if (MathUtil.le(maxPercent, minPercent)) {
            MyToast.showMessage(getActivity(), "价格浮动上限 必须大于 价格浮动下限");
            return;
        }
        maxPercent = MathUtil.divide(maxPercent, "100", 2);
        minPercent = MathUtil.divide(minPercent, "100", 2);

        builder = builder
                .setName(name)
                .setUnit(unit);
        if (mGoods != null) {
            builder = builder.setId(mGoods.getId())
                    .setPrice(NumberUtil.create(mGoods.getPriceId(), price, maxPercent, minPercent));
        } else {
            builder = builder
                    .setPrice(NumberUtil.create(price, maxPercent, minPercent));
        }
        builder.create();
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
