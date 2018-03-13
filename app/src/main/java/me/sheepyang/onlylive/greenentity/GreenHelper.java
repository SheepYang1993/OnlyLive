package me.sheepyang.onlylive.greenentity;

import android.content.Context;

import org.greenrobot.greendao.database.Database;

import me.sheepyang.onlylive.greenentity.dao.DaoMaster;
import me.sheepyang.onlylive.greenentity.dao.DaoSession;
import me.sheepyang.onlylive.greenentity.dao.EventDao;
import me.sheepyang.onlylive.greenentity.dao.GoodsDao;
import me.sheepyang.onlylive.greenentity.dao.JoinBadGoodsToEventDao;
import me.sheepyang.onlylive.greenentity.dao.JoinGoodGoodsToEventDao;
import me.sheepyang.onlylive.greenentity.dao.JoinPlayerGoodsToPlayerDao;
import me.sheepyang.onlylive.greenentity.dao.NumberDao;
import me.sheepyang.onlylive.greenentity.dao.PlayerDao;
import me.sheepyang.onlylive.greenentity.dao.PlayerGoodsDao;
import me.sheepyang.onlylive.greenentity.dao.RankDao;
import me.sheepyang.onlylive.greenentity.dao.ShopGoodsDao;
import me.sheepyang.onlylive.utils.MyLog;

/**
 * GreenDao数据库升级助手
 *
 * @author SheepYang
 * @since 2018/3/13 17:11
 */

public class GreenHelper extends DaoMaster.OpenHelper {

    private static DaoMaster daoMaster;
    private static DaoSession daoSession;

    public static final String DBNAME = "only_live_game.db";

    public GreenHelper(Context context) {
        super(context, DBNAME, null);
    }


    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {
        super.onUpgrade(db, oldVersion, newVersion);
        MyLog.i(oldVersion + "---先前和更新之后的版本---" + newVersion);
        if (oldVersion < newVersion) {
            MyLog.i(oldVersion + "---先前和更新之后的版本---" + newVersion);
            MigrationHelper.getInstance().migrate(db, EventDao.class);
            MigrationHelper.getInstance().migrate(db, GoodsDao.class);
            MigrationHelper.getInstance().migrate(db, JoinBadGoodsToEventDao.class);
            MigrationHelper.getInstance().migrate(db, JoinGoodGoodsToEventDao.class);
            MigrationHelper.getInstance().migrate(db, JoinPlayerGoodsToPlayerDao.class);
            MigrationHelper.getInstance().migrate(db, NumberDao.class);
            MigrationHelper.getInstance().migrate(db, PlayerDao.class);
            MigrationHelper.getInstance().migrate(db, PlayerGoodsDao.class);
            MigrationHelper.getInstance().migrate(db, RankDao.class);
            MigrationHelper.getInstance().migrate(db, ShopGoodsDao.class);
            //更改过的实体类(新增的不用加)   更新UserDao文件 可以添加多个  XXDao.class 文件
//             MigrationHelper.getInstance().migrate(db, UserDao.class,XXDao.class);
        }
    }

    /**
     * 取得DaoMaster
     *
     * @param context
     * @return
     */
    public static DaoMaster getDaoMaster(Context context) {
        if (daoMaster == null) {
            DaoMaster.OpenHelper helper = new DaoMaster.DevOpenHelper(context,
                    DBNAME, null);
            daoMaster = new DaoMaster(helper.getWritableDatabase());
        }
        return daoMaster;
    }

    /**
     * 取得DaoSession
     *
     * @param context
     * @return
     */
    public static DaoSession getDaoSession(Context context) {
        if (daoSession == null) {
            if (daoMaster == null) {
                daoMaster = getDaoMaster(context);
            }
            daoSession = daoMaster.newSession();
        }
        return daoSession;
    }
}
