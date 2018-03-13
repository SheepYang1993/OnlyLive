package me.sheepyang.onlylive.app;


import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.tencent.bugly.crashreport.CrashReport;
import com.umeng.message.IUmengRegisterCallback;
import com.umeng.message.PushAgent;

import cn.bmob.v3.Bmob;
import me.sheepyang.onlylive.greenentity.dao.DaoMaster;
import me.sheepyang.onlylive.greenentity.dao.DaoSession;
import me.sheepyang.onlylive.utils.MyLog;

/**
 * Created by SheepYang on 2016/10/13 00:30.
 */

public class GameApplication extends Application {
    private DaoMaster.DevOpenHelper mHelper;
    private SQLiteDatabase db;
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;
    private static GameApplication mInstances;

    @Override
    public void onCreate() {
        super.onCreate();
        // 初始化Bugly
        // 第三个参数为SDK调试模式开关，调试模式的行为特性如下：
        // ● 输出详细的Bugly SDK的Log；
        // ● 每一条Crash都会被立即上报；
        // ● 自定义日志将会在Logcat中输出。
        // 建议在测试阶段建议设置成true，发布时设置为false。
        CrashReport.initCrashReport(getApplicationContext(), Constants.BUGLY_ID, true);
        // 初始化Bmob
        Bmob.initialize(this, Constants.BMOB_APP_ID);
        initUMeng();
        mInstances = this;
        // 初始化数据库
        setDatabase();
    }

    private void initUMeng() {
        final PushAgent mPushAgent = PushAgent.getInstance(this);
//        mPushAgent.setDebugMode(false);// 正式打包设置为false
        new Thread() {
            @Override
            public void run() {
                //注册推送服务，每次调用register方法都会回调该接口
                mPushAgent.register(new IUmengRegisterCallback() {

                    @Override
                    public void onSuccess(String deviceToken) {
                        //注册成功会返回device token
                        MyLog.i("友盟推送注册成功！device token：" + deviceToken);
                    }

                    @Override
                    public void onFailure(String s, String s1) {
                        MyLog.i("友盟推送注册失败！" + s + ", " + s1);
                    }
                });
            }
        }.start();
    }

    public static GameApplication getInstances() {
        return mInstances;
    }

    /**
     * 设置greenDao
     */
    private void setDatabase() {
        // 通过 DaoMaster 的内部类 DevOpenHelper，你可以得到一个便利的 SQLiteOpenHelper 对象。
        // 可能你已经注意到了，你并不需要去编写「CREATE TABLE」这样的 SQL 语句，因为 greenDAO 已经帮你做了。
        // 注意：默认的 DaoMaster.DevOpenHelper 会在数据库升级时，删除所有的表，意味着这将导致数据的丢失。
        // 所以，在正式的项目中，你还应该做一层封装，来实现数据库的安全升级。
        mHelper = new DaoMaster.DevOpenHelper(this, "only-live-game-db", null);
        db = mHelper.getWritableDatabase();
        // 注意：该数据库连接属于 DaoMaster，所以多个 Session 指的是相同的数据库连接。
        mDaoMaster = new DaoMaster(db);
        mDaoSession = mDaoMaster.newSession();
    }

    public DaoSession getDaoSession() {
        return mDaoSession;
    }

    public SQLiteDatabase getDb() {
        return db;
    }
}
