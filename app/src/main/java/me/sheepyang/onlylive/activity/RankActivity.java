package me.sheepyang.onlylive.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.View;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.sheepyang.onlylive.R;
import me.sheepyang.onlylive.adapter.RankAdapter;
import me.sheepyang.onlylive.greenentity.Rank;
import me.sheepyang.onlylive.utils.data.RankUtil;
import me.sheepyang.onlylive.widget.dialog.MessageDialog;
import me.sheepyang.onlylive.widget.recyclerview.NoAlphaItemAnimator;

/**
 * Created by SheepYang on 2016/11/28.
 */

public class RankActivity extends BaseActivity {
    private static final int RANK_SIZE = 20;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    private RankAdapter mAdapter;
    private List<Rank> mDatas;// 排行榜列表数据源
    private MessageDialog mDeleteDialog;
    private Rank mRank;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rank);
        ButterKnife.bind(this);
        getSupportActionBar().setTitle("排行榜");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initView();
        initListener();
        initData();
    }

    private void initListener() {
        mDeleteDialog.setOnOkClickListener("删除", new MessageDialog.OnOkClickListener() {
            @Override
            public void onClick(View view) {
                deleteRank();
            }
        });
        mDeleteDialog.setOnCancelClickListener("", new MessageDialog.OnCancelClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        mAdapter.setOnItemClickListener(new RankAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                showToast("长按删除记录");
            }
        });
        mAdapter.setOnItemLongClickListener(new RankAdapter.OnItemLongClickListener() {
            @Override
            public void onItemLongClick(View view, int position) {
                mRank = mDatas.get(position);
                mDeleteDialog.setMessage(Html.fromHtml("<br/>确定要删除排行" + (position + 1) + " <font color='#ff435f'>" + mRank.getName() + "</font> 的记录吗？<br/>"));
                mDeleteDialog.show(getSupportFragmentManager(), "DeleteDialog");
            }
        });
    }

    private void deleteRank() {
        if (mRank != null) {
            RankUtil.delete(mRank);
            mDatas = RankUtil.getAllRank(RANK_SIZE);
            mAdapter.updata(mDatas);
            mRank = null;
        }
    }

    private void initData() {
        mDatas = RankUtil.getAllRank(RANK_SIZE);
        mAdapter.updata(mDatas);
    }

    private void initView() {
        mDeleteDialog = new MessageDialog();
        mDeleteDialog.setTitle("删除记录");
        //设置布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        // 解决notifyItem闪烁的问题
        recyclerView.setItemAnimator(new NoAlphaItemAnimator());
        //设置适配器
        mAdapter = new RankAdapter(this, mDatas);
        recyclerView.setAdapter(mAdapter);
    }
}
