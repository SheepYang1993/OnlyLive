package me.sheepyang.onlylive.utils;

import android.content.Context;

import static me.sheepyang.onlylive.utils.DataUtil.INIT_GAME_HOUSE_LEVEL1;
import static me.sheepyang.onlylive.utils.DataUtil.INIT_GAME_HOUSE_LEVEL2;
import static me.sheepyang.onlylive.utils.DataUtil.INIT_GAME_HOUSE_LEVEL3;
import static me.sheepyang.onlylive.utils.DataUtil.INIT_GAME_HOUSE_LEVEL4;

/**
 * Created by SheepYang on 2016/11/25.
 */

public class CacheUtil {

    public static final String IS_INIT = "isInit";
    public static final String INIT_GAME_CASH = "INIT_GAME_CASH";// 现金
    public static final String INIT_GAME_DEBT = "INIT_GAME_DEBT";// 负债
    public static final String INIT_GAME_DEPOSIT = "INIT_GAME_DEPOSIT";// 存款
    public static final String INIT_GAME_HEALTH = "INIT_GAME_HEALTH";// 健康
    public static final String INIT_GAME_HOUSE = "INIT_GAME_HOUSE";// 房子数量
    public static final String INIT_GAME_WEEK = "INIT_GAME_WEEK";// 周数
    public static final String INIT_GAME_HOUSE_TOTAL = "INIT_GAME_HOUSE_TOTAL";// 总房子数量
    public static final String INIT_GAME_WEEK_TOTAL = "INIT_GAME_WEEK_TOTAL";// 游戏总周数
    public static final String INIT_GAME_SHOP_GOODS_NUMBER = "INIT_GAME_SHOP_GOODS_NUMBER";// 商店销售物品数量
    public static final String INIT_GAME_DEBT_RATE_MAX = "INIT_GAME_DEBT_RATE_MAX";// 负债利息最高倍率
    public static final String INIT_GAME_DEBT_RATE_MIN = "INIT_GAME_DEBT_RATE_MIN";// 负债利息最低倍率
    public static final String INIT_GAME_GOODS_NUMBER = "INIT_GAME_GOODS_NUMBER";// 随机最多获得物品个数
    public static final String INIT_GAME_HEALTH_COST = "INIT_GAME_HEALTH_COST";// 恢复100健康花费
    public static final String INIT_GAME_HOUSE_LEVEL1_COST = "INIT_GAME_HOUSE_LEVEL1_COST";// 升级房子费用1
    public static final String INIT_GAME_HOUSE_LEVEL2_COST = "INIT_GAME_HOUSE_LEVEL2_COST";// 升级房子费用2
    public static final String INIT_GAME_HOUSE_LEVEL3_COST = "INIT_GAME_HOUSE_LEVEL3_COST";// 升级房子费用3
    public static final String INIT_GAME_HOUSE_LEVEL4_COST = "INIT_GAME_HOUSE_LEVEL4_COST";// 升级房子费用4


    /**
     * 获取游戏是否初始化
     *
     * @param context
     * @return
     */
    public static boolean isInit(Context context) {
        return SPUtil.getBoolean(context, IS_INIT, false);
    }

    /**
     * @param context
     * @param isInit  游戏是否初始化
     */
    public static void setInit(Context context, boolean isInit) {
        SPUtil.putBoolean(context, IS_INIT, isInit);
    }

    /**
     * 获取初始现金
     *
     * @param context
     * @return
     */
    public static String getInitGameCash(Context context) {
        return SPUtil.getString(context, INIT_GAME_CASH);
    }

    public static void setInitGameCash(Context context, String cash) {
        SPUtil.putString(context, INIT_GAME_CASH, cash);
    }

    /**
     * 获取初始负债
     *
     * @param context
     * @return
     */
    public static String getInitGameDebt(Context context) {
        return SPUtil.getString(context, INIT_GAME_DEBT);
    }

    public static void setInitGameDebt(Context context, String debt) {
        SPUtil.putString(context, INIT_GAME_DEBT, debt);
    }

    /**
     * 获取初始存款
     *
     * @param context
     * @return
     */
    public static String getInitGameDeposit(Context context) {
        return SPUtil.getString(context, INIT_GAME_DEPOSIT);
    }

    public static void setInitGameDeposit(Context context, String deposit) {
        SPUtil.putString(context, INIT_GAME_DEPOSIT, deposit);
    }

    /**
     * 获取初始健康
     *
     * @param context
     * @return
     */
    public static String getInitGameHealth(Context context) {
        return SPUtil.getString(context, INIT_GAME_HEALTH);
    }

    public static void setInitGameHealth(Context context, String health) {
        SPUtil.putString(context, INIT_GAME_HEALTH, health);
    }

    /**
     * 获取初始房间已使用空间
     *
     * @param context
     * @return
     */
    public static String getInitGameHouse(Context context) {
        return SPUtil.getString(context, INIT_GAME_HOUSE);
    }

    public static void setInitGameHouse(Context context, String house) {
        SPUtil.putString(context, INIT_GAME_HOUSE, house);
    }

    /**
     * 获取初始房间总容量
     *
     * @param context
     * @return
     */
    public static String getInitGameHouseTotal(Context context) {
        return SPUtil.getString(context, INIT_GAME_HOUSE_TOTAL);
    }

    public static void setInitGameHouseTotal(Context context, String houseTotal) {
        SPUtil.putString(context, INIT_GAME_HOUSE_TOTAL, houseTotal);
    }

