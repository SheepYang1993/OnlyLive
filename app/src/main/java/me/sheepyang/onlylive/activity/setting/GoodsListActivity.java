package me.sheepyang.onlylive.activity.setting;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.sheepyang.onlylive.R;
import me.sheepyang.onlylive.activity.BaseActivity;
import me.sheepyang.onlylive.adapter.GoodsAdapter;
import me.sheepyang.onlylive.entity.Goods;
import me.sheepyang.onlylive.utils.data.GoodsUtil;
import me.sheepyang.onlylive.widget.dialog.AddGoodsDialog;
import me.sheepyang.onlylive.widget.dialog.MessageDialog;
import me.sheepyang.onlylive.widget.recyclerview.NoAlphaItemAnimator;

/**
 * Created by SheepYang on 2016/11/25.
 */

public class GoodsListActivity extends BaseActivity {
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    private GoodsAdapter mAdapter;
    private String mTitle;// 标题
    private List<Goods> mDatas;// 菜单列表数据源
    private Intent mIntent;
    private MessageDialog mDeleteDialog;
    private AddGoodsDialog mAddGoodsDialog;
    private Goods mGoods;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_list);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        setBackBarTitle("物品列表");
        initView();
        initListener();
        initData();
    }

    private void initListener() {
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAddGoodsDialog.show(getSupportFragmentManager(), "AddGoodsDialog");
            }
        });
        mAdapter.setOnItemClickListener(new GoodsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                showToast("长按删除  " + mDatas.get(position).getName());
            }
        });
        mAdapter.setOnItemLongClickListener(new GoodsAdapter.OnItemLongClickListener() {
            @Override
            public void onItemLongClick(View view, int position) {
                mGoods = mDatas.get(position);
                mDeleteDialog.setMessage(Html.fromHtml("<br/>确定要删除 <font color='#ff435f'>" + mGoods.getName() + "</font> 吗？<br/>"));
                mDeleteDialog.show(getSupportFragmentManager(), "DeleteDialog");
            }
        });
        mDeleteDialog.setOnOkClickListener("删除", new MessageDialog.OnOkClickListener() {
            @Override
            public void onClick(View view) {
                deleteGoods();
            }
        });
        mDeleteDialog.setOnCancelClickListener("取消", new MessageDialog.OnCancelClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    private void deleteGoods() {
        if (mGoods != null) {
            mDatas.remove(mGoods);
            GoodsUtil.delete(mGoods);
            mGoods = null;
            mAdapter.update(mDatas);
        }
    }

    private void initData() {
        mDatas = GoodsUtil.loadAll();
        mDatas.addAll(GoodsUtil.loadAll());
        mDatas.addAll(GoodsUtil.loadAll());
        mAdapter.update(mDatas);
    }

    private void initView() {
        mAddGoodsDialog = new AddGoodsDialog();
        mDeleteDialog = new MessageDialog();
        mDeleteDialog.setTitle("删除物品");
        //设置布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        // 解决notifyItem闪烁的问题
        recyclerView.setItemAnimator(new NoAlphaItemAnimator());
        //设置适配器
        mAdapter = new GoodsAdapter(this, mDatas);
        recyclerView.setAdapter(mAdapter);
    }
}
