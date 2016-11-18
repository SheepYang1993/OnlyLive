package me.sheepyang.onlylive.utils.data;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

import me.sheepyang.onlylive.app.GameApplication;
import me.sheepyang.onlylive.entity.Event;
import me.sheepyang.onlylive.entity.EventGoods;
import me.sheepyang.onlylive.entity.JoinEventGoodsToEvent;
import me.sheepyang.onlylive.entity.dao.EventGoodsDao;
import me.sheepyang.onlylive.entity.dao.JoinEventGoodsToEventDao;

/**
 * Created by SheepYang on 2016/10/20 21:15.
 */

public class EventGoodsUtil {
    private static EventGoodsDao mEventGoodsDao;
    private static JoinEventGoodsToEventDao mJoinEventGoodsToEventDao;

    static {
        mEventGoodsDao = GameApplication.getInstances().getDaoSession().getEventGoodsDao();
        mJoinEventGoodsToEventDao = GameApplication.getInstances().getDaoSession().getJoinEventGoodsToEventDao();
    }

    public static long create(String goodsName, String unit, String max, String min) {
        EventGoods goods = new EventGoods();
        goods.setName(goodsName);
        goods.setUnit(unit);
        long rowId = NumberUtil.create(max, min);
        goods.setNumber(NumberUtil.getNumber(rowId));
        return mEventGoodsDao.insertOrReplace(goods);
    }

    public static EventGoods getEventGoods(String goodsName) {
        QueryBuilder<EventGoods> qb = mEventGoodsDao.queryBuilder();
        qb.where(EventGoodsDao.Properties.Name.eq(goodsName));
        List<EventGoods> list = qb.list();
        if (list != null && list.size() > 0) {
            return list.get(0);
        } else {
            return null;
        }
    }

    public static long joinEventGoodsToEvent(String goodsName, String eventName) {
        EventGoods goods = EventGoodsUtil.getEventGoods(goodsName);
        Event event = EventUtil.getEvent(eventName);
        if (goods != null && event != null) {
            JoinEventGoodsToEvent join = new JoinEventGoodsToEvent();
            join.setEventGoodsId(goods.getId());
            join.setEventId(event.getId());
            return mJoinEventGoodsToEventDao.insertOrReplace(join);
        }
        return -1;
    }

    public static void joinDeleteAll() {
        mJoinEventGoodsToEventDao.deleteAll();
    }

    public static void deleteAll() {
        mEventGoodsDao.deleteAll();
    }
}
