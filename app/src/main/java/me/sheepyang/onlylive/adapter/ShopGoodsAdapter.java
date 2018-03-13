package me.sheepyang.onlylive.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.sheepyang.onlylive.R;
import me.sheepyang.onlylive.greenentity.ShopGoods;

/**
 * Created by SheepYang on 2016/10/28 21:26.
 */

public class ShopGoodsAdapter extends BaseAdapter {

    private Context mContext;
    private List<ShopGoods> mData;

    public ShopGoodsAdapter(Context context, List<ShopGoods> shopGoodsList) {
        mContext = context;
        mData = shopGoodsList;
    }

    @Override
    public int getCount() {
        return mData == null ? 0 : mData.size();
    }

    @Override
    public ShopGoods getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mData.get(position).getId();
    }

    public void updata(List<ShopGoods> shopGoodsList) {
        mData = shopGoodsList;
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_shop_goods, null);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        ShopGoods shopGoods = mData.get(position);
        vh.tvName.setText(shopGoods.getName());
        vh.tvPrice.setText(shopGoods.getPrice() + "");
        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_price)
        TextView tvPrice;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
