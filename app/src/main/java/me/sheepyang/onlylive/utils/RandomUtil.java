package me.sheepyang.onlylive.utils;

import java.util.Random;

import me.sheepyang.onlylive.entity.Number;

/**
 * 随机数工具类
 * Created by SheepYang on 2016/10/14 23:09.
 */

public class RandomUtil {
    public static int getRandomNum(int max, int min) {
        return new Random().nextInt(max - min + 1) + min;
    }

    /**
     * 获取随机数
     *
     * @param number
     * @return
     */
    public static String getRandomNum(Number number) {
        int intMaxPercent = Integer.valueOf(MathUtil.multiply(number.getMaxPercent(), "100"));
        int intMinPercent = Integer.valueOf(MathUtil.multiply(number.getMinPercent(), "100"));
        int tempRandom = new Random().nextInt(intMaxPercent - intMinPercent + 1) + intMinPercent;
        String percent = MathUtil.divide(tempRandom + "", "100", 2);
        return MathUtil.multiply(number.getNumber(), percent);
    }
}
