package me.sheepyang.onlylive.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.sheepyang.onlylive.R;
import me.sheepyang.onlylive.adapter.SettingAdapter;
import me.sheepyang.onlylive.domain.SettingData;
import me.sheepyang.onlylive.widget.recyclerview.NoAlphaItemAnimator;

/**
 * Created by SheepYang on 2016/11/24.
 */

public class SettingActivity extends BaseActivity {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    private SettingAdapter mAdapter;
    private String mTitle;// 标题
    private boolean mIsShowBack;// 是否显示返回键
    private ArrayList<SettingData> mDatas;// 菜单列表数据源
    private Intent mIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);
        mIntent = getIntent();
        mTitle = mIntent.getStringExtra("title");
        mIsShowBack = mIntent.getBooleanExtra("isShowBack", false);
        mDatas = mIntent.getParcelableArrayListExtra("Datas");
        initActionBar();
        initView();
        initListener();
    }

    private void initListener() {
        mAdapter.setOnItemClickListener(new SettingAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                showToast("选项：" + mDatas.get(position).getText() + ", 描述：" + mDatas.get(position).getDesc());
                if (!TextUtils.isEmpty(mDatas.get(position).getIntentClass())) {
                    try {
                        Class intentClass = Class.forName(mDatas.get(position).getIntentClass());
                        Intent intent = new Intent(mContext, intentClass);
                        if (mDatas.get(position).getArguments() != null) {
                            intent.putExtras(mDatas.get(position).getArguments());
                        }
                        startActivity(intent);
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    private void initView() {
        //设置布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        // 解决notifyItem闪烁的问题
        recyclerView.setItemAnimator(new NoAlphaItemAnimator());
        if (mDatas != null && mDatas.size() > 0) {
            //设置适配器
            mAdapter = new SettingAdapter(this, mDatas);
            recyclerView.setAdapter(mAdapter);
        }
    }

    private void initActionBar() {
        if (TextUtils.isEmpty(mTitle)) {
            mTitle = "设置";
        }
        getSupportActionBar().setTitle(mTitle);
        if (mIsShowBack) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:// 点击返回图标事件
                onBackPressed();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public interface OnItemLongClickListener {
        void onItemLongClick(View view, int position);
    }
}
