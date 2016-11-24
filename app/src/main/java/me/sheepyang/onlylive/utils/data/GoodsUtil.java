package me.sheepyang.onlylive.utils.data;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import me.sheepyang.onlylive.app.GameApplication;
import me.sheepyang.onlylive.entity.Goods;
import me.sheepyang.onlylive.entity.Number;
import me.sheepyang.onlylive.entity.dao.GoodsDao;
import me.sheepyang.onlylive.utils.MyLog;
import me.sheepyang.onlylive.utils.RandomUtil;

/**
 * Created by SheepYang on 2016/10/20 21:50.
 */

public class GoodsUtil {
    private static GoodsDao mGoodsDao;

    static {
        mGoodsDao = GameApplication.getInstances().getDaoSession().getGoodsDao();
    }

    public static class Builder {
        private final GoodsParams P;

        public Builder() {
            P = new GoodsParams();
        }

        public Builder setName(String name) {
            P.mName = name;
            return this;
        }

        public Builder setUnit(String unit) {
            P.mUnit = unit;
            return this;
        }

        public Builder setPrice(Number price) {
            P.mPrice = price;
            return this;
        }

        public Goods create() {
            Goods goods = new Goods();
            goods.setName(P.mName);
            goods.setUnit(P.mUnit);
            if (P.mPrice != null) {
                goods.setPrice(P.mPrice);
            }
            return goods;
        }

        private class GoodsParams {
            String mName;
            String mUnit;
            Number mPrice;
        }
    }

    public static void deleteAll() {
        mGoodsDao.deleteAll();
    }

    public static List<Goods> getRandomList(int size) {
        int count = (int) mGoodsDao.count();
        List<Goods> goodsList = new ArrayList<>();
        HashSet<Integer> rowIdSet = new HashSet<>();
        while (rowIdSet.size() < (size > count ? count : size)) {
            rowIdSet.add(RandomUtil.getRandomNum(count, 1));
        }
        for (Integer rowId : rowIdSet) {
            MyLog.i("rowId:" + rowId);
            Goods goods = getGoods(rowId);
            if (goods != null) {
                goodsList.add(goods);
            }
        }
        return goodsList;
    }

    public static Goods getGoods(String name) {
        Goods goods = null;
        QueryBuilder<Goods> qb = mGoodsDao.queryBuilder();
        qb.where(GoodsDao.Properties.Name.eq(name));
        List<Goods> list = qb.list();
        if (list != null && list.size() > 0) {
            goods = list.get(0);
        }
        return goods;
    }

    public static Goods getGoods(int rowId) {
        return mGoodsDao.loadByRowId(rowId);
    }

    public static List<Goods> loadAll() {
        return mGoodsDao.loadAll();
    }
}
