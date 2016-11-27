package me.sheepyang.onlylive.activity.setting;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.sheepyang.onlylive.R;
import me.sheepyang.onlylive.activity.BaseActivity;
import me.sheepyang.onlylive.adapter.SettingAdapter;
import me.sheepyang.onlylive.domain.SettingData;
import me.sheepyang.onlylive.utils.CacheUtil;
import me.sheepyang.onlylive.utils.data.PlayerUtil;
import me.sheepyang.onlylive.widget.dialog.ModifyPlayerDataDialog;
import me.sheepyang.onlylive.widget.recyclerview.NoAlphaItemAnimator;

/**
 * Created by SheepYang on 2016/11/25.
 */

public class InitGameDataActivity extends BaseActivity {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    private SettingAdapter mAdapter;
    private ArrayList<SettingData> mDatas;// 菜单列表数据源
    private Intent mIntent;
    private ModifyPlayerDataDialog mModifyPlayerDataDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_init_game_data);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        setBackBarTitle("初始参数设置");
        mIntent = getIntent();
        initView();
        initListener();
        initData();
    }

    private void initListener() {
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mModifyPlayerDataDialog.show(getSupportFragmentManager(), "EventDialog");
            }
        });
        mModifyPlayerDataDialog.setOnSaveListener(new ModifyPlayerDataDialog.SaveListener() {
            @Override
            public void onSuccess() {
                showToast("修改成功，游戏记录被清除");
                initData();
                PlayerUtil.initPlayerData(mContext);
            }
        });
    }

    private void initData() {
        if (mDatas == null) {
            mDatas = new ArrayList<>();
        }
        mDatas.clear();
        SettingData data;
        data = new SettingData();
        data.setText("现金");
        data.setDesc("初始现金：" + CacheUtil.getInitGameCash(mContext));
        mDatas.add(data);

        data = new SettingData();
        data.setText("负债");
        data.setDesc("初始负债：" + CacheUtil.getInitGameDebt(mContext));
        mDatas.add(data);

        data = new SettingData();
        data.setText("存款");
        data.setDesc("初始存款：" + CacheUtil.getInitGameDeposit(mContext));
        mDatas.add(data);

        data = new SettingData();
        data.setText("健康");
        data.setDesc("初始健康：" + CacheUtil.getInitGameHealth(mContext));
        mDatas.add(data);

        data = new SettingData();
        data.setText("使用空间");
        data.setDesc("初始已使用空间：" + CacheUtil.getInitGameHouse(mContext));
        mDatas.add(data);

        data = new SettingData();
        data.setText("总房子数量");
        data.setDesc("初始总房子数量：" + CacheUtil.getInitGameHouseTotal(mContext));
        mDatas.add(data);

        data = new SettingData();
        data.setText("当前周数");
        data.setDesc("初始当前周数：" + CacheUtil.getInitGameWeek(mContext));
        mDatas.add(data);

        data = new SettingData();
        data.setText("游戏总周数");
        data.setDesc("初始游戏总周数：" + CacheUtil.getInitGameWeekTotal(mContext));
        mDatas.add(data);

        data = new SettingData();
        data.setText("商店出售物品数");
        data.setDesc("物品数量：" + CacheUtil.getInitGameShopGoodsNumber(mContext));
        mDatas.add(data);
        mAdapter.updata(mDatas);
    }

    private void initView() {
        mModifyPlayerDataDialog = new ModifyPlayerDataDialog();
        //设置布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        // 解决notifyItem闪烁的问题
        recyclerView.setItemAnimator(new NoAlphaItemAnimator());
        mDatas = new ArrayList<>();
        //设置适配器
        mAdapter = new SettingAdapter(this, mDatas);
        recyclerView.setAdapter(mAdapter);
    }
}