    /**
     * 获取初始周数
     *
     * @param context
     * @return
     */
    public static String getInitGameWeek(Context context) {
        return SPUtil.getString(context, INIT_GAME_WEEK);
    }

    public static void setInitGameWeek(Context context, String week) {
        SPUtil.putString(context, INIT_GAME_WEEK, week);
    }

    /**
     * 获取初始总周数
     *
     * @param context
     * @return
     */
    public static String getInitGameWeekTotal(Context context) {
        return SPUtil.getString(context, INIT_GAME_WEEK_TOTAL);
    }

    public static void setInitGameWeekTotal(Context context, String weekTotal) {
        SPUtil.putString(context, INIT_GAME_WEEK_TOTAL, weekTotal);
    }

    /**
     * 获取商店物品数
     *
     * @param context
     * @return
     */
    public static String getInitGameShopGoodsNumber(Context context) {
        return SPUtil.getString(context, INIT_GAME_SHOP_GOODS_NUMBER);
    }

    public static void setInitGameShopGoodsNumber(Context context, String shopGoodsNumber) {
        SPUtil.putString(context, INIT_GAME_SHOP_GOODS_NUMBER, shopGoodsNumber);
    }

    public static String getInitGameDebtRateMax(Context context) {
        return SPUtil.getString(context, INIT_GAME_DEBT_RATE_MAX);
    }

    public static void setInitGameDebtRateMax(Context context, String cash) {
        SPUtil.putString(context, INIT_GAME_DEBT_RATE_MAX, cash);
    }

    public static String getInitGameDebtRateMin(Context context) {
        return SPUtil.getString(context, INIT_GAME_DEBT_RATE_MIN);
    }

    public static void setInitGameDebtRateMin(Context context, String cash) {
        SPUtil.putString(context, INIT_GAME_DEBT_RATE_MIN, cash);
    }

    public static String getInitGameGoodsNumber(Context context) {
        return SPUtil.getString(context, INIT_GAME_GOODS_NUMBER);
    }

    public static void setInitGameGoodsNumber(Context context, String goodsNumber) {
        SPUtil.putString(context, INIT_GAME_GOODS_NUMBER, goodsNumber);
    }

    public static String getInitGameHealthCost(Context context) {
        return SPUtil.getString(context, INIT_GAME_HEALTH_COST);
    }

    public static void setInitGameHealthCost(Context context, String goodsNumber) {
        SPUtil.putString(context, INIT_GAME_HEALTH_COST, goodsNumber);
    }

    public static String getInitGameHouseLevel1Cost(Context context) {
        return SPUtil.getString(context, INIT_GAME_HOUSE_LEVEL1_COST);
    }

    public static void setInitGameHouseLevel1Cost(Context context, String goodsNumber) {
        SPUtil.putString(context, INIT_GAME_HOUSE_LEVEL1_COST, goodsNumber);
    }

    public static String getInitGameHouseLevel2Cost(Context context) {
        return SPUtil.getString(context, INIT_GAME_HOUSE_LEVEL2_COST);
    }

    public static void setInitGameHouseLevel2Cost(Context context, String goodsNumber) {
        SPUtil.putString(context, INIT_GAME_HOUSE_LEVEL2_COST, goodsNumber);
    }

    public static String getInitGameHouseLevel3Cost(Context context) {
        return SPUtil.getString(context, INIT_GAME_HOUSE_LEVEL3_COST);
    }

    public static void setInitGameHouseLevel3Cost(Context context, String goodsNumber) {
        SPUtil.putString(context, INIT_GAME_HOUSE_LEVEL3_COST, goodsNumber);
    }

    public static String getInitGameHouseLevel4Cost(Context context) {
        return SPUtil.getString(context, INIT_GAME_HOUSE_LEVEL4_COST);
    }

    public static void setInitGameHouseLevel4Cost(Context context, String goodsNumber) {
        SPUtil.putString(context, INIT_GAME_HOUSE_LEVEL4_COST, goodsNumber);
    }

    public static String getInitGameHouseLevel1(Context context) {
        return SPUtil.getString(context, INIT_GAME_HOUSE_LEVEL1);
    }

    public static void setInitGameHouseLevel1(Context context, String goodsNumber) {
        SPUtil.putString(context, INIT_GAME_HOUSE_LEVEL1, goodsNumber);
    }

    public static String getInitGameHouseLevel2(Context context) {
        return SPUtil.getString(context, INIT_GAME_HOUSE_LEVEL2);
    }

    public static void setInitGameHouseLevel2(Context context, String goodsNumber) {
        SPUtil.putString(context, INIT_GAME_HOUSE_LEVEL2, goodsNumber);
    }

    public static String getInitGameHouseLevel3(Context context) {
        return SPUtil.getString(context, INIT_GAME_HOUSE_LEVEL3);
    }

    public static void setInitGameHouseLevel3(Context context, String goodsNumber) {
        SPUtil.putString(context, INIT_GAME_HOUSE_LEVEL3, goodsNumber);
    }

    public static String getInitGameHouseLevel4(Context context) {
        return SPUtil.getString(context, INIT_GAME_HOUSE_LEVEL4);
    }

    public static void setInitGameHouseLevel4(Context context, String goodsNumber) {
        SPUtil.putString(context, INIT_GAME_HOUSE_LEVEL4, goodsNumber);
    }
}
