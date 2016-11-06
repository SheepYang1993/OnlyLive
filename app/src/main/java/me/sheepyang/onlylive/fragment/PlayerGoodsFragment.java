package me.sheepyang.onlylive.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.sheepyang.onlylive.R;
import me.sheepyang.onlylive.adapter.PlayerGoodsAdapter;
import me.sheepyang.onlylive.entity.PlayerGoods;
import me.sheepyang.onlylive.entity.ShopGoods;
import me.sheepyang.onlylive.utils.data.PlayerGoodsUtil;

/**
 * Created by SheepYang on 2016/10/27 22:24.
 */

public class PlayerGoodsFragment extends BaseFragment {

    @BindView(R.id.tv_no_data)
    TextView tvNoData;
    @BindView(R.id.listview)
    ListView listview;
    private View rootView;
    private List<PlayerGoods> mData;
    private PlayerGoodsAdapter mAdapter;
    private OnItemClickListener mListener;
    private List<ShopGoods> mShopGoodsList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_shop_goods, null);
            ButterKnife.bind(this, rootView);
            setPlayerGoodsPrice();// 设置物品的市价
            initData();
        }
        ViewGroup parent = (ViewGroup) rootView.getParent();
        if (parent != null) {
            parent.removeView(rootView);
        }
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    private void initData() {
        mData = getPlayerGoods();
        if (mData == null || mData.size() <= 0) {
            listview.setVisibility(View.GONE);
            tvNoData.setVisibility(View.VISIBLE);
        } else {
            listview.setVisibility(View.VISIBLE);
            tvNoData.setVisibility(View.GONE);
        }
        mAdapter = new PlayerGoodsAdapter(mContext, mData);
        listview.setAdapter(mAdapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (mListener != null) {
                    mListener.onItemClick(mData.get(position));
                }
            }
        });
    }

    public List<PlayerGoods> getPlayerGoods() {
        return PlayerGoodsUtil.loadAll();
    }

    public void refreshData() {
        mData = getPlayerGoods();
        if (mData == null || mData.size() <= 0) {
            listview.setVisibility(View.GONE);
            tvNoData.setVisibility(View.VISIBLE);
        } else {
            listview.setVisibility(View.VISIBLE);
            tvNoData.setVisibility(View.GONE);
        }
        mAdapter.updata(mData);
        if (mData == null || mData.size() <= 0) {
            tvNoData.setVisibility(View.VISIBLE);
            listview.setVisibility(View.GONE);
        } else {
            tvNoData.setVisibility(View.GONE);
            listview.setVisibility(View.VISIBLE);
        }
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    /**
     * 设置玩家物品的市价
     */
    private void setPlayerGoodsPrice() {
        PlayerGoodsUtil.clearPrice();
        PlayerGoodsUtil.setPrice(mShopGoodsList);
    }

    public void setShopGoodsList(List<ShopGoods> shopGoodsList) {
        mShopGoodsList = shopGoodsList;
    }

    public interface OnItemClickListener {
        void onItemClick(PlayerGoods playerGoods);
    }
}
