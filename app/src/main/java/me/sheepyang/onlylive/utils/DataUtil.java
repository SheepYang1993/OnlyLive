package me.sheepyang.onlylive.utils;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

import me.sheepyang.onlylive.app.Constants;
import me.sheepyang.onlylive.app.GameApplication;
import me.sheepyang.onlylive.entity.Event;
import me.sheepyang.onlylive.entity.EventGoods;
import me.sheepyang.onlylive.entity.JoinPlayerGoodsToPlayer;
import me.sheepyang.onlylive.entity.Number;
import me.sheepyang.onlylive.entity.Player;
import me.sheepyang.onlylive.entity.PlayerGoods;
import me.sheepyang.onlylive.entity.dao.EventDao;
import me.sheepyang.onlylive.entity.dao.GoodsDao;
import me.sheepyang.onlylive.entity.dao.JoinPlayerGoodsToPlayerDao;
import me.sheepyang.onlylive.entity.dao.PlayerDao;
import me.sheepyang.onlylive.entity.dao.PlayerGoodsDao;
import me.sheepyang.onlylive.utils.data.EventGoodsUtil;
import me.sheepyang.onlylive.utils.data.EventUtil;
import me.sheepyang.onlylive.utils.data.GoodsUtil;

/**
 * 游戏数据相关工具类
 * Created by SheepYang on 2016/10/13 00:24.
 */
public class DataUtil {
    private static PlayerDao mPlayerDao;
    private static EventDao mEventDao;
    private static PlayerGoodsDao mPlayerGoodsDao;
    private static JoinPlayerGoodsToPlayerDao mJoinPlayerGoodsToPlayerDao;

    static {
        mPlayerDao = GameApplication.getInstances().getDaoSession().getPlayerDao();
        mPlayerGoodsDao = GameApplication.getInstances().getDaoSession().getPlayerGoodsDao();
        mEventDao = GameApplication.getInstances().getDaoSession().getEventDao();
        mJoinPlayerGoodsToPlayerDao = GameApplication.getInstances().getDaoSession().getJoinPlayerGoodsToPlayerDao();
    }

