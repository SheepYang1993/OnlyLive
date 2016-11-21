package me.sheepyang.onlylive.utils.data;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

import me.sheepyang.onlylive.app.GameApplication;
import me.sheepyang.onlylive.entity.Event;
import me.sheepyang.onlylive.entity.Number;
import me.sheepyang.onlylive.entity.dao.EventDao;

/**
 * Created by SheepYang on 2016/10/20 21:36.
 */

public class EventUtil {
    private static EventDao mEventDao;

    static {
        mEventDao = GameApplication.getInstances().getDaoSession().getEventDao();
    }

    /**
     * @return
     */
    public static long create(String title, String msg) {
        Event event = new Event();
        event.setTitle(title);
        event.setMessage(msg);
        return mEventDao.insertOrReplace(event);
    }

    /**
     * @return
     */
    public static long create(String title, String msg, Number money) {
        Event event = new Event();
        event.setTitle(title);
        event.setMessage(msg);
        event.setMoney(money);

        return mEventDao.insertOrReplace(event);
    }

    /**
     * 根据title获取事件
     *
     * @param title
     * @return
     */
    public static Event getEvent(String title) {
        QueryBuilder<Event> qb = mEventDao.queryBuilder();
        qb.where(EventDao.Properties.Title.eq(title));
        List<Event> list = qb.list();
        if (list != null && list.size() > 0) {
            return list.get(0);
        } else {
            return null;
        }
    }

    public static void deleteAll() {
        mEventDao.deleteAll();
    }
}
