package me.sheepyang.onlylive.utils;

import android.content.Context;
import android.text.TextUtils;

import java.math.BigDecimal;

/**
 * Created by SheepYang on 2016/11/18.
 */

public class MathUtil {

    public static String getNumber(String number, int scalse) {
        return new BigDecimal(number)
                .setScale(scalse, BigDecimal.ROUND_HALF_UP)
                .toString();
    }

    public static String abs(String a) {
        if (TextUtils.isEmpty(a)) {
            a = "0";
        }
        return new BigDecimal(a)
                .abs()
                .setScale(0, BigDecimal.ROUND_HALF_UP)
                .toBigInteger()
                .toString();
    }

    /**
     * 加法 四舍五入
     *
     * @param a
     * @param b
     * @return
     */
    public static String add(String a, String b) {
        if (TextUtils.isEmpty(a)) {
            a = "0";
        }
        if (TextUtils.isEmpty(b)) {
            b = "0";
        }
        return new BigDecimal(a)
                .add(new BigDecimal(b))
                .setScale(0, BigDecimal.ROUND_HALF_UP)
                .toBigInteger()
                .toString();
    }

    /**
     * 减法 四舍五入
     *
     * @param a
     * @param b
     * @return
     */
    public static String subtract(String a, String b) {
        if (TextUtils.isEmpty(a)) {
            a = "0";
        }
        if (TextUtils.isEmpty(b)) {
            b = "0";
        }
        return new BigDecimal(a)
                .subtract(new BigDecimal(b))
                .setScale(0, BigDecimal.ROUND_HALF_UP)
                .toBigInteger()
                .toString();
    }

    /**
     * 乘法 四舍五入
     *
     * @param a
     * @param b
     * @return
     */
    public static String multiply(String a, String b, int scalse) {
        if (TextUtils.isEmpty(a)) {
            a = "0";
        }
        if (TextUtils.isEmpty(b)) {
            b = "0";
        }
        return new BigDecimal(a)
                .multiply(new BigDecimal(b))
                .setScale(scalse, BigDecimal.ROUND_HALF_UP)
                .toString();
    }

    /**
     * 乘法 四舍五入
     *
     * @param a
     * @param b
     * @return
     */
    public static String multiply(String a, String b) {
        if (TextUtils.isEmpty(a)) {
            a = "0";
        }
        if (TextUtils.isEmpty(b)) {
            b = "0";
        }
        return new BigDecimal(a)
                .multiply(new BigDecimal(b))
                .setScale(0, BigDecimal.ROUND_HALF_UP)
                .toBigInteger()
                .toString();
    }

    /**
     * 除法 四舍五入 取整
     *
     * @param a 被除数
     * @param b 除数
     * @return
     */
    public static String divide(String a, String b) {
        if (TextUtils.isEmpty(a)) {
            a = "0";
        }
        if (TextUtils.isEmpty(b)) {
            b = "0";
        }
        return new BigDecimal(a)
                .divide(new BigDecimal(b), BigDecimal.ROUND_HALF_UP)
                .toBigInteger()
                .toString();
    }

    public static String divideRoundDown(int scalse, String a, String b) {
        if (TextUtils.isEmpty(a)) {
            a = "0";
        }
        if (TextUtils.isEmpty(b)) {
            b = "0";
        }
        return new BigDecimal(a)
                .divide(new BigDecimal(b), scalse, BigDecimal.ROUND_DOWN)
                .toBigInteger()
                .toString();
    }

    /**
     * 除法 四舍五入
     *
     * @param a 被除数
     * @param b 除数
     * @return
     */
    public static String divide(String a, String b, int scale) {
        if (TextUtils.isEmpty(a)) {
            a = "0";
        }
        if (TextUtils.isEmpty(b)) {
            b = "0";
        }
        return new BigDecimal(a)
                .divide(new BigDecimal(b), scale, BigDecimal.ROUND_HALF_UP)
                .toString();
    }

