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

        public Builder setOkText(String okText) {
            P.mOkText = okText;
            return this;
        }

        public Builder setCancelText(String cancelText) {
            P.mCancelText = cancelText;
            return this;
        }

        public Builder setResultOKGoodTitle(String resultOKGoodTitle) {
            P.mResultOKGoodTitle = resultOKGoodTitle;
            return this;
        }

        public Builder setResultOKGoodMsg(String resultOKGoodMsg) {
            P.mResultOKGoodMsg = resultOKGoodMsg;
            return this;
        }

        public Builder setResultOKBadTitle(String resultOKBadTitle) {
            P.mResultOKBadTitle = resultOKBadTitle;
            return this;
        }

        public Builder setResultOKBadMsg(String resultOKBadMsg) {
            P.mResultOKBadMsg = resultOKBadMsg;
            return this;
        }

        public Builder setResultCancelGoodTitle(String resultCancelGoodTitle) {
            P.mResultCancelGoodTitle = resultCancelGoodTitle;
            return this;
        }

        public Builder setResultCancelGoodMsg(String resultCancelGoodMsg) {
            P.mResultCancelGoodMsg = resultCancelGoodMsg;
            return this;
        }

        public Builder setResultCancelBadTitle(String resultCancelBadTitle) {
            P.mResultCancelBadTitle = resultCancelBadTitle;
            return this;
        }

        public Builder setResultCancelBadMsg(String resultCancelBadMsg) {
            P.mResultCancelBadMsg = resultCancelBadMsg;
            return this;
        }

        public Builder setIsNeedSelect(boolean isNeedSelect) {
            P.mIsNeedSelect = isNeedSelect;
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

        /**
         * 添加事件中赠送的物品
         *
         * @param goods
         * @return
         */
        public Builder addGoods(Goods goods) {
            if (P.mGoodGoodsList == null) {
                P.mGoodGoodsList = new ArrayList<>();
            }
            if (goods != null) {
                P.mGoodGoodsList.add(goods);
            }
            return this;
        }

        /**
         * 添加事件中会丢失的物品
         *
         * @param goods
         * @return
         */
        public Builder addBadGoods(Goods goods) {
            if (P.mBadGoodsList == null) {
                P.mBadGoodsList = new ArrayList<>();
            }
            if (goods != null) {
                P.mBadGoodsList.add(goods);
            }
            return this;
        }

        public Event create() {
            Event event = new Event();
            event.setTitle(P.mTitle);
            event.setMessage(P.mMessage);
            event.setBtnOk(P.mOkText);
            event.setBtnCancel(P.mCancelText);
            event.setResultOKGoodTitle(P.mResultOKGoodTitle);
            event.setResultOKGoodMsg(P.mResultOKGoodMsg);
            event.setResultOKBadTitle(P.mResultOKBadTitle);
            event.setResultOKBadMsg(P.mResultOKBadMsg);
            event.setResultCancelGoodTitle(P.mResultCancelGoodTitle);
            event.setResultCancelGoodMsg(P.mResultCancelGoodMsg);
            event.setResultCancelBadTitle(P.mResultCancelBadTitle);
            event.setResultCancelBadMsg(P.mResultCancelBadMsg);
            if (P.mIsGood != null) {
                event.setIsGood(P.mIsGood);
            }
            if (P.mIsNeedSelect != null) {
                event.setIsNeedSelect(P.mIsNeedSelect);
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
            if (P.mGoodGoodsList != null && P.mGoodGoodsList.size() > 0) {
                for (int i = 0; i < P.mGoodGoodsList.size(); i++) {
                    JoinGoodGoodsToEventUtil.join(P.mGoodGoodsList.get(i), event);
                }
            }
            if (P.mBadGoodsList != null && P.mBadGoodsList.size() > 0) {
                for (int i = 0; i < P.mBadGoodsList.size(); i++) {
                    JoinBadGoodsToEventUtil.join(P.mBadGoodsList.get(i), event);
                }
            }
            return event;
        }

        private class EventParams {
            String mTitle;
            String mMessage;
            String mOkText;
            String mCancelText;
            String mResultOKGoodTitle;
            String mResultOKBadTitle;
            String mResultOKGoodMsg;
            String mResultOKBadMsg;
            String mResultCancelGoodTitle;
            String mResultCancelBadTitle;
            String mResultCancelGoodMsg;
            String mResultCancelBadMsg;
            Boolean mIsGood;
            Boolean mIsNeedSelect;
            Number mHealth;
            Number mCash;
            Number mDebt;
            Number mDeposit;
            List<Goods> mGoodGoodsList;
            List<Goods> mBadGoodsList;
        }
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
        Event event = null;
        QueryBuilder<Event> qb = mEventDao.queryBuilder();
        qb.where(EventDao.Properties.Title.eq(title));
        List<Event> list = qb.list();
        if (list != null && list.size() > 0) {
            event = list.get(0);
        }
        return event;
    }

    public static Event getRandomGoodEvent(boolean isGood) {
        Event event = null;
        QueryBuilder<Event> qb = mEventDao.queryBuilder();
        qb.where(EventDao.Properties.IsGood.eq(isGood));
        List<Event> list = qb.list();
        if (list != null && list.size() > 0) {
            int position = RandomUtil.getRandomNum(list.size() - 1, 0);
            event = list.get(position);
        }
        return event;
    }

    public static Event getRandomEvent() {
        int rowId = RandomUtil.getRandomNum((int) mEventDao.count(), 1);
        return getEvent(rowId);
    }

    public static void deleteAll() {
        mEventDao.deleteAll();
    }
}
