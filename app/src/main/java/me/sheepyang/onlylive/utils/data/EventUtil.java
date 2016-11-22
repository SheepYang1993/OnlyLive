package me.sheepyang.onlylive.utils.data;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.ArrayList;
import java.util.List;

import me.sheepyang.onlylive.app.GameApplication;
import me.sheepyang.onlylive.entity.Event;
import me.sheepyang.onlylive.entity.Goods;
import me.sheepyang.onlylive.entity.Number;
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

    public static class Builder {
        private final EventParams P;

        public Builder() {
            P = new EventParams();
        }

        public Builder setTitle(String title) {
            P.mTitle = title;
            return this;
        }

        public Builder setMessage(String message) {
            P.mMessage = message;
            return this;
        }

        public Builder setIsGood(Boolean isGood) {
            P.mIsGood = isGood;
            return this;
        }

        public Builder setHealth(Number health) {
            P.mHealth = health;
            return this;
        }

        public Builder setCash(Number cash) {
            P.mCash = cash;
            return this;
        }

        public Builder setDebt(Number debt) {
            P.mDebt = debt;
            return this;
        }

        public Builder setDeposit(Number deposit) {
            P.mDeposit = deposit;
            return this;
        }

        public Builder addGoods(Goods goods) {
            if (P.mGoodsList == null) {
                P.mGoodsList = new ArrayList<>();
            }
            if (goods != null) {
                P.mGoodsList.add(goods);
            }
            return this;
        }

        public Event create() {
            Event event = new Event();
            event.setTitle(P.mTitle);
            event.setMessage(P.mMessage);
            if (P.mIsGood != null) {
                event.setIsGood(P.mIsGood);
            }
            if (P.mCash != null) {
                event.setCash(P.mCash);
            }
            if (P.mDebt != null) {
                event.setDebt(P.mDebt);
            }
            if (P.mDeposit != null) {
                event.setDeposit(P.mDeposit);
            }
            if (P.mHealth != null) {
                event.setHealth(P.mHealth);
            }
            mEventDao.insertOrReplace(event);
            if (P.mGoodsList != null && P.mGoodsList.size() > 0) {
                for (int i = 0; i < P.mGoodsList.size(); i++) {
                    GoodsUtil.joinGoodsToEvent(P.mGoodsList.get(i), event);
                }
            }
            return event;
        }

        private class EventParams {
            String mTitle;
            String mMessage;
            Boolean mIsGood;
            Number mHealth;
            Number mCash;
            Number mDebt;
            Number mDeposit;
            List<Goods> mGoodsList;
        }
    }

    public static long create(Boolean isGood, String title, String msg, Number cash, Number debt, Number deposit) {
        Event event = new Event();
        event.setTitle(title);
        event.setMessage(msg);
        if (isGood != null) {
            event.setIsGood(isGood);
        }
        if (cash != null) {
            event.setCash(cash);
        }
        if (debt != null) {
            event.setDebt(debt);
        }
        if (deposit != null) {
            event.setDeposit(deposit);
        }
        return mEventDao.insertOrReplace(event);
    }

    /**
     * @return
     */
    public static long createGood(String title, String msg) {
        return create(true, title, msg, null, null, null);
    }

    /**
     * @return
     */
    public static long createGood(String title, String msg, Number cash, Number debt, Number deposit) {
        return create(true, title, msg, cash, debt, deposit);
    }

    /**
     * 根据rowId获取事件
     *
     * @param rowId
     */
    private static Event getEvent(int rowId) {
        return mEventDao.loadByRowId(rowId);
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

    public static Event getRandomGoodEvent(boolean isGood) {
        QueryBuilder<Event> qb = mEventDao.queryBuilder();
        qb.where(EventDao.Properties.IsGood.eq(isGood));
        List<Event> list = qb.list();
        if (list != null && list.size() > 0) {
            int position = RandomUtil.getRandomNum(list.size() - 1, 0);
            return list.get(position);
        } else {
            return null;
        }
    }

    public static Event getRandomEvent() {
        int rowId = RandomUtil.getRandomNum((int) mEventDao.count(), 1);
        return getEvent(rowId);
    }

    public static void deleteAll() {
        mEventDao.deleteAll();
    }
}
