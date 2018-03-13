package me.sheepyang.onlylive.utils.data;

import android.content.Context;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

import me.sheepyang.onlylive.app.GameApplication;
import me.sheepyang.onlylive.greenentity.Player;
import me.sheepyang.onlylive.greenentity.dao.PlayerDao;
import me.sheepyang.onlylive.utils.CacheUtil;

/**
 * Created by SheepYang on 2016/10/22 17:50.
 */

public class PlayerUtil {
    private static PlayerDao mPlayerDao;

    static {
        mPlayerDao = GameApplication.getInstances().getDaoSession().getPlayerDao();
    }

    public static Player getPlayer() {
        Player player = null;
        QueryBuilder<Player> qb = mPlayerDao.queryBuilder();
        qb.where(PlayerDao.Properties.Id.eq(1));
        List<Player> list = qb.list();
        if (list != null && list.size() > 0) {
            player = list.get(0);
        }
        return player;
    }

    public static void setPlayer(Player player) {
        mPlayerDao.insertOrReplace(player);
    }

    public static void deletePlayer() {
        mPlayerDao.deleteByKey((long) 1);
    }

    /**
     * 初始化玩家數據
     */
    public static void initPlayerData(Context context) {
        Player player = getPlayer();
        if (player == null) {
            player = new Player();
            player.setId(Long.valueOf(1));
        }
        player.setIsFirst(true);
        player.setCity("");
        player.setCash(CacheUtil.getInitGameCash(context));// 现金
        player.setDebt(CacheUtil.getInitGameDebt(context));// 负债
        player.setDeposit(CacheUtil.getInitGameDeposit(context));// 存款
        player.setHealth(CacheUtil.getInitGameHealth(context));// 健康
        player.setHouse(CacheUtil.getInitGameHouse(context));// 房子
        player.setWeek(CacheUtil.getInitGameWeek(context));// 周数
        player.setHouseTotal(CacheUtil.getInitGameHouseTotal(context));// 总房子
        player.setWeekTotal(CacheUtil.getInitGameWeekTotal(context));// 总周数
        mPlayerDao.insertOrReplace(player);
        PlayerGoodsUtil.deleteAll();// 清除玩家背包数据
    }
}
