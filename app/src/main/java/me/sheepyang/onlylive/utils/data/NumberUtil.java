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

    public static long createNumber(String maxNumber, String minNumer) {
        Number number = new Number();
        number.setMaxNumber(maxNumber);
        number.setMinNumber(minNumer);
        return mNumberDao.insertOrReplace(number);
    }

    public static long createPercent(String strNum, String maxPercent, String minPercent) {
        Number number = new Number();
        number.setNumber(strNum);
        number.setMaxPercent(maxPercent);
        number.setMinPercent(minPercent);
        return mNumberDao.insertOrReplace(number);
    }

    public static Number getNumber(long rowId) {
        return mNumberDao.loadByRowId(rowId);
    }

    public static Number getNumber(String number, String maxPercent, String minPercent) {
        long rowId = NumberUtil.createPercent(number, maxPercent, minPercent);
        return mNumberDao.loadByRowId(rowId);
    }
}
