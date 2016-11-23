package me.sheepyang.onlylive.utils.data;

import me.sheepyang.onlylive.app.GameApplication;
import me.sheepyang.onlylive.entity.Event;
import me.sheepyang.onlylive.entity.Goods;
import me.sheepyang.onlylive.entity.JoinGoodsToEvent;
import me.sheepyang.onlylive.entity.dao.JoinGoodsToEventDao;

/**
 * Created by SheepYang on 2016/11/23.
 */

public class JoinGoodsToEventUtil {
    private static JoinGoodsToEventDao mJoinGoodsToEventDao;

    static {
        mJoinGoodsToEventDao = GameApplication.getInstances().getDaoSession().getJoinGoodsToEventDao();
    }

    public static void join(Goods goods, Event event) {
        if (goods != null && event != null) {
            JoinGoodsToEvent join = new JoinGoodsToEvent();
            join.setGoodsId(goods.getId());
            join.setEventId(event.getId());
            mJoinGoodsToEventDao.insertOrReplace(join);
        }
    }
}
