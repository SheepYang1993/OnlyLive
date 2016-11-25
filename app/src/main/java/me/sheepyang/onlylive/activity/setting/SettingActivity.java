package me.sheepyang.onlylive.activity.setting;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.sheepyang.onlylive.R;
import me.sheepyang.onlylive.activity.BaseActivity;
import me.sheepyang.onlylive.adapter.SettingAdapter;
import me.sheepyang.onlylive.domain.SettingData;
import me.sheepyang.onlylive.utils.MyLog;
import me.sheepyang.onlylive.widget.recyclerview.NoAlphaItemAnimator;

/**
 * Created by SheepYang on 2016/11/24.
 */

public class SettingActivity extends BaseActivity {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    private SettingAdapter mAdapter;
    private String mTitle;// 标题
    private ArrayList<SettingData> mDatas;// 菜单列表数据源
    private Intent mIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);
        mIntent = getIntent();
        mTitle = mIntent.getStringExtra("title");
        mDatas = mIntent.getParcelableArrayListExtra("data");
        initActionBar(mTitle);
        initView();
        initListener();
    }

    private void initListener() {
        mAdapter.setOnItemClickListener(new SettingAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                SettingData data = mDatas.get(position);
                MyLog.i("选项：" + data.getText() + ", 描述：" + data.getDesc());
                if (!TextUtils.isEmpty(data.getIntentClass())) {
                    try {
                        MyLog.i("IntentClass:" + data.getIntentClass());
                        Class intentClass = Class.forName(data.getIntentClass());
                        Intent intent = new Intent(mContext, intentClass);
                        if (data.getArguments() != null) {
                            intent.putExtras(data.getArguments());
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
        //设置适配器
        mAdapter = new SettingAdapter(this, mDatas);
        recyclerView.setAdapter(mAdapter);
    }

    private void initActionBar(String title) {
        if (TextUtils.isEmpty(title)) {
            title = "设置";
        }
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
