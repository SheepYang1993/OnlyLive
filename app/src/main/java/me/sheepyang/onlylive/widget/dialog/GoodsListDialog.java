package me.sheepyang.onlylive.widget.dialog;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.sheepyang.onlylive.R;
import me.sheepyang.onlylive.adapter.GoodsAdapter;
import me.sheepyang.onlylive.entity.Goods;
import me.sheepyang.onlylive.utils.data.GoodsUtil;
import me.sheepyang.onlylive.widget.recyclerview.NoAlphaItemAnimator;

/**
 * Created by SheepYang on 2016/11/27 17:38.
 */

public class GoodsListDialog extends BaseDialogFragment {
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    private GoodsAdapter mAdapter;
    private List<Goods> mDatas;// 菜单列表数据源
    private OnSelectListener mSelectListener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.dialog_goods_list, null);
        ButterKnife.bind(this, view);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));// 设置背景透明
        initView();
        initListener();
        return view;
    }

    private void initListener() {
        mAdapter.setOnItemClickListener(new GoodsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if (mSelectListener != null) {
                    mSelectListener.onSelect(mDatas.get(position), position);
                }
                dismiss();
            }
        });
    }

    private void initView() {
        //设置布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        // 解决notifyItem闪烁的问题
        recyclerView.setItemAnimator(new NoAlphaItemAnimator());
        //设置适配器
        mAdapter = new GoodsAdapter(getActivity(), mDatas);
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        initData();
    }

    private void initData() {
        if (mDatas != null) {
            mDatas.clear();
        }
        mDatas = GoodsUtil.loadAll();
        mAdapter.update(mDatas);
    }

    public void setOnSelectListener(OnSelectListener selectListener) {
        mSelectListener = selectListener;
    }

    public interface OnSelectListener {
        void onSelect(Goods goods, int position);
    }
}