    /**
     * 比较大小
     *
     * @param a
     * @param b
     * @return -1 a < b; 0 a = b; 1 a > b;
     */
    public static int compareTo(String a, String b) {
        if (TextUtils.isEmpty(a)) {
            a = "0";
        }
        if (TextUtils.isEmpty(b)) {
            b = "0";
        }
        return new BigDecimal(a).compareTo(new BigDecimal(b));
    }

    /**
     * 判断两个数是否相等
     *
     * @param a
     * @param b
     * @return
     */
    public static boolean eq(String a, String b) {
        if (TextUtils.isEmpty(a)) {
            a = "0";
        }
        if (TextUtils.isEmpty(b)) {
            b = "0";
        }
        if (compareTo(a, b) == 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断a 是否小于 b
     *
     * @param a
     * @param b
     * @return
     */
    public static boolean lt(String a, String b) {
        if (TextUtils.isEmpty(a)) {
            a = "0";
        }
        if (TextUtils.isEmpty(b)) {
            b = "0";
        }
        if (compareTo(a, b) == -1) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断a 是否小于等于 b
     *
     * @param a
     * @param b
     * @return
     */
    public static boolean le(String a, String b) {
        if (TextUtils.isEmpty(a)) {
            a = "0";
        }
        if (TextUtils.isEmpty(b)) {
            b = "0";
        }
        if (compareTo(a, b) != 1) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断a 是否大于 b
     *
     * @param a
     * @param b
     * @return
     */
    public static boolean gt(String a, String b) {
        if (TextUtils.isEmpty(a)) {
            a = "0";
        }
        if (TextUtils.isEmpty(b)) {
            b = "0";
        }
        if (compareTo(a, b) == 1) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断a 是否大于等于 b
     *
     * @param a
     * @param b
     * @return
     */
    public static boolean ge(String a, String b) {
        if (TextUtils.isEmpty(a)) {
            a = "0";
        }
        if (TextUtils.isEmpty(b)) {
            b = "0";
        }
        if (compareTo(a, b) != -1) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 获取计算利息后的money
     */
    public static String getInterest(Context context, String money) {
        String percent = MathUtil.divide("" + RandomUtil.getRandomNum(Integer.valueOf(MathUtil.multiply("100", CacheUtil.getInitGameDebtRateMax(context))), Integer.valueOf(MathUtil.multiply("100", CacheUtil.getInitGameDebtRateMin(context)))), "100", 2);
        return MathUtil.multiply(money, percent);
    }

    public static boolean checkNumber(String number) {
        boolean isRight;
        try {
            new BigDecimal(number);
            isRight = true;
        } catch (NumberFormatException e) {
            MyLog.i(e.getMessage());
            isRight = false;
        }
        return isRight;
    }

    public static void main(String[] args) {
        String a = "2";
        String b = "3";
        System.out.println("加法：" + a + " + " + b + "\n结果：" + add(a, b) + "\n");
        System.out.println("减法：" + a + " - " + b + "\n结果：" + subtract(a, b) + "\n");
        System.out.println("乘法：" + a + " * " + b + "\n结果：" + multiply(a, b) + "\n");
        try {
            System.out.println("除法：" + a + " / " + b + "\n结果：" + divide(a, b, 2) + "\n");
        } catch (ArithmeticException e) {
            System.out.println("除法：" + a + " / " + b + "\n结果：除数不能等于0\n");
        }
        System.out.println("比较大小：" + a + " compareTo " + b + "\n结果：" + compareTo(a, b) + " (-1小于; 0等于; 1大于)\n");
        System.out.println("是否相等：" + a + " eq " + b + " ?\n结果：" + eq(a, b) + "\n");
        System.out.println("是否小于：" + a + " lt " + b + " ?\n结果：" + lt(a, b) + "\n");
        System.out.println("是否小于等于：" + a + " le " + b + " ?\n结果：" + le(a, b) + "\n");
        System.out.println("是否大于：" + a + " gt " + b + " ?\n结果：" + gt(a, b) + "\n");
        System.out.println("是否大于等于：" + a + " ge " + b + " ?\n结果：" + ge(a, b) + "\n");
    }
}
