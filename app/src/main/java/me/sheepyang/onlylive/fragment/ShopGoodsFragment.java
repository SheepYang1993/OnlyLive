package me.sheepyang.onlylive.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.sheepyang.onlylive.R;
import me.sheepyang.onlylive.entity.Goods;
import me.sheepyang.onlylive.entity.ShopGoods;
import me.sheepyang.onlylive.utils.data.GoodsUtil;

import static me.sheepyang.onlylive.utils.data.GoodsUtil.getRandomList;

/**
 * Created by SheepYang on 2016/10/27 22:24.
 */

public class ShopGoodsFragment extends Fragment {
    @BindView(R.id.listview)
    ListView listview;
    private View rootView;
    private List<ShopGoods> mData;

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
        mData = new ArrayList<>();
        getShopGoods();
    }

    public void getShopGoods() {
        List<Goods> goodsList = GoodsUtil.getRandomList(20);
        List<ShopGoods> shopGoodsList = new ArrayList<>();
        for (Goods goods : goodsList) {

        }
    }
}
