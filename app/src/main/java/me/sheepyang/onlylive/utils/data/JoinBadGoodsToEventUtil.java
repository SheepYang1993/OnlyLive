package me.sheepyang.onlylive.utils.data;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

import me.sheepyang.onlylive.app.GameApplication;
import me.sheepyang.onlylive.greenentity.Event;
import me.sheepyang.onlylive.greenentity.Goods;
import me.sheepyang.onlylive.greenentity.JoinBadGoodsToEvent;
import me.sheepyang.onlylive.greenentity.dao.JoinBadGoodsToEventDao;

/**
 * Created by SheepYang on 2016/11/23.
 */

public class JoinBadGoodsToEventUtil {
    private static JoinBadGoodsToEventDao mJoinBadGoodsToEventDao;

    static {
        mJoinBadGoodsToEventDao = GameApplication.getInstances().getDaoSession().getJoinBadGoodsToEventDao();
    }

    public static void deleteAllGoods(Long eventId) {
        QueryBuilder<JoinBadGoodsToEvent> qb = mJoinBadGoodsToEventDao.queryBuilder();
        qb.where(JoinBadGoodsToEventDao.Properties.EventId.eq(eventId));
        List<JoinBadGoodsToEvent> list = qb.list();
        if (list != null && list.size() > 0) {
            for (JoinBadGoodsToEvent join :
                    list) {
                mJoinBadGoodsToEventDao.delete(join);
            }
        }
    }

    public static void join(Goods goods, Event event) {
        if (goods != null && event != null) {
            JoinBadGoodsToEvent join = new JoinBadGoodsToEvent();
            join.setGoodsId(goods.getId());
            join.setEventId(event.getId());
            mJoinBadGoodsToEventDao.insertOrReplace(join);
        }
    }
}
