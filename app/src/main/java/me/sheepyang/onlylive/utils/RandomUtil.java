package me.sheepyang.onlylive.utils;

import java.util.Random;

import me.sheepyang.onlylive.entity.Number;

import static android.R.attr.max;

/**
 * 随机数工具类
 * Created by SheepYang on 2016/10/14 23:09.
 */

public class RandomUtil {
    public static int getRandomNum(int min, int max) {
        return new Random().nextInt(max - min + 1) + min;
    }

    public static int getRandomNum(Number number) {
        return new Random().nextInt(number.getMaxNumber() - number.getMinNumber() + 1) + number.getMinNumber();
    }
}
