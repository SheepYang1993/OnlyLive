package me.sheepyang.onlylive.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.umeng.socialize.UMShareAPI;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.listener.BmobDialogButtonListener;
import cn.bmob.v3.listener.BmobUpdateListener;
import cn.bmob.v3.update.BmobUpdateAgent;
import cn.bmob.v3.update.UpdateResponse;
import cn.bmob.v3.update.UpdateStatus;
import me.sheepyang.onlylive.R;
import me.sheepyang.onlylive.activity.setting.EventListActivity;
import me.sheepyang.onlylive.activity.setting.GoodsListActivity;
import me.sheepyang.onlylive.activity.setting.InitGameDataActivity;
import me.sheepyang.onlylive.activity.setting.SettingActivity;
import me.sheepyang.onlylive.domain.SettingData;
import me.sheepyang.onlylive.utils.AppManager;
import me.sheepyang.onlylive.utils.CacheUtil;
import me.sheepyang.onlylive.utils.DataUtil;
import me.sheepyang.onlylive.utils.MyLog;
import me.sheepyang.onlylive.utils.data.PlayerUtil;
import me.sheepyang.onlylive.widget.dialog.SelectGameModeDialog;

import static me.sheepyang.onlylive.widget.dialog.SelectGameModeDialog.MODE_NEW_GAME;
import static me.sheepyang.onlylive.widget.dialog.SelectGameModeDialog.MODE_RESUME;

public class MainActivity extends BaseActivity {

