package me.sheepyang.onlylive.utils;

import android.content.Context;

import me.sheepyang.onlylive.app.Constants;
import me.sheepyang.onlylive.utils.data.EventUtil;
import me.sheepyang.onlylive.utils.data.GoodsUtil;
import me.sheepyang.onlylive.utils.data.NumberUtil;

/**
 * 游戏数据相关工具类
 * Created by SheepYang on 2016/10/13 00:24.
 */
public class DataUtil {

    public static void initGame(Context context) {
        initGameCache(context);
        initGoods();
        initEvent();
        initNews();
    }

    /**
     * 初始化游戏起始数据
     *
     * @param context
     */
    private static void initGameCache(Context context) {
        CacheUtil.setInitGameCash(context, Constants.INIT_GAME_CASH);// 现金
        CacheUtil.setInitGameDebt(context, Constants.INIT_GAME_DEBT);// 负债
        CacheUtil.setInitGameDeposit(context, Constants.INIT_GAME_DEPOSIT);// 存款
        CacheUtil.setInitGameHealth(context, Constants.INIT_GAME_HEALTH);// 健康
        CacheUtil.setInitGameHouse(context, Constants.INIT_GAME_HOUSE);// 使用空间
        CacheUtil.setInitGameHouseTotal(context, Constants.INIT_GAME_HOUSE_TOTAL);// 总房子数量
        CacheUtil.setInitGameWeek(context, Constants.INIT_GAME_WEEK);// 当前周数
        CacheUtil.setInitGameWeekTotal(context, Constants.INIT_GAME_WEEK_TOTAL);// 游戏总周数
        CacheUtil.setInitGameShopGoodsNumber(context, Constants.INIT_GAME_SHOP_GOODS_NUMBER);// 商店出售物品数
        CacheUtil.setInitGameDebtRateMax(context, Constants.INIT_GAME_DEBT_RATE_MAX);// 负债利息最高倍率
        CacheUtil.setInitGameDebtRateMin(context, Constants.INIT_GAME_DEBT_RATE_MIN);// 负债利息最低倍率
        CacheUtil.setInitGameGoodsNumber(context, Constants.INIT_GAME_GOODS_NUMBER);// 物品最多获得个数
        CacheUtil.setInitGameHealthCost(context, Constants.INIT_GAME_HEALTH_COST);// 恢复100健康费用
        CacheUtil.setInitGameHouseLevel1Cost(context, Constants.INIT_GAME_HOUSE_LEVEL1_COST);// 房子空间1数量
        CacheUtil.setInitGameHouseLevel2Cost(context, Constants.INIT_GAME_HOUSE_LEVEL2_COST);// 房子空间2数量
        CacheUtil.setInitGameHouseLevel3Cost(context, Constants.INIT_GAME_HOUSE_LEVEL3_COST);// 房子空间3数量
        CacheUtil.setInitGameHouseLevel4Cost(context, Constants.INIT_GAME_HOUSE_LEVEL4_COST);// 房子空间4数量
        CacheUtil.setInitGameHouseLevel1(context, Constants.INIT_GAME_HOUSE_LEVEL1);// 升级房子费用1
        CacheUtil.setInitGameHouseLevel2(context, Constants.INIT_GAME_HOUSE_LEVEL2);// 升级房子费用2
        CacheUtil.setInitGameHouseLevel3(context, Constants.INIT_GAME_HOUSE_LEVEL3);// 升级房子费用3
        CacheUtil.setInitGameHouseLevel4(context, Constants.INIT_GAME_HOUSE_LEVEL4);// 升级房子费用4

        CacheUtil.setInit(context, true);
    }

    /**
     * 初始化所有物品信息
     */
    private static void initGoods() {
        GoodsUtil.deleteAll();
        new GoodsUtil.Builder()
                .setName("假冒茅台")
                .setUnit("瓶")
                .setPrice(NumberUtil.create("1200", "3.5", "0.2"))
                .create();

        new GoodsUtil.Builder()
                .setName("黑心棉")
                .setUnit("件")
                .setPrice(NumberUtil.create("999", "2.5", "0.3"))
                .create();

        new GoodsUtil.Builder()
                .setName("北京户口")
                .setUnit("本")
                .setPrice(NumberUtil.create("120000", "2.2", "1"))
                .create();

        new GoodsUtil.Builder()
                .setName("名校学历")
                .setUnit("本")
                .setPrice(NumberUtil.create("80000", "2", "0.9"))
                .create();

        new GoodsUtil.Builder()
                .setName("走私海洛因")
                .setUnit("包")
                .setPrice(NumberUtil.create("10000", "3", "0.1"))
                .create();

        new GoodsUtil.Builder()
                .setName("高考答案")
                .setUnit("份")
                .setPrice(NumberUtil.create("700000", "4", "0.8"))
                .create();

        new GoodsUtil.Builder()
                .setName("走私汽车")
                .setUnit("辆")
                .setPrice(NumberUtil.create("100000", "2", "1"))
                .create();

        new GoodsUtil.Builder()
                .setName("水货手机")
                .setUnit("部")
                .setPrice(NumberUtil.create("15000", "1.5", "0.3"))
                .create();

        new GoodsUtil.Builder()
                .setName("劣质化妆品")
                .setUnit("盒")
                .setPrice(NumberUtil.create("1500", "2", "0.5"))
                .create();
    }

