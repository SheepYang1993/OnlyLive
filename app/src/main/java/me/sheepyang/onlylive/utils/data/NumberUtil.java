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

    public static Number getNumber(long rowId) {
        return mNumberDao.loadByRowId(rowId);
    }

    public static Number create(String strNum, String maxPercent, String minPercent) {
        Number number = new Number();
        number.setNumber(strNum);
        number.setMaxPercent(maxPercent);
        number.setMinPercent(minPercent);
        long rowId = mNumberDao.insertOrReplace(number);
        return getNumber(rowId);
    }
}
