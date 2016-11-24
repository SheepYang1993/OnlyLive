package me.sheepyang.onlylive.activity;

import android.os.Bundle;
import android.view.MenuItem;

import butterknife.ButterKnife;
import me.sheepyang.onlylive.R;

/**
 * Created by SheepYang on 2016/11/24.
 */

public class HideSettingActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hide_setting);
        ButterKnife.bind(this);
        initActionBar();
    }

    private void initActionBar() {
        getSupportActionBar().setTitle("隐藏设置");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
}