    /**
     * 初始化所有突发事件
     */
    private static void initEvent() {
        EventUtil.deleteAll();
        // 好事件
        initGoodEvent();
        // 需要选择的事件
        initSelectEvent();
    }

    private static void initGoodEvent() {
        new EventUtil.Builder()
                .setIsGood(true)
                .setTitle("类型一：仅有物品的事件")
                .setMessage("内容，仅有物品的事件")
                .addGoods(GoodsUtil.getGoods("北京户口"))
                .addGoods(GoodsUtil.getGoods("走私海洛因"))
                .addGoods(GoodsUtil.getGoods("高考答案"))
                .addGoods(GoodsUtil.getGoods("劣质化妆品"))
                .addGoods(GoodsUtil.getGoods("假冒茅台"))
                .create();

        new EventUtil.Builder()
                .setIsGood(true)
                .setTitle("类型二：仅有现金的事件")
                .setMessage("内容，仅有现金的事件")
                .setCash(NumberUtil.create("-1000", "4", "0.1"))
                .create();

        new EventUtil.Builder()
                .setIsGood(true)
                .setTitle("类型三：仅有负债的事件")
                .setMessage("内容，仅有负债的事件")
                .setDebt(NumberUtil.create("1500", "5", "1"))
                .create();

        new EventUtil.Builder()
                .setIsGood(true)
                .setTitle("类型四：仅有存款的事件")
                .setMessage("内容，仅有存款的事件")
                .setDeposit(NumberUtil.create("-2000", "4", "0.2"))
                .create();

        new EventUtil.Builder()
                .setIsGood(true)
                .setTitle("类型五：有物品有现金的事件")
                .setMessage("内容，有物品有现金的事件")
                .setCash(NumberUtil.create("5000", "2", "0.5"))
                .addGoods(GoodsUtil.getGoods("水货手机"))
                .addGoods(GoodsUtil.getGoods("假冒茅台"))
                .addGoods(GoodsUtil.getGoods("黑心棉"))
                .create();

        new EventUtil.Builder()
                .setIsGood(true)
                .setTitle("类型六：有现金有负债有存款的事件")
                .setMessage("内容，有现金有负债有存款的事件")
                .setCash(NumberUtil.create("-3000", "2", "0.5"))
                .setDebt(NumberUtil.create("3500", "2", "0.5"))
                .setDeposit(NumberUtil.create("-4000", "2", "0.5"))
                .create();

        new EventUtil.Builder()
                .setIsGood(true)
                .setTitle("类型七：所有类型都有的事件")
                .setMessage("内容，所有类型都有的事件")
                .setCash(NumberUtil.create("-3000", "2", "0.5"))
                .setDebt(NumberUtil.create("3500", "2", "0.5"))
                .setDeposit(NumberUtil.create("-4000", "2", "0.5"))
                .setHealth(NumberUtil.create("-40", "1", "0.5"))
                .addGoods(GoodsUtil.getGoods("北京户口"))
                .addGoods(GoodsUtil.getGoods("走私海洛因"))
                .addGoods(GoodsUtil.getGoods("高考答案"))
                .addGoods(GoodsUtil.getGoods("劣质化妆品"))
                .addGoods(GoodsUtil.getGoods("假冒茅台"))
                .create();

        new EventUtil.Builder()
                .setIsGood(true)
                .setTitle("类型八：现金负债存款事件")
                .setMessage("内容，现金负债存款事件")
                .setCash(NumberUtil.create("2000", "4", "-4"))
                .setDebt(NumberUtil.create("1500", "4", "-4"))
                .setDeposit(NumberUtil.create("-2000", "4", "-4"))
                .create();

    }

    private static void initSelectEvent() {
        new EventUtil.Builder()
                .setIsNeedSelect(true)
                .setTitle("这是选择事件")
                .setMessage("路遇老奶奶摔倒，要不要扶起她？")
                .setOkText("扶")
                .setCancelText("不扶")

                .setResultOKGoodTitle("你扶起了老奶奶，结果是好的")
                .setResultOKBadTitle("你扶起了老奶奶，结果是坏的")
                .setResultCancelGoodTitle("你假装没看到，结果是好的")
                .setResultCancelBadTitle("你假装没看到，结果是坏的")

                .setResultOKGoodMsg("老奶奶奖励你１００块")
                .setResultCancelGoodMsg("这个老太婆是骗子，你举报成功，奖励５０")
                .setResultOKBadMsg("老奶奶抓住你不放，说是你撞倒了她")
                .setResultCancelBadMsg("跑得太快，跌入下水道")

                .setCash(NumberUtil.create("6000", "2", "0.2"))
                .setDebt(NumberUtil.create("2000", "2", "0.2"))
                .setDeposit(NumberUtil.create("4000", "2", "0.2"))
                .setHealth(NumberUtil.create("20", "1", "0.2"))
                .addGoods(GoodsUtil.getGoods("北京户口"))
                .addGoods(GoodsUtil.getGoods("走私海洛因"))
                .addGoods(GoodsUtil.getGoods("高考答案"))
                .addBadGoods(GoodsUtil.getGoods("劣质化妆品"))
                .addBadGoods(GoodsUtil.getGoods("假冒茅台"))
                .addBadGoods(GoodsUtil.getGoods("黑心棉"))
                .create();
    }

    /**
     * 初始化所有新聞事件
     */
    private static void initNews() {

    }

}
