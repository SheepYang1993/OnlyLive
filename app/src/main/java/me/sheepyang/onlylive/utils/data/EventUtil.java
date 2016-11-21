package me.sheepyang.onlylive.utils.data;

import android.text.TextUtils;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

import me.sheepyang.onlylive.app.GameApplication;
import me.sheepyang.onlylive.entity.Event;
import me.sheepyang.onlylive.entity.dao.EventDao;
import me.sheepyang.onlylive.utils.RandomUtil;

/**
 * Created by SheepYang on 2016/10/20 21:36.
 */

public class EventUtil {
    private static EventDao mEventDao;

    static {
        mEventDao = GameApplication.getInstances().getDaoSession().getEventDao();
    }

    public static long create(boolean isGoodEvent, String title, String msg) {
        return create(isGoodEvent, title, msg, null, null);
    }

    /**
     * @param isGoodEvent 是否好事件，例如增加物品，增加现金存款，减少债务，增加健康，房屋容量等等
     * @param title       标题
     * @param msg         详情
     * @return
     */
    public static long create(boolean isGoodEvent, String title, String msg, String maxMoney, String minMoney) {
        Event event = new Event();
        event.setIsGoodEvent(isGoodEvent);
        event.setTitle(title);
        event.setMessage(msg);
        if (!TextUtils.isEmpty(maxMoney) && !TextUtils.isEmpty(minMoney)) {
            long rowId = NumberUtil.create(maxMoney, minMoney);
            event.setMoney(NumberUtil.getNumber(rowId));
        }
        return mEventDao.insertOrReplace(event);
    }

    public static long createSelect(String title, String msg, String msgYes, String msgNo, String selectYes, String seletcNo) {
        Event event = new Event();
        event.setIsSelect(true);
        event.setIsGoodEvent(false);
        event.setTitle(title);
        event.setMessage(msg);
        event.setResultYes(msgYes);
        event.setResultNo(msgNo);
        event.setSelectYes(selectYes);
        event.setSelectNo(seletcNo);
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

    /**
     * 获得随机事件
     *
     * @return
     */
    public static Event getRandomEvent(Boolean isGoodEvent) {
        if (isGoodEvent != null) {// 获取所有好事件中的随机一个
            QueryBuilder<Event> qb = mEventDao.queryBuilder();
            qb.where(EventDao.Properties.IsGoodEvent.eq(isGoodEvent));
            List<Event> list = qb.list();
            if (list != null && list.size() > 0) {
                int position = RandomUtil.getRandomNum(list.size(), 0);
                return list.get(position);
            } else {
                return null;
            }
        } else {// 获取所有事件中的随机一个
            long total = mEventDao.loadAll().size();
            int rowId = RandomUtil.getRandomNum((int) total, 1);
            return mEventDao.loadByRowId(rowId);
        }
    }

    public static void deleteAll() {
        mEventDao.deleteAll();
    }
}
