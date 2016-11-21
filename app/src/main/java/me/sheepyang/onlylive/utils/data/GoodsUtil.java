package me.sheepyang.onlylive.utils.data;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

import me.sheepyang.onlylive.app.GameApplication;
import me.sheepyang.onlylive.entity.Event;
import me.sheepyang.onlylive.entity.Goods;
import me.sheepyang.onlylive.entity.JoinGoodsToEvent;
import me.sheepyang.onlylive.entity.JoinPlayerGoodsToPlayer;
import me.sheepyang.onlylive.entity.dao.EventDao;
import me.sheepyang.onlylive.entity.dao.GoodsDao;
import me.sheepyang.onlylive.entity.dao.JoinGoodsToEventDao;

/**
 * Created by SheepYang on 2016/10/20 21:50.
 */

public class GoodsUtil {
    private static GoodsDao mGoodsDao;
    private static JoinGoodsToEventDao mJoinGoodsToEventDao;

    static {
        mGoodsDao = GameApplication.getInstances().getDaoSession().getGoodsDao();
        mJoinGoodsToEventDao = GameApplication.getInstances().getDaoSession().getJoinGoodsToEventDao();
    }

    public static long create(String name, String unit, String maxNumber, String minNumber) {
        Goods goods = new Goods();
        goods.setName(name);
        goods.setUnit(unit);
        long rowId = NumberUtil.createNumber(maxNumber, minNumber);
        goods.setPrice(NumberUtil.getNumber(rowId));
        return mGoodsDao.insertOrReplace(goods);
    }

    public static void deleteAll() {
        mGoodsDao.deleteAll();
    }

    public static List<Goods> getRandomList(int size) {
        return mGoodsDao.loadAll();
    }

    public static Goods getGoods(String name) {
        QueryBuilder<Goods> qb = mGoodsDao.queryBuilder();
        qb.where(GoodsDao.Properties.Name.eq(name));
        List<Goods> list = qb.list();
        if (list != null && list.size() > 0) {
            return list.get(0);
        } else {
            return null;
        }
    }

    public static List<Goods> loadAll() {
        return mGoodsDao.loadAll();
    }

    public static void joinGoodsToEvent(Goods goods, Event event) {
        if (goods != null && event != null) {
            JoinGoodsToEvent join = new JoinGoodsToEvent();
            join.setGoodsId(goods.getId());
            join.setEventId(event.getId());
            mJoinGoodsToEventDao.insertOrReplace(join);
        }
    }
}
