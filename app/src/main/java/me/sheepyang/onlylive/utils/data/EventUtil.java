package me.sheepyang.onlylive.utils.data;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

import me.sheepyang.onlylive.app.Constants;
import me.sheepyang.onlylive.app.GameApplication;
import me.sheepyang.onlylive.entity.Event;
import me.sheepyang.onlylive.entity.EventGoods;
import me.sheepyang.onlylive.entity.dao.EventDao;
import me.sheepyang.onlylive.entity.dao.EventGoodsDao;
import me.sheepyang.onlylive.utils.MyLog;
import me.sheepyang.onlylive.utils.RandomUtil;

import static android.R.attr.max;
import static android.R.attr.name;

/**
 * Created by SheepYang on 2016/10/20 21:36.
 */

public class EventUtil {
    private static EventDao mEventDao;

    static {
        mEventDao = GameApplication.getInstances().getDaoSession().getEventDao();
    }

    public static long create(boolean isGoodEvent, String title, String msg) {
        Event event = new Event();
        event.setIsGoodEvent(isGoodEvent);
        event.setTitle(title);
        event.setMessage(msg);
        return mEventDao.insertOrReplace(event);
    }

    public static long create(boolean isGoodEvent, String title, String msg, int maxMoney, int minMoney) {
        Event event = new Event();
        event.setIsGoodEvent(isGoodEvent);
        event.setTitle(title);
        event.setMessage(msg);
        long rowId = NumberUtil.create(maxMoney, minMoney);
        event.setMoney(NumberUtil.getNumber(rowId));
        return mEventDao.insertOrReplace(event);
    }

    /**
     * 获得随机事件
     *
     * @return
     */
    public static Event getRandomEvent() {
        long total = mEventDao.loadAll().size();
        int rowId = RandomUtil.getRandomNum((int) total, 1);
        return mEventDao.loadByRowId(rowId);
    }

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
