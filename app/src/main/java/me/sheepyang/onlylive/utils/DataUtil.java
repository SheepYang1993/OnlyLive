package me.sheepyang.onlylive.utils;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.ArrayList;
import java.util.List;

import me.sheepyang.onlylive.app.Constants;
import me.sheepyang.onlylive.app.GameApplication;
import me.sheepyang.onlylive.entity.Event;
import me.sheepyang.onlylive.entity.EventGoods;
import me.sheepyang.onlylive.entity.Goods;
import me.sheepyang.onlylive.entity.JoinEventGoodsToEvent;
import me.sheepyang.onlylive.entity.Number;
import me.sheepyang.onlylive.entity.Player;
import me.sheepyang.onlylive.entity.dao.EventDao;
import me.sheepyang.onlylive.entity.dao.GoodsDao;
import me.sheepyang.onlylive.entity.dao.JoinEventGoodsToEventDao;
import me.sheepyang.onlylive.entity.dao.PlayerDao;

/**
 * 游戏数据相关工具类
 * Created by SheepYang on 2016/10/13 00:24.
 */
public class DataUtil {
    private static PlayerDao mPlayerDao;
    private static GoodsDao mGoodsDao;
    private static EventDao mEventDao;
    private static JoinEventGoodsToEventDao mJoinEventGoodsToEventDao;

    static {
        mPlayerDao = GameApplication.getInstances().getDaoSession().getPlayerDao();
        mGoodsDao = GameApplication.getInstances().getDaoSession().getGoodsDao();
        mEventDao = GameApplication.getInstances().getDaoSession().getEventDao();
        mJoinEventGoodsToEventDao = GameApplication.getInstances().getDaoSession().getJoinEventGoodsToEventDao();
    }

    private static Object event;

    public static void initGameData() {
        initEventData();
        initGoodsData();
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
        List<Event> eventList = new ArrayList<>();
        List<JoinEventGoodsToEvent> joinList = new ArrayList<>();
        Event event1 = new Event();
        event1.setIsGoodEvent(true);
        event1.setTitle("老乡见老乡，两眼泪汪汪");
        event1.setMessage("老乡送你 " + Constants.GOODS_NUMBER + Constants.GOODS_UNIT + "化妆品。\n\n" + Constants.GOODS_NAME + " x" + Constants.GOODS_NUMBER);
        EventGoods eventGoods1 = new EventGoods();
        eventGoods1.setName("劣质化妆品");
        Number number1 = new Number();
        number1.setMaxNumber(4);
        number1.setMinNumber(1);
        eventGoods1.setNumber(number1);
        JoinEventGoodsToEvent joinEventGoodsToEvent1 = new JoinEventGoodsToEvent();
        joinEventGoodsToEvent1.setEventGoodsId(eventGoods1.getId());
        joinEventGoodsToEvent1.setEventId(event1.getId());

        Event event2 = new Event();
        event2.setIsGoodEvent(true);
        event2.setTitle("美男传说");
        event2.setMessage("学生妹看你太帅，送你 " + Constants.GOODS_NUMBER + Constants.GOODS_UNIT + "有她艳照的手机。\n\n" + Constants.GOODS_NAME + " x" + Constants.GOODS_NUMBER);
        EventGoods eventGoods2 = new EventGoods();
        eventGoods2.setName("水货手机");
        Number number2 = new Number();
        number2.setMaxNumber(2);
        number2.setMinNumber(1);
        eventGoods2.setNumber(number2);
        JoinEventGoodsToEvent joinEventGoodsToEvent2 = new JoinEventGoodsToEvent();
        joinEventGoodsToEvent2.setEventGoodsId(eventGoods2.getId());
        joinEventGoodsToEvent2.setEventId(event2.getId());

        Event event3 = new Event();
        event3.setIsGoodEvent(true);
        event3.setTitle("捡到地沟油");
        event3.setMessage("路边捡到 " + Constants.GOODS_NUMBER + Constants.GOODS_UNIT + Constants.GOODS_NAME + "\n\n" + Constants.GOODS_NAME + " x" + Constants.GOODS_NUMBER);
        EventGoods eventGoods3 = new EventGoods();
        eventGoods3.setName("地沟油");
        Number number3 = new Number();
        number3.setMaxNumber(5);
        number3.setMinNumber(1);
        eventGoods3.setNumber(number3);
        JoinEventGoodsToEvent joinEventGoodsToEvent3 = new JoinEventGoodsToEvent();
        joinEventGoodsToEvent3.setEventGoodsId(eventGoods3.getId());
        joinEventGoodsToEvent3.setEventId(event3.getId());

        Event event4 = new Event();
        event4.setIsGoodEvent(true);
        event4.setTitle("出门靠朋友");
        event4.setMessage("朋友送你 " + Constants.GOODS_NUMBER + Constants.GOODS_UNIT + "陈年白酒。\n\n" + Constants.GOODS_NAME + " x" + Constants.GOODS_NUMBER);
        EventGoods eventGoods4 = new EventGoods();
        eventGoods4.setName("假冒茅台");
        Number number4 = new Number();
        number4.setMaxNumber(5);
        number4.setMinNumber(1);
        eventGoods4.setNumber(number4);
        JoinEventGoodsToEvent joinEventGoodsToEvent4 = new JoinEventGoodsToEvent();
        joinEventGoodsToEvent4.setEventGoodsId(eventGoods4.getId());
        joinEventGoodsToEvent4.setEventId(event4.getId());

//        Event event5 = new Event();
//        event5.setIsGoodEvent(true);
//        event5.setTitle("老乡见老乡，两眼泪汪汪");
//        event5.setMessage("老乡送你 " + Constants.GOODS_NUMBER + Constants.GOODS_UNIT + Constants.GOODS_NAME + "\n\n" + Constants.GOODS_NAME + " x" + Constants.GOODS_NUMBER);
//        EventGoods eventGoods5 = new EventGoods();
//        eventGoods5.setName("假冒茅台");
//        Number number5 = new Number();
//        number5.setMaxNumber(5);
//        number5.setMinNumber(1);
//        eventGoods5.setNumber(number5);
//        JoinEventGoodsToEvent joinEventGoodsToEvent5 = new JoinEventGoodsToEvent();
//        joinEventGoodsToEvent5.setEventGoodsId(eventGoods5.getId());
//        joinEventGoodsToEvent5.setEventId(event5.getId());

        eventList.add(event1);
        eventList.add(event2);
        eventList.add(event3);
        eventList.add(event4);
//        eventList.add(event5);

        joinList.add(joinEventGoodsToEvent1);
        joinList.add(joinEventGoodsToEvent2);
        joinList.add(joinEventGoodsToEvent3);
        joinList.add(joinEventGoodsToEvent4);
//        joinList.add(joinEventGoodsToEvent5);

        mJoinEventGoodsToEventDao.insertOrReplaceInTx(joinList);
        mEventDao.insertInTx(eventList);
    }

