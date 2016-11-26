package me.sheepyang.onlylive.utils;

import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.math.BigDecimal;

/**
 * Created by SheepYang on 2016/11/26 13:13.
 */

@RunWith(AndroidJUnit4.class)
public class MathUtilTest {

    @Test
    public void getNumberTest() {
        String number = "-00001002.232";
        MyLog.i("number:" + number + ", getNumber result:" + MathUtil.getNumber(number, 2));
    }

    @Test
    public void checkNumberTest() {
        String number = "-1002.2";
        MyLog.i("number:" + number + ", check result:" + MathUtil.checkNumber(number));
    }
}
