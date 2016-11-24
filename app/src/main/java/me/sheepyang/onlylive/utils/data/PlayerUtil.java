package me.sheepyang.onlylive.utils.data;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

import me.sheepyang.onlylive.app.Constants;
import me.sheepyang.onlylive.app.GameApplication;
import me.sheepyang.onlylive.entity.Player;
import me.sheepyang.onlylive.entity.dao.PlayerDao;

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
            player =  list.get(0);
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
    public static void initPlayerData() {
        Player player = getPlayer();
        if (player == null) {
            player = new Player();
            player.setId(Long.valueOf(1));
        }
        player.setIsFirst(true);
        player.setCity("");
        player.setCash(Constants.INIT_GAME_CASH);// 现金
        player.setDebt(Constants.INIT_GAME_DEBT);// 负债
        player.setDeposit(Constants.INIT_GAME_DEPOSIT);// 存款
        player.setHealth(Constants.INIT_GAME_HEALTH);// 健康
        player.setHouse(Constants.INIT_GAME_HOUSE);// 房子
        player.setWeek(Constants.INIT_GAME_WEEK);// 周数
        player.setHouseTotal(Constants.INIT_GAME_HOUSE_TOTAL);// 总房子
        player.setWeekTotal(Constants.INIT_GAME_WEEK_TOTAL);// 总周数
        mPlayerDao.insertOrReplace(player);
        PlayerGoodsUtil.deleteAll();// 清除玩家背包数据
    }
}
