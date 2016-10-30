package me.sheepyang.onlylive.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.sheepyang.onlylive.R;
import me.sheepyang.onlylive.adapter.ShopGoodsAdapter;
import me.sheepyang.onlylive.entity.Goods;
import me.sheepyang.onlylive.entity.ShopGoods;
import me.sheepyang.onlylive.utils.MyLog;
import me.sheepyang.onlylive.utils.data.GoodsUtil;
import me.sheepyang.onlylive.utils.data.ShopGoodsUtil;

import static me.sheepyang.onlylive.utils.data.GoodsUtil.getRandomList;

/**
 * Created by SheepYang on 2016/10/27 22:24.
 */

public class ShopGoodsFragment extends BaseFragment {
    @BindView(R.id.listview)
    ListView listview;
    private View rootView;
    private List<ShopGoods> mData;
    private ShopGoodsAdapter mAdapter;
    private OnItemClickListener mListener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_shop_goods, null);
            ButterKnife.bind(this, rootView);
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
        mData = getShopGoods();
        mAdapter = new ShopGoodsAdapter(mContext, mData);
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

    public List<ShopGoods> getShopGoods() {
        List<Goods> goodsList = GoodsUtil.getRandomList(20);
        return ShopGoodsUtil.getShopGoodsList(goodsList);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(ShopGoods shopGoods);
    }
}
