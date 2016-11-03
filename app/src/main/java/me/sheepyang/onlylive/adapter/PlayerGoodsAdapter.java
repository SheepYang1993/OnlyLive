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
import me.sheepyang.onlylive.entity.PlayerGoods;

/**
 * Created by SheepYang on 2016/10/28 21:26.
 */

public class PlayerGoodsAdapter extends BaseAdapter {

    private Context mContext;
    private List<PlayerGoods> mData;

    public PlayerGoodsAdapter(Context context, List<PlayerGoods> playerGoodsList) {
        mContext = context;
        mData = playerGoodsList;
    }

    @Override
    public int getCount() {
        return mData == null ? 0 : mData.size();
    }

    @Override
    public PlayerGoods getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mData.get(position).getId();
    }

    public void updata(List<PlayerGoods> playerGoodsList) {
        mData = playerGoodsList;
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_player_goods, null);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        PlayerGoods playerGoods = mData.get(position);
        vh.tvName.setText(playerGoods.getName());
        if (playerGoods.getPrice() < 0) {
            vh.tvPrice.setText("有价无市");// 没有市价，表示现在市场不能够出售该物品
        } else {
            vh.tvPrice.setText(playerGoods.getPrice() + "");
        }
        vh.tvNumber.setText(playerGoods.getNumber() + "");
        vh.tvPaid.setText(playerGoods.getPaid() + "");
        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_price)
        TextView tvPrice;
        @BindView(R.id.tv_number)
        TextView tvNumber;
        @BindView(R.id.tv_paid)
        TextView tvPaid;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
