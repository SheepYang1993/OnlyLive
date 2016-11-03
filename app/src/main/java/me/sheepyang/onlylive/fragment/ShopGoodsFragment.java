package me.sheepyang.onlylive.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.sheepyang.onlylive.R;
import me.sheepyang.onlylive.adapter.ShopGoodsAdapter;
import me.sheepyang.onlylive.entity.ShopGoods;

/**
 * Created by SheepYang on 2016/10/27 22:24.
 */

public class ShopGoodsFragment extends BaseFragment {
    @BindView(R.id.listview)
    ListView listview;
    private View rootView;
    private List<ShopGoods> mData;
    private ShopGoodsAdapter mAdapter;
    private OnItemClickListener mItemClickListener;

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
        mAdapter = new ShopGoodsAdapter(mContext, mData);
        listview.setAdapter(mAdapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (mItemClickListener != null) {
                    mItemClickListener.onItemClick(mData.get(position));
                }
            }
        });
    }

    public void setShopGoodsList(List<ShopGoods> shopGoodsList) {
        mData = shopGoodsList;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mItemClickListener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(ShopGoods shopGoods);
    }
}
