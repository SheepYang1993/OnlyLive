package me.sheepyang.onlylive.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.OnClick;
import me.sheepyang.onlylive.R;
import me.sheepyang.onlylive.utils.DataUtil;
import me.sheepyang.onlylive.widget.SelectGameModeDialog;

import static me.sheepyang.onlylive.widget.SelectGameModeDialog.MODE_NEW_GAME;
import static me.sheepyang.onlylive.widget.SelectGameModeDialog.MODE_RESUME;

public class MainActivity extends BaseActivity {

    private SelectGameModeDialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
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
                    case MODE_RESUME:// 继续上一盘游戏
                        startActivity(new Intent(MainActivity.this, GameActivity.class));
                        break;
                    case MODE_NEW_GAME:// 新的游戏
                        DataUtil.initGameData();
                        startActivity(new Intent(MainActivity.this, GameActivity.class));
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
