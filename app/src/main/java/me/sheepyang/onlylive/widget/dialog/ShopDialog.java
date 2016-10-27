package me.sheepyang.onlylive.widget.dialog;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.sheepyang.onlylive.R;
import me.sheepyang.onlylive.entity.Player;
import me.sheepyang.onlylive.fragment.PlayerGoodsFragment;
import me.sheepyang.onlylive.fragment.ShopGoodsFragment;
import me.sheepyang.onlylive.utils.data.PlayerUtil;
import me.sheepyang.onlylive.widget.MarqueeTextView;

/**
 * Created by SheepYang on 2016/10/27 21:26.
 */

public class ShopDialog extends DialogFragment {
    private static final int TYPE_BUY = 121212;
    private static final int TYPE_SELL = 131313;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.tv_city)
    MarqueeTextView tvCity;
    @BindView(R.id.tvBuy)
    TextView tvBuy;
    @BindView(R.id.rlBuy)
    RelativeLayout rlBuy;
    @BindView(R.id.tvSell)
    TextView tvSell;
    @BindView(R.id.rlSell)
    RelativeLayout rlSell;
    @BindView(R.id.llHint1)
    LinearLayout llHint1;
    @BindView(R.id.tvCash)
    TextView tvCash;
    @BindView(R.id.tvDeposit)
    TextView tvDeposit;
    @BindView(R.id.llHint2)
    LinearLayout llHint2;
    private GoodsFragmentAdapter mAdapter;
    private Player mPlayer;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCancelable(true);
        setStyle(DialogFragment.STYLE_NO_TITLE, android.R.style.Theme_Holo_Light_Dialog_MinWidth);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.dialog_shop, null);
        ButterKnife.bind(this, view);
        getDialog().setCanceledOnTouchOutside(true);//点击边际可消失
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));// 设置背景透明
        mPlayer = PlayerUtil.getPlayer();
        initView();
        setShopType(TYPE_BUY);
        return view;
    }

    private void initView() {
        List<Fragment> list = new ArrayList<>();
        list.add(new ShopGoodsFragment());
        list.add(new PlayerGoodsFragment());
        mAdapter = new GoodsFragmentAdapter(getChildFragmentManager(), list);// 注意，这边是ChildFragmentManager而不是FragmentManager
        viewpager.setAdapter(mAdapter);
        viewpager.setCurrentItem(0);

        tvCash.setText("现金：" + mPlayer.getCash());
        tvDeposit.setText("存款：" + mPlayer.getDeposit());
    }

    @Override
    public void show(FragmentManager fm, String tag) {
        super.show(fm, tag);
    }

    @Override
    public void onResume() {
        super.onResume();
        viewpager.setCurrentItem(0);
    }

    @OnClick({R.id.rlBuy, R.id.rlSell, R.id.btn_cancel, R.id.btn_ok})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rlBuy:// 买入
                setShopType(TYPE_BUY);
                break;
            case R.id.rlSell:// 卖出
                setShopType(TYPE_SELL);
                break;
            case R.id.btn_cancel:
                dismiss();
                break;
            case R.id.btn_ok:
                break;
            default:
                break;
        }
    }

    private void setShopType(int type) {
        switch (type) {
            case TYPE_BUY:
                rlBuy.setBackgroundResource(R.drawable.bg_white_shape);
                rlSell.setBackgroundResource(R.drawable.bg_black_shape);
                tvBuy.setTextColor(getResources().getColor(R.color.text_black));
                tvSell.setTextColor(getResources().getColor(R.color.text_white));
                break;
            case TYPE_SELL:
                rlBuy.setBackgroundResource(R.drawable.bg_black_shape);
                rlSell.setBackgroundResource(R.drawable.bg_white_shape);
                tvBuy.setTextColor(getResources().getColor(R.color.text_white));
                tvSell.setTextColor(getResources().getColor(R.color.text_black));
                break;
            default:
                break;
        }
    }

    private class GoodsFragmentAdapter extends FragmentPagerAdapter {
        List<Fragment> mDataList = new ArrayList<>();

        public GoodsFragmentAdapter(FragmentManager fm, List<Fragment> dataList) {
            super(fm);
            mDataList = dataList;
        }

        @Override
        public Fragment getItem(int position) {
            return mDataList.get(position);
        }

        @Override
        public int getCount() {
            return mDataList.size();
        }
    }
}
