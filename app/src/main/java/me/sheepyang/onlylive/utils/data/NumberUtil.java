package me.sheepyang.onlylive.utils.data;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

import me.sheepyang.onlylive.app.GameApplication;
import me.sheepyang.onlylive.entity.EventGoods;
import me.sheepyang.onlylive.entity.Number;
import me.sheepyang.onlylive.entity.dao.EventGoodsDao;
import me.sheepyang.onlylive.entity.dao.NumberDao;

/**
 * Created by SheepYang on 2016/10/20 22:02.
 */

public class NumberUtil {
    private static NumberDao mNumberDao;

    static {
        mNumberDao = GameApplication.getInstances().getDaoSession().getNumberDao();
    }

    public static long create(int max, int min) {
        Number number = new Number();
        number.setMaxNumber(max);
        number.setMinNumber(min);
        return mNumberDao.insertOrReplace(number);
    }

    public static Number getNumber(long rowId) {
        return mNumberDao.loadByRowId(rowId);
    }
}
