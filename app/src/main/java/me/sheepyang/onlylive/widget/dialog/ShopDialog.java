package me.sheepyang.onlylive.widget.dialog;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
import me.sheepyang.onlylive.entity.Goods;
import me.sheepyang.onlylive.entity.Player;
import me.sheepyang.onlylive.entity.PlayerGoods;
import me.sheepyang.onlylive.entity.ShopGoods;
import me.sheepyang.onlylive.fragment.PlayerGoodsFragment;
import me.sheepyang.onlylive.fragment.ShopGoodsFragment;
import me.sheepyang.onlylive.utils.MathUtil;
import me.sheepyang.onlylive.utils.MyLog;
import me.sheepyang.onlylive.utils.MyToast;
import me.sheepyang.onlylive.utils.data.GoodsUtil;
import me.sheepyang.onlylive.utils.data.PlayerGoodsUtil;
import me.sheepyang.onlylive.utils.data.PlayerUtil;
import me.sheepyang.onlylive.utils.data.ShopGoodsUtil;
import me.sheepyang.onlylive.widget.MarqueeTextView;

import static me.sheepyang.onlylive.R.id.tv_goods_cash;

/**
 * Created by SheepYang on 2016/10/27 21:26.
 */

public class ShopDialog extends BaseDialogFragment {
    private static final int TYPE_BUY = 121212;
    private static final int TYPE_SELL = 131313;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.tv_title_hint)
    TextView tvTitleHint;
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
    private PlayerGoodsFragment mPlayerGoodsFragment;
    private ShopGoods mShopGoods;
    private PlayerGoods mPlayerGoods;
    private String mShopGoodsNum;
    private String mPlayerGoodsNum;
    private OnShopListener mListener;
    private String mSeekbarMax;
    private List<ShopGoods> mShopGoodsList;

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
        tvCity.setText("当前地区：" + mCity);
        tvCash.setText("现金：" + mPlayer.getCash());
        tvDeposit.setText("存款：" + mPlayer.getDeposit());
        viewpager.setCurrentItem(0);
    }

    private void initListener() {
        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                String percent = MathUtil.divide(progress + "", seekBar.getMax() + "", 2);// 百分比
                String result = MathUtil.multiply(mSeekbarMax, percent);
                if (viewpager.getCurrentItem() == 0) {// 买入
                    mShopGoodsNum = result;
                    tvGoodsNum.setText("数量：" + mShopGoodsNum);
                    tvHouse.setText("房子：" + MathUtil.add(mPlayer.getHouse(), mShopGoodsNum) + "/" + mPlayer.getHouseTotal());
                    if (mShopGoods != null) {
                        tvCost.setText("花费：" + MathUtil.multiply(mShopGoodsNum, mShopGoods.getPrice()));
                    }
                } else if (viewpager.getCurrentItem() == 1) {// 卖出
                    mPlayerGoodsNum = result;
                    tvGoodsNum.setText("数量：" + mPlayerGoodsNum);
                    if (mPlayerGoods != null) {
                        tvCost.setText("销售额：" + MathUtil.multiply(mPlayerGoodsNum, mPlayerGoods.getPaid()));// 数量 * 进价
                        tvHouse.setText("利润：" + MathUtil.multiply(mPlayerGoodsNum, MathUtil.subtract(mPlayerGoods.getPrice(), mPlayerGoods.getPaid())));// 市价 - 进价
                    }
                }
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
                setSeekBarToMax(shopGoods);
            }
        });
        mPlayerGoodsFragment.setOnItemClickListener(new PlayerGoodsFragment.OnItemClickListener() {
            @Override
            public void onItemClick(PlayerGoods playerGoods) {
                mPlayerGoods = playerGoods;
                llHint1.setVisibility(View.VISIBLE);
                llHint2.setVisibility(View.GONE);
                tvGoodsName.setText("物品：" + mPlayerGoods.getName());
                tvGoodsPrice.setText("市价：" + mPlayerGoods.getPrice() + "");
                tvGoodsCash.setText("进价：" + mPlayerGoods.getPaid() + "");
                setSeekBarToMax(playerGoods);
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

    public void setOnShopListener(OnShopListener listener) {
        mListener = listener;
    }

    private void initView() {
        List<Fragment> list = new ArrayList<>();
        mShopGoodsFragment = new ShopGoodsFragment();
        mPlayerGoodsFragment = new PlayerGoodsFragment();

        if (mShopGoodsList == null || mShopGoodsList.size() <= 0) {
            List<Goods> goodsList = GoodsUtil.getRandomList(20);
            mShopGoodsList = ShopGoodsUtil.getShopGoodsList(goodsList);
        }

        mShopGoodsFragment.setShopGoodsList(mShopGoodsList);
        mPlayerGoodsFragment.setShopGoodsList(mShopGoodsList);

        list.add(mShopGoodsFragment);
        list.add(mPlayerGoodsFragment);
        mAdapter = new GoodsFragmentAdapter(getChildFragmentManager(), list);// 注意，这边是ChildFragmentManager而不是FragmentManager
        viewpager.setAdapter(mAdapter);
    }

    public List<ShopGoods> getShopGoodsList() {
        return mShopGoodsList;
    }

    public void setShopGoodsList(List<ShopGoods> shopGoodsList) {
        mShopGoodsList = shopGoodsList;
    }

    @Override
    public void onResume() {
        super.onResume();
        viewpager.setCurrentItem(0);
    }

    @OnClick({R.id.rlBuy, R.id.rlSell, R.id.btn_cancel, R.id.btn_ok})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rlBuy:// 选择买入
                viewpager.setCurrentItem(0);
                break;
            case R.id.rlSell:// 选择卖出
                viewpager.setCurrentItem(1);
                break;
            case R.id.btn_cancel:
                dismiss();
                break;
            case R.id.btn_ok:
                if (llHint2.getVisibility() == View.VISIBLE) {
                    MyToast.showMessage(getActivity(), "请选择买入或卖出");
                    return;
                } else {
                    if (viewpager.getCurrentItem() == 0) {// 买入
                        toBuy();
                    } else if (viewpager.getCurrentItem() == 1) {// 卖出
                        toSell();
                    }
                }
                break;
            default:
                break;
        }
    }


    /**
     * 出售物品
     */
    private void toSell() {
        if (mPlayerGoods != null) {
            MyLog.i("卖出 -> 物品：" + mPlayerGoods.getName() + "， 数量：" + mPlayerGoodsNum + "， 市价：" + mPlayerGoods.getPrice() + "， 进价：" + mPlayerGoods.getPaid());
            String sales = MathUtil.multiply(mPlayerGoodsNum, mPlayerGoods.getPaid());
            MyLog.i("卖出 -> 销售额：" + sales);
            mPlayer.setCash(MathUtil.add(mPlayer.getCash(), sales));
            mPlayer.setHouse(MathUtil.subtract(mPlayer.getHouse(), mPlayerGoodsNum));
            PlayerUtil.setPlayer(mPlayer);
            mPlayerGoods.setNumber(MathUtil.subtract(mPlayerGoods.getNumber(), mPlayerGoodsNum));
            mPlayerGoodsNum = "0";
            PlayerGoodsUtil.insertOrReplace(mPlayerGoods);
            if (MathUtil.le(mPlayerGoods.getNumber(), "0")) {// 全部卖完
                PlayerGoodsUtil.delete(mPlayerGoods);
                mPlayerGoods = null;
                setShopType(TYPE_SELL);
            }
            mPlayer = PlayerUtil.getPlayer();
            setSeekBarToMax(mPlayerGoods);
            mPlayerGoodsFragment.refreshData();
            refreshView();
            if (mListener != null) {
                mListener.onSellSuccess();
            }
        } else {
            if (mListener != null) {
                mListener.onSellError("销售失败");
            }
        }
    }

    /**
     * 购买物品
     */
    private void toBuy() {
        if (mShopGoods != null) {
            if (MathUtil.le(mShopGoodsNum, "0")) {
                String totalGoodsNum = MathUtil.divide(mPlayer.getCash(), mShopGoods.getPrice());// 现金最多能买商品个数
                String houseNum = MathUtil.subtract(mPlayer.getHouseTotal(), mPlayer.getHouse());// 房子能装下的商品个数
                String msg = "";
                if (MathUtil.le(totalGoodsNum, "0")) {// 现金连一件商品都买不起
                    msg = "没有足够的现金";
                } else if (MathUtil.le(houseNum, "0")) {// 房子已经装不下更多商品
                    msg = "房子里已经放不下东西啦";
                } else {
                    msg = "请选择物品数量";
                }
                if (mListener != null) {
                    mListener.onBuyError(msg);
                }
            } else {
                MyLog.i("买入 -> 物品：" + mShopGoods.getName() + "， 单价：" + mShopGoods.getPrice() + "， 数量：" + mShopGoodsNum);
                mPlayer.setCash(MathUtil.subtract(mPlayer.getCash(), MathUtil.multiply(mShopGoods.getPrice(), mShopGoodsNum)));
                mPlayer.setHouse(MathUtil.add(mPlayer.getHouse(), mShopGoodsNum));
                PlayerUtil.setPlayer(mPlayer);
                PlayerGoodsUtil.addPlayGoods(mShopGoods, mShopGoodsNum);
                setSeekBarToMax(mShopGoods);
                mPlayerGoodsFragment.refreshData();// 刷新界面数据
                refreshView();
                if (mListener != null) {
                    mListener.onBuySuccess();
                }
            }
        } else {
            MyToast.showMessage(getActivity(), "请选择买入的物品");
        }
    }

    private void refreshView() {
        mPlayer = PlayerUtil.getPlayer();
        tvCash.setText("现金：" + mPlayer.getCash());
        tvDeposit.setText("存款：" + mPlayer.getDeposit());
        if (viewpager.getCurrentItem() == 0) {// 买入
            if (mShopGoods != null) {
                tvCost.setText("花费：" + MathUtil.multiply(mShopGoodsNum, mShopGoods.getPrice()));
                tvGoodsName.setText("物品：" + mShopGoods.getName());
                tvGoodsNum.setText("数量：" + mShopGoodsNum);
                tvGoodsPrice.setText("单价：" + mShopGoods.getPrice() + "");
            }
            tvHouse.setText("房子：" + mPlayer.getHouse() + "/" + mPlayer.getHouseTotal());
            tvGoodsCash.setText("现金：" + mPlayer.getCash());
        } else if (viewpager.getCurrentItem() == 1) {// 卖出
            if (mPlayerGoods != null) {
                tvCost.setText("销售额：" + MathUtil.multiply(mPlayerGoodsNum, mPlayerGoods.getPaid()));// 数量 * 进价
                tvHouse.setText("利润：" + MathUtil.multiply(mPlayerGoodsNum, MathUtil.subtract(mPlayerGoods.getPrice(), mPlayerGoods.getPaid())));// 市价 - 进价

                tvGoodsName.setText("物品：" + mPlayerGoods.getName());
                tvGoodsNum.setText("数量：" + mPlayerGoodsNum);
                tvGoodsCash.setText("进价：" + mPlayerGoods.getPaid() + "");
                tvGoodsPrice.setText("市价：" + mPlayerGoods.getPrice() + "");
            }
        }
    }

    private void setShopType(int type) {
        llHint1.setVisibility(View.GONE);
        llHint2.setVisibility(View.VISIBLE);
        switch (type) {
            case TYPE_BUY:
                tvTitleHint.setText("（商店）");
                rlBuy.setBackgroundResource(R.drawable.bg_white2_shape);
                rlSell.setBackgroundResource(R.drawable.bg_black_shape);
                tvBuy.setTextColor(getResources().getColor(R.color.text_black));
                tvSell.setTextColor(getResources().getColor(R.color.text_white));
                break;
            case TYPE_SELL:
                tvTitleHint.setText("（背包）");
                rlBuy.setBackgroundResource(R.drawable.bg_black_shape);
                rlSell.setBackgroundResource(R.drawable.bg_white2_shape);
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

    public void setSeekBarToMax(PlayerGoods playerGoods) {
        if (playerGoods != null) {
            mSeekbarMax = playerGoods.getNumber();
            mPlayerGoodsNum = mSeekbarMax;
            tvCost.setText("销售额：" + MathUtil.multiply(mSeekbarMax, mPlayerGoods.getPaid()));// 数量 * 进价
            tvHouse.setText("利润：" + MathUtil.multiply(mSeekbarMax, MathUtil.subtract(mPlayerGoods.getPrice(), mPlayerGoods.getPaid())));// 市价 - 进价
            seekbar.setProgress(seekbar.getMax());
        }
    }

    public void setSeekBarToMax(ShopGoods shopGoods) {
        if (shopGoods != null) {
            String totalGoodsNum = (MathUtil.divide(mPlayer.getCash(), shopGoods.getPrice()));// 现金最多能买商品个数
            String houseNum = MathUtil.subtract(mPlayer.getHouseTotal(), mPlayer.getHouse());// 房子能装下的商品个数
            MyLog.i("seekbar -> 剩余空间:" + houseNum + ", 能够购买:" + totalGoodsNum);
            MyLog.i("seekbar -> 玩家现金:" + mPlayer.getCash() + ", 物品价格:" + shopGoods.getPrice());
            if (MathUtil.le(houseNum, "0") || MathUtil.le(totalGoodsNum, "0")) {// 当现金连一件商品都买不起的时候  或者  房子已经装不下更多商品时
                mSeekbarMax = "0";
            } else {
                if (MathUtil.ge(houseNum, totalGoodsNum)) {
                    mSeekbarMax = totalGoodsNum;
                } else {
                    mSeekbarMax = houseNum;
                }
            }
            mShopGoodsNum = mSeekbarMax;
            tvHouse.setText("房子：" + (MathUtil.add(mPlayer.getHouse(), mSeekbarMax)) + "/" + mPlayer.getHouseTotal());
            tvCost.setText("花费：" + MathUtil.multiply(mSeekbarMax, mShopGoods.getPrice()));
            seekbar.setProgress(seekbar.getMax());
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

    public interface OnShopListener {
        void onBuySuccess();

        void onBuyError(String msg);

        void onSellSuccess();

        void onSellError(String msg);
    }
}
