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
import me.sheepyang.onlylive.adapter.EventAdapter;
import me.sheepyang.onlylive.entity.Event;
import me.sheepyang.onlylive.utils.data.EventUtil;
import me.sheepyang.onlylive.utils.data.PlayerUtil;
import me.sheepyang.onlylive.widget.dialog.EventDialog;
import me.sheepyang.onlylive.widget.dialog.GoodsDialog;
import me.sheepyang.onlylive.widget.dialog.MessageDialog;
import me.sheepyang.onlylive.widget.recyclerview.NoAlphaItemAnimator;

/**
 * Created by SheepYang on 2016/11/25.
 */

public class EventListActivity extends BaseActivity {
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    private EventAdapter mAdapter;
    private String mTitle;// 标题
    private List<Event> mDatas;// 菜单列表数据源
    private Intent mIntent;
    private Event mEvent;
    private MessageDialog mDeleteDialog;
    private EventDialog mAddEventDialog;
    private EventDialog mModifyEventDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_list);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        setBackBarTitle("事件列表");
        initView();
        initListener();
        initData();
    }

    private void initListener() {
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAddEventDialog.show(getSupportFragmentManager(), "EventDialog");
            }
        });
        mAdapter.setOnItemClickListener(new EventAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                showToast("长按删除该事件");
                mModifyEventDialog.setEvent(mDatas.get(position));
                mModifyEventDialog.show(getSupportFragmentManager(), "ModifyEventDialog");
            }
        });
        mAdapter.setOnItemLongClickListener(new EventAdapter.OnItemLongClickListener() {
            @Override
            public void onItemLongClick(View view, int position) {
                mEvent = mDatas.get(position);
                mDeleteDialog.setMessage(Html.fromHtml("<br/>确定要删除 <font color='#ff435f'>" + mEvent.getTitle() + "</font> 吗？<br/>"));
                mDeleteDialog.show(getSupportFragmentManager(), "DeleteDialog");
            }
        });
        mAddEventDialog.setOnSaveListener(new EventDialog.SaveListener() {
            @Override
            public void onSuccess() {
                showToast("添加成功，游戏记录被清除");
                initData();
                PlayerUtil.initPlayerData(mContext);
            }
        });
        mModifyEventDialog.setOnSaveListener(new EventDialog.SaveListener() {
            @Override
            public void onSuccess() {
                showToast("修改成功，游戏记录被清除");
                initData();
                PlayerUtil.initPlayerData(mContext);
            }
        });
        mDeleteDialog.setOnOkClickListener("删除", new MessageDialog.OnOkClickListener() {
            @Override
            public void onClick(View view) {
                deleteEvent();
            }
        });
        mDeleteDialog.setOnCancelClickListener("取消", new MessageDialog.OnCancelClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    private void deleteEvent() {
        if (mEvent != null) {
            mDatas.remove(mEvent);
            EventUtil.delete(mEvent);
            mAdapter.update(mDatas);
        }
    }

    private void initData() {
        if (mDatas != null) {
            mDatas.clear();
        }
        mDatas = EventUtil.loadAll();
        mAdapter.update(mDatas);
    }

    private void initView() {
        mModifyEventDialog = new EventDialog();
        mModifyEventDialog.setTitle("修改事件");
        mModifyEventDialog.setHint("修改事件会清空游戏记录，请谨慎修改");
        mAddEventDialog = new EventDialog();
        mDeleteDialog = new MessageDialog();
        mDeleteDialog.setTitle("删除事件");
        //设置布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        // 解决notifyItem闪烁的问题
        recyclerView.setItemAnimator(new NoAlphaItemAnimator());
        //设置适配器
        mAdapter = new EventAdapter(this, mDatas);
        recyclerView.setAdapter(mAdapter);
    }
}
