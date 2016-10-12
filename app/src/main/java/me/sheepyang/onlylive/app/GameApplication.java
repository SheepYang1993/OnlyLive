package me.sheepyang.onlylive.app;


import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import me.sheepyang.onlylive.entity.dao.DaoMaster;
import me.sheepyang.onlylive.entity.dao.DaoSession;

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
        mInstances = this;
        // 初始化数据库
        setDatabase();
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