    public static void initGameData() {
        initGoodsData();
        initEventData();
        initEventGoodsData();
        initJoinEventGoodsToEvent();
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

    public static void setPlayerData(Player player) {
        mPlayerDao.insertOrReplace(player);
    }

    public static void deletePlayerData() {
        mPlayerDao.deleteByKey((long) 1);
    }

    /**
     * 初始化玩家數據
     */
    public static void initPlayerData() {
        Player player = new Player();
        player.setId(Long.valueOf(1));
        player.setCash(Constants.INIT_GAME_CASH);// 现金
        player.setDebt(Constants.INIT_GAME_DEBT);// 负债
        player.setDeposit(Constants.INIT_GAME_DEPOSIT);// 存款
        player.setHealth(Constants.INIT_GAME_HEALTH);// 健康
        player.setHouse(Constants.INIT_GAME_HOUSE);// 房子
        player.setWeek(Constants.INIT_GAME_WEEK);// 周数
        mPlayerDao.insertOrReplace(player);
    }

    /**
     * 初始化所有突发事件
     */
    private static void initEventData() {
        mEventDao.deleteAll();
        String title1 = "老乡见老乡，两眼泪汪汪";
        String msg1 = "老乡送你 " + Constants.REPLACE_GOODS_NUMBER_0 + Constants.REPLACE_GOODS_UNIT_0 + "化妆品。\n\n" + Constants.REPLACE_GOODS_NAME_0 + " x" + Constants.REPLACE_GOODS_NUMBER_0;
        EventUtil.create(title1, msg1);

        String title2 = "美男传说";
        String msg2 = "学生妹看你太帅，送你 " + Constants.REPLACE_GOODS_NUMBER_0 + Constants.REPLACE_GOODS_UNIT_0 + "有她艳照的手机。\n\n" + Constants.REPLACE_GOODS_NAME_0 + " x" + Constants.REPLACE_GOODS_NUMBER_0;
        EventUtil.create(title2, msg2);

        String title3 = "捡到地沟油";
        String msg3 = "路边捡到 " + Constants.REPLACE_GOODS_NUMBER_0 + Constants.REPLACE_GOODS_UNIT_0 + Constants.REPLACE_GOODS_NAME_0 + "\n\n" + Constants.REPLACE_GOODS_NAME_0 + " x" + Constants.REPLACE_GOODS_NUMBER_0;
        EventUtil.create(title3, msg3);

        String title4 = "出门靠朋友";
        String msg4 = "朋友送你 " + Constants.REPLACE_GOODS_NUMBER_0 + Constants.REPLACE_GOODS_UNIT_0 + "陈年白酒。\n\n" + Constants.REPLACE_GOODS_NAME_0 + " x" + Constants.REPLACE_GOODS_NUMBER_0;
        EventUtil.create(title4, msg4);
    }

    /**
     * 初始化事件物品
     */
    private static void initEventGoodsData() {
        EventGoodsUtil.deleteAll();
        EventGoodsUtil.create("劣质化妆品", "盒", 4, 1);
        EventGoodsUtil.create("水货手机", "部", 2, 1);
        EventGoodsUtil.create("地沟油", "桶", 5, 1);
        EventGoodsUtil.create("假冒茅台", "瓶", 5, 1);
    }

    private static void initJoinEventGoodsToEvent() {
        EventGoodsUtil.joinDeleteAll();
        EventGoodsUtil.joinEventGoodsToEvent("劣质化妆品", "老乡见老乡，两眼泪汪汪");
        EventGoodsUtil.joinEventGoodsToEvent("水货手机", "美男传说");
        EventGoodsUtil.joinEventGoodsToEvent("地沟油", "捡到地沟油");
        EventGoodsUtil.joinEventGoodsToEvent("假冒茅台", "出门靠朋友");
    }

    /**
     * 初始化所有物品信息
     */
    private static void initGoodsData() {
        GoodsUtil.deleteAll();
        GoodsUtil.create("假冒茅台", 1200, 100);
        GoodsUtil.create("黑心棉", 999, 90);
        GoodsUtil.create("北京户口", 120000, 25000);
        GoodsUtil.create("名校学历", 80000, 6000);
        GoodsUtil.create("走私海洛因", 10000, 4000);
        GoodsUtil.create("高考答案", 700000, 300000);
        GoodsUtil.create("走私汽车", 100000, 20000);
        GoodsUtil.create("水货手机", 15000, 1800);
        GoodsUtil.create("劣质化妆品", 1500, 200);
    }

    /**
     * 初始化所有新聞事件
     */
    private static void initNewsData() {

    }

    public static void addPlayGoods(EventGoods eventGoods, int goodsNum) {
        QueryBuilder<PlayerGoods> qb = mPlayerGoodsDao.queryBuilder();
        qb.where(PlayerGoodsDao.Properties.Name.eq(eventGoods.getName()));
        List<PlayerGoods> list = qb.list();
        if (list != null && list.size() > 0) {
            PlayerGoods playerGoods = list.get(0);
            Number newPrice = playerGoods.getPrice();
            Number newGoodsNumber = playerGoods.getNumber();
            // 计算获得物品后，物品的平均价格
            // 公式 = 总价 / (原数量 + 新增数量)
            float price = (float) (newPrice.getNumber() * newGoodsNumber.getNumber()) / (float) (newGoodsNumber.getNumber() + goodsNum);
            newPrice.setNumber(Math.round(price));
            playerGoods.setPrice(newPrice);
            // 设置物品数量
            newGoodsNumber.setNumber(newGoodsNumber.getNumber() + goodsNum);
            playerGoods.setNumber(newGoodsNumber);

            JoinPlayerGoodsToPlayer joinGoods = new JoinPlayerGoodsToPlayer();
            joinGoods.setPlayerGoodsId(playerGoods.getId());
            joinGoods.setPlayerId(getPlayerData().getId());
            mJoinPlayerGoodsToPlayerDao.insertOrReplace(joinGoods);
            mPlayerGoodsDao.insertOrReplace(playerGoods);
        } else {// 玩家物品列表中没有该物品，则新建物品
            PlayerGoods playerGoods = new PlayerGoods();
            Number price = eventGoods.getPrice();
            Number newGoodsNum = eventGoods.getNumber();
            price.setNumber(0);
            newGoodsNum.setNumber(0);
            playerGoods.setPrice(price);
            playerGoods.setNumber(newGoodsNum);
            playerGoods.setName(eventGoods.getName());
            playerGoods.setUnit(eventGoods.getUnit());

            JoinPlayerGoodsToPlayer joinGoods = new JoinPlayerGoodsToPlayer();
            joinGoods.setPlayerGoodsId(playerGoods.getId());
            joinGoods.setPlayerId(getPlayerData().getId());
            mJoinPlayerGoodsToPlayerDao.insertOrReplace(joinGoods);
            mPlayerGoodsDao.insertOrReplace(playerGoods);
        }
    }
}
