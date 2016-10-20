package me.sheepyang.onlylive.utils.data;

import me.sheepyang.onlylive.app.GameApplication;
import me.sheepyang.onlylive.entity.Goods;
import me.sheepyang.onlylive.entity.Number;
import me.sheepyang.onlylive.entity.dao.GoodsDao;

import static android.R.attr.max;

/**
 * Created by SheepYang on 2016/10/20 21:50.
 */

public class GoodsUtil {
    private static GoodsDao mGoodsDao;

    static {
        mGoodsDao = GameApplication.getInstances().getDaoSession().getGoodsDao();
    }

    public static long create(String name, int max, int min) {
        Goods goods = new Goods();
        goods.setName(name);
        long rowId = NumberUtil.create(max, min);
        goods.setPrice(NumberUtil.getNumber(rowId));
        return mGoodsDao.insertOrReplace(goods);
    }

    public static void deleteAll() {
        mGoodsDao.deleteAll();
    }
}
