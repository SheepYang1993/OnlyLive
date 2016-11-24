package me.sheepyang.onlylive.utils.data;

import me.sheepyang.onlylive.app.GameApplication;
import me.sheepyang.onlylive.entity.Event;
import me.sheepyang.onlylive.entity.Goods;
import me.sheepyang.onlylive.entity.JoinBadGoodsToEvent;
import me.sheepyang.onlylive.entity.dao.JoinBadGoodsToEventDao;

/**
 * Created by SheepYang on 2016/11/23.
 */

public class JoinBadGoodsToEventUtil {
    private static JoinBadGoodsToEventDao mJoinBadGoodsToEventDao;

    static {
        mJoinBadGoodsToEventDao = GameApplication.getInstances().getDaoSession().getJoinBadGoodsToEventDao();
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
