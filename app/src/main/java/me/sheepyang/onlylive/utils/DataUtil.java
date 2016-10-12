package me.sheepyang.onlylive.utils;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

import me.sheepyang.onlylive.app.Constant;
import me.sheepyang.onlylive.app.GameApplication;
import me.sheepyang.onlylive.entity.Player;
import me.sheepyang.onlylive.entity.dao.GoodsDao;
import me.sheepyang.onlylive.entity.dao.PlayerDao;

import static me.sheepyang.onlylive.app.Constant.INIT_GAME_CASH;

/**
 * 游戏数据相关工具类
 * Created by SheepYang on 2016/10/13 00:24.
 */
public class DataUtil {
    private static PlayerDao mPlayerDao;
    private static GoodsDao mGoodsDao;

    static {
        mPlayerDao = GameApplication.getInstances().getDaoSession().getPlayerDao();
        mGoodsDao = GameApplication.getInstances().getDaoSession().getGoodsDao();
    }

    public static void initGameData() {
        initPlayerData();
        initGoodsData();
        initEventData();
        initNewsData();
    }

    public static Player getPlayerData() {
        QueryBuilder<Player> qb = mPlayerDao.queryBuilder();
        qb.where(PlayerDao.Properties.Id.eq(1));
        List<Player> list = qb.list();
        if (list != null && list.size() > 0) {
            return list.get(0);
        } else {
            return null;
        }
    }

    /**
     * 初始化玩家數據
     */
    private static void initPlayerData() {
        Player player = new Player();
        player.setId(1);
        player.setCash(Constant.INIT_GAME_CASH);// 现金
        player.setDebt(Constant.INIT_GAME_DEBT);// 负债
        player.setDeposit(Constant.INIT_GAME_DEPOSIT);// 存款
        player.setHealth(Constant.INIT_GAME_HEALTH);// 健康
        player.setHouse(Constant.INIT_GAME_HOUSE);// 房子
        player.setWeek(Constant.INIT_GAME_WEEK);// 周数
        mPlayerDao.insertOrReplace(player);
    }

    /**
     * 初始化所有物品信息
     */
    private static void initGoodsData() {

    }

    /**
     * 初始化所有新聞事件
     */
    private static void initNewsData() {

    }

    /**
     * 初始化所有突發事件
     */
    private static void initEventData() {

    }
}
