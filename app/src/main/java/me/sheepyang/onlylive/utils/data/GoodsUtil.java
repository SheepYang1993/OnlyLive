package me.sheepyang.onlylive.utils.data;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import me.sheepyang.onlylive.app.GameApplication;
import me.sheepyang.onlylive.entity.Goods;
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

    public static long create(String name, String unit, String number, String maxPercent, String minPercent) {
        Goods goods = new Goods();
        goods.setName(name);
        goods.setUnit(unit);
        goods.setPrice(NumberUtil.create(number, maxPercent, minPercent));
        return mGoodsDao.insertOrReplace(goods);
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
        QueryBuilder<Goods> qb = mGoodsDao.queryBuilder();
        qb.where(GoodsDao.Properties.Name.eq(name));
        List<Goods> list = qb.list();
        if (list != null && list.size() > 0) {
            return list.get(0);
        } else {
            return null;
        }
    }

    public static Goods getGoods(int rowId) {
        return mGoodsDao.loadByRowId(rowId);
    }

    public static List<Goods> loadAll() {
        return mGoodsDao.loadAll();
    }
}
