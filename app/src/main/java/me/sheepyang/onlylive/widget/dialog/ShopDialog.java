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
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.sheepyang.onlylive.R;
import me.sheepyang.onlylive.entity.Player;
import me.sheepyang.onlylive.entity.ShopGoods;
import me.sheepyang.onlylive.fragment.PlayerGoodsFragment;
import me.sheepyang.onlylive.fragment.ShopGoodsFragment;
import me.sheepyang.onlylive.utils.MyLog;
import me.sheepyang.onlylive.utils.MyToast;
import me.sheepyang.onlylive.utils.data.PlayerGoodsUtil;
import me.sheepyang.onlylive.utils.data.PlayerUtil;
import me.sheepyang.onlylive.widget.MarqueeTextView;

import static android.R.attr.max;
import static me.sheepyang.onlylive.R.id.tv_goods_cash;

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
    @BindView(R.id.tvDeposit)
    TextView tvDeposit;
    @BindView(R.id.llHint2)
    LinearLayout llHint2;
    @BindView(R.id.tv_goods_name)
    TextView tvGoodsName;
    @BindView(R.id.tv_goods_num)
    TextView tvGoodsNum;
    @BindView(tv_goods_cash)
    TextView tvGoodsCash;
    @BindView(R.id.tvCash)
    TextView tvCash;
    @BindView(R.id.tv_goods_price)
    TextView tvGoodsPrice;
    @BindView(R.id.seekbar)
    SeekBar seekbar;
    @BindView(R.id.tv_cost)
    TextView tvCost;
    @BindView(R.id.tv_house)
    TextView tvHouse;
    private GoodsFragmentAdapter mAdapter;
    private Player mPlayer;
    private String mCity;
    private ShopGoodsFragment mShopGoodsFragment;
    private ShopGoods mShopGoods;
    private int mShopGoodsNum;

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
        initListener();
        initData();
        setShopType(TYPE_BUY);
        return view;
    }

    private void initData() {
        tvCity.setText("当前城市：" + mCity);
        tvCash.setText("现金：" + mPlayer.getCash());
        tvDeposit.setText("存款：" + mPlayer.getDeposit());
        viewpager.setCurrentItem(0);
    }

    private void initListener() {
        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mShopGoodsNum = progress;
                tvCost.setText("花费：" + progress * mShopGoods.getPrice());
                tvGoodsNum.setText("数量：" + progress);
                tvHouse.setText("房子：" + (mPlayer.getHouse() + progress) + "/" + mPlayer.getHouseTotal());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        mShopGoodsFragment.setOnItemClickListener(new ShopGoodsFragment.OnItemClickListener() {
            @Override
            public void onItemClick(ShopGoods shopGoods) {
                mShopGoods = shopGoods;
                llHint1.setVisibility(View.VISIBLE);
                llHint2.setVisibility(View.GONE);
                tvGoodsName.setText("物品：" + mShopGoods.getName());
                tvGoodsPrice.setText("单价：" + mShopGoods.getPrice() + "");
                tvGoodsCash.setText("现金：" + mPlayer.getCash() + "");
                setSeekBar(shopGoods);
            }
        });
        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        setShopType(TYPE_BUY);
                        break;
                    case 1:
                        setShopType(TYPE_SELL);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initView() {
        List<Fragment> list = new ArrayList<>();
        mShopGoodsFragment = new ShopGoodsFragment();
        list.add(mShopGoodsFragment);
        list.add(new PlayerGoodsFragment());
        mAdapter = new GoodsFragmentAdapter(getChildFragmentManager(), list);// 注意，这边是ChildFragmentManager而不是FragmentManager
        viewpager.setAdapter(mAdapter);
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
                viewpager.setCurrentItem(0);
                break;
            case R.id.rlSell:// 卖出
                viewpager.setCurrentItem(1);
                break;
            case R.id.btn_cancel:
                dismiss();
                break;
            case R.id.btn_ok:
                if (viewpager.getCurrentItem() == 0) {// 买入
                    buy();
                } else if (viewpager.getCurrentItem() == 1) {// 卖出

                }
//                dismiss();
                break;
            default:
                break;
        }
    }

    private void buy() {
        if (mShopGoods != null) {
            if (mShopGoodsNum <= 0) {
                int totalGoodsNum = (int) (mPlayer.getCash() / mShopGoods.getPrice());// 现金最多能买商品个数
                int houseNum = mPlayer.getHouseTotal() - mPlayer.getHouse();// 房子能装下的商品个数
                if (totalGoodsNum <= 0) {// 现金连一件商品都买不起
                    MyToast.showMessage(getActivity(), "没有足够的现金");
                } else if (houseNum <= 0) {// 房子已经装不下更多商品
                    MyToast.showMessage(getActivity(), "房子里已经放不下东西啦");
                }
            } else {
                MyLog.i("买入 -> 物品：" + mShopGoods.getName() + "， 单价：" + mShopGoods.getPrice() + "， 数量：" + mShopGoodsNum);
                mPlayer.setCash(mShopGoods.getPrice() * mShopGoodsNum);
                PlayerUtil.setPlayer(mPlayer);
                PlayerGoodsUtil.addPlayGoods(mShopGoods, mShopGoodsNum);
                refreshView();
                MyToast.showMessage(getActivity(), "购买成功");
                dismiss();
            }
        } else {
            MyToast.showMessage(getActivity(), "请选择买入的物品");
        }
    }

    private void refreshView() {
        mPlayer = PlayerUtil.getPlayer();
        tvGoodsCash.setText("现金：" + mPlayer.getCash());
    }

    private void setShopType(int type) {
        llHint1.setVisibility(View.GONE);
        llHint2.setVisibility(View.VISIBLE);
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

    public void setCity(String city) {
        mCity = city;
    }

    public void setSeekBar(ShopGoods shopGoods) {
        int totalGoodsNum = (int) (mPlayer.getCash() / shopGoods.getPrice());// 现金最多能买商品个数
        int houseNum = mPlayer.getHouseTotal() - mPlayer.getHouse();// 房子能装下的商品个数
        int max = 0;
        if (houseNum <= 0 || totalGoodsNum <= 0) {// 当现金连一件商品都买不起的时候  或者  房子已经装不下更多商品时
            max = 0;
        } else {
            if (houseNum >= totalGoodsNum) {
                max = totalGoodsNum;
            } else {
                max = houseNum;
            }
        }
        seekbar.setMax(max);
        seekbar.setProgress(max);
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
