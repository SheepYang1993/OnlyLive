package me.sheepyang.onlylive.utils.data;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

import me.sheepyang.onlylive.app.GameApplication;
import me.sheepyang.onlylive.entity.Event;
import me.sheepyang.onlylive.entity.Goods;
import me.sheepyang.onlylive.entity.JoinGoodGoodsToEvent;
import me.sheepyang.onlylive.entity.dao.EventDao;
import me.sheepyang.onlylive.entity.dao.JoinGoodGoodsToEventDao;

/**
 * Created by SheepYang on 2016/11/23.
 */

public class JoinGoodGoodsToEventUtil {
    private static JoinGoodGoodsToEventDao mJoinGoodGoodsToEventDao;

    static {
        mJoinGoodGoodsToEventDao = GameApplication.getInstances().getDaoSession().getJoinGoodGoodsToEventDao();
    }

    public static void deleteAllGoods(Long eventId) {
        QueryBuilder<JoinGoodGoodsToEvent> qb = mJoinGoodGoodsToEventDao.queryBuilder();
        qb.where(JoinGoodGoodsToEventDao.Properties.EventId.eq(eventId));
        List<JoinGoodGoodsToEvent> list = qb.list();
        if (list != null && list.size() > 0) {
            for (JoinGoodGoodsToEvent join :
                    list) {
                mJoinGoodGoodsToEventDao.delete(join);
            }
        }
    }

    public static void join(Goods goods, Event event) {
        if (goods != null && event != null) {
            JoinGoodGoodsToEvent join = new JoinGoodGoodsToEvent();
            join.setGoodsId(goods.getId());
            join.setEventId(event.getId());
            mJoinGoodGoodsToEventDao.insertOrReplace(join);
        }
    }
}
