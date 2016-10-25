package me.sheepyang.onlylive.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.OnClick;
import me.sheepyang.onlylive.R;
import me.sheepyang.onlylive.utils.DataUtil;
import me.sheepyang.onlylive.utils.SPUtil;
import me.sheepyang.onlylive.utils.data.PlayerUtil;
import me.sheepyang.onlylive.widget.dialog.SelectGameModeDialog;

import static me.sheepyang.onlylive.widget.dialog.SelectGameModeDialog.MODE_NEW_GAME;
import static me.sheepyang.onlylive.widget.dialog.SelectGameModeDialog.MODE_RESUME;

public class MainActivity extends BaseActivity {

    private SelectGameModeDialog mDialog;
    private boolean isInit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        // 初始化游戏数据
        isInit = SPUtil.getBoolean(mContext, "isInit", false);
        if (!isInit) {
            DataUtil.initGameData();
            SPUtil.putBoolean(mContext, "isInit", true);
        }
        initView();
    }

    private void initView() {
        if (mDialog == null) {
            mDialog = new SelectGameModeDialog(this);
        }
        mDialog.setOnSelectListener(new SelectGameModeDialog.OnSelectListener() {
            @Override
            public void OnSelect(View view, int mode) {
                switch (mode) {
                    case MODE_NEW_GAME:// 新的游戏
                        PlayerUtil.initPlayerData();
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

    @OnClick({R.id.ll_play})
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.ll_play:// 开始游戏
                mDialog.show();
                break;
            default:
                break;
        }
    }
}