    private static final int SETTING = 1000;
    private static final int SETTING_GAME_CONFIG = SETTING + 1;
    private static final int SETTING_EVENT_LIST = SETTING + 2;
    private static final int SETTING_GOODS_LIST = SETTING + 3;
    @BindView(R.id.iv_logo)
    ImageView ivLogo;
    private SelectGameModeDialog mDialog;
    private boolean isClickUpdate;
    private long mCurrentTime;
    private long mLogoDoubleClickTime;
    private int mLogoLongClickTimes = 0;// Logo长按次数
    private Runnable runable = new Runnable() {
        @Override
        public void run() {
            mLogoLongClickTimes = 0;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        // 初始化游戏数据
        if (!CacheUtil.isInit(mContext)) {
            DataUtil.initGame(mContext);
        }
        initView();
        initListener();
        BmobUpdateAgent.update(mContext);
    }

    private void initListener() {
        ivLogo.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                mLogoLongClickTimes++;
                MyLog.i("长按了" + mLogoLongClickTimes + "次");
                ivLogo.removeCallbacks(runable);
                ivLogo.postDelayed(runable, 3000);
                return true;
            }
        });
        //设置对对话框按钮的点击事件的监听
        BmobUpdateAgent.setDialogListener(new BmobDialogButtonListener() {

            @Override
            public void onClick(int status) {
                switch (status) {
                    case UpdateStatus.Update:
//                        showToast("点击了立即更新按钮");
                        break;
                    case UpdateStatus.NotNow:
//                        showToast("点击了以后再说按钮");
                        break;
                    case UpdateStatus.Close://只有在强制更新状态下才会在更新对话框的右上方出现close按钮,如果用户不点击”立即更新“按钮，这时候开发者可做些操作，比如直接退出应用等
                        finish();
                        break;
                }
            }
        });
        BmobUpdateAgent.setUpdateListener(new BmobUpdateListener() {

            @Override
            public void onUpdateReturned(int updateStatus, UpdateResponse updateInfo) {
                if (updateStatus == UpdateStatus.Yes) {//版本有更新

                } else if (updateStatus == UpdateStatus.No) {
                    if (isClickUpdate) {
                        showToast("当前是最新版本");
                    }
                } else if (updateStatus == UpdateStatus.EmptyField) {//此提示只是提醒开发者关注那些必填项，测试成功后，无需对用户提示
//                    showToast("请检查你AppVersion表的必填项，1、target_size（文件大小）是否填写；2、path或者android_url两者必填其中一项。");
                } else if (updateStatus == UpdateStatus.IGNORED) {
//                    showToast("该版本已被忽略更新");
                } else if (updateStatus == UpdateStatus.ErrorSizeFormat) {
//                    showToast("请检查target_size填写的格式，请使用file.length()方法获取apk大小。");
                } else if (updateStatus == UpdateStatus.TimeOut) {
//                    showToast("查询出错或查询超时");
                }
                isClickUpdate = false;
            }
        });
        mDialog.setOnSelectListener(new SelectGameModeDialog.OnSelectListener() {
            @Override
            public void OnSelect(View view, int mode) {
                switch (mode) {
                    case MODE_NEW_GAME:// 新的游戏
                        PlayerUtil.deletePlayer();
                        PlayerUtil.initPlayerData(mContext);
                        startActivity(new Intent(MainActivity.this, GameActivity.class));
                        break;
                    case MODE_RESUME:// 继续上一盘游戏
                        if (PlayerUtil.getPlayer() != null && !PlayerUtil.getPlayer().getIsFirst()) {
                            startActivity(new Intent(MainActivity.this, GameActivity.class));
                        } else {
                            showToast("没有游戏记录，请重新开始一盘游戏");
                        }
                        break;
                    default:
                        break;
                }
            }
        });
    }

    private void initView() {
        if (mDialog == null) {
            mDialog = new SelectGameModeDialog(this);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

    @OnClick({R.id.iv_logo, R.id.btn_play, R.id.btn_update, R.id.btn_rank, R.id.btn_setting, R.id.btn_share})
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.iv_logo:// 三次长按，一次双击 开启隐藏设置
                if (System.currentTimeMillis() - mLogoDoubleClickTime < 2000) {
                    mLogoDoubleClickTime = 0;
                    if (mLogoLongClickTimes == 3) {
                        mLogoLongClickTimes = 0;
                        MyLog.i("开启隐藏设置");
                        showToast("开启隐藏设置");

                        Intent intent = new Intent(mContext, SettingActivity.class);
                        intent.putExtra("title", "隐藏设置");

                        ArrayList<SettingData> dataList = new ArrayList<>();
                        dataList.add(initSettingData(SETTING_GAME_CONFIG));
                        dataList.add(initSettingData(SETTING_EVENT_LIST));
                        dataList.add(initSettingData(SETTING_GOODS_LIST));
                        intent.putParcelableArrayListExtra("data", dataList);

                        startActivity(intent);
                    }
                } else {
                    mLogoDoubleClickTime = System.currentTimeMillis();
                }
                break;
            case R.id.btn_play:// 开始游戏
                mDialog.show();
                break;
            case R.id.btn_rank:
            case R.id.btn_setting:
            case R.id.btn_share:
                showToast("          暂未开放\n期待下一个版本吧~");
                break;
            case R.id.btn_update:// 检测版本
                if (!isClickUpdate) {
                    isClickUpdate = true;
                    BmobUpdateAgent.forceUpdate(mContext);
                }
                break;
            default:
                break;
        }
    }

    private SettingData initSettingData(int type) {
        SettingData data = null;
        switch (type) {
            case SETTING_GAME_CONFIG:// 初始游戏数据
                data = new SettingData();
                data.setText("初始游戏数据");
                data.setDesc("可以查看修改游戏初始化数值");
                data.setIntentClass(InitGameDataActivity.class.getName());
                break;
            case SETTING_EVENT_LIST:// 事件列表
                data = new SettingData();
                data.setText("事件列表");
                data.setDesc("游戏中出现的所有突发事件");
                data.setIntentClass(EventListActivity.class.getName());
                break;
            case SETTING_GOODS_LIST:// 物品列表
                data = new SettingData();
                data.setText("物品列表");
                data.setDesc("商店中能够购买到的所有物品");
                data.setIntentClass(GoodsListActivity.class.getName());
                break;
            default:
                break;
        }
        return data;
    }

    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() - mCurrentTime < 2000) {
            mCurrentTime = 0;
            AppManager.getAppManager().AppExit(mContext);
        } else {
            mCurrentTime = System.currentTimeMillis();
            showToast("再次点击退出APP");
        }
    }
}