    /**
     * 初始化所有物品信息
     */
    private static void initGoodsData() {
        mGoodsDao.deleteAll();
        List<Goods> goodsList = new ArrayList<>();
        Goods goods1 = new Goods();
        goods1.setName("假冒茅台");
        Number price1 = new Number();
        price1.setMaxNumber(1200);
        price1.setMinNumber(100);
        goods1.setPrice(price1);

        Goods goods2 = new Goods();
        goods2.setName("黑心棉");
        Number price2 = new Number();
        price2.setMaxNumber(999);
        price2.setMinNumber(90);
        goods2.setPrice(price2);

        Goods goods3 = new Goods();
        goods3.setName("北京户口");
        Number price3 = new Number();
        price3.setMaxNumber(120000);
        price3.setMinNumber(25000);
        goods3.setPrice(price3);

        Goods goods4 = new Goods();
        goods4.setName("名校学历");
        Number price4 = new Number();
        price4.setMaxNumber(80000);
        price4.setMinNumber(6000);
        goods4.setPrice(price4);

        Goods goods5 = new Goods();
        goods5.setName("走私海洛因");
        Number price5 = new Number();
        price5.setMaxNumber(4000);
        price5.setMinNumber(10000);
        goods5.setPrice(price5);

        Goods goods6 = new Goods();
        goods6.setName("高考答案");
        Number price6 = new Number();
        price6.setMaxNumber(700000);
        price6.setMinNumber(300000);
        goods6.setPrice(price6);

        Goods goods7 = new Goods();
        goods7.setName("走私汽车");
        Number price7 = new Number();
        price7.setMaxNumber(100000);
        price7.setMinNumber(20000);
        goods7.setPrice(price7);

        Goods goods8 = new Goods();
        goods8.setName("水货手机");
        Number price8 = new Number();
        price8.setMaxNumber(15000);
        price8.setMinNumber(1800);
        goods8.setPrice(price8);

        Goods goods9 = new Goods();
        goods9.setName("劣质化妆品");
        Number price9 = new Number();
        price9.setMaxNumber(1500);
        price9.setMinNumber(200);
        goods9.setPrice(price9);

        goodsList.add(goods1);
        goodsList.add(goods2);
        goodsList.add(goods3);
        goodsList.add(goods4);
        goodsList.add(goods5);
        goodsList.add(goods6);
        goodsList.add(goods7);
        goodsList.add(goods8);
        mGoodsDao.insertOrReplaceInTx(goodsList);
    }

    /**
     * 初始化所有新聞事件
     */
    private static void initNewsData() {

    }

    public static Event getEvent() {
        long total = mEventDao.loadAll().size();
        MyLog.i("total:" + total);
        int rowId = RandomUtil.getRandomNum(1, (int) total);
        MyLog.i("rowId:" + rowId);
        return mEventDao.loadByRowId(rowId);
    }
}
