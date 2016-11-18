package me.sheepyang.onlylive.utils.data;

import me.sheepyang.onlylive.app.GameApplication;
import me.sheepyang.onlylive.entity.Number;
import me.sheepyang.onlylive.entity.dao.NumberDao;

/**
 * Created by SheepYang on 2016/10/20 22:02.
 */

public class NumberUtil {
    private static NumberDao mNumberDao;

    static {
        mNumberDao = GameApplication.getInstances().getDaoSession().getNumberDao();
    }

    public static long create(String max, String min) {
        Number number = new Number();
        number.setMaxNumber(max);
        number.setMinNumber(min);
        return mNumberDao.insertOrReplace(number);
    }

    public static Number getNumber(long rowId) {
        return mNumberDao.loadByRowId(rowId);
    }
}
