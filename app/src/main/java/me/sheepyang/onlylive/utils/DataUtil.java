package me.sheepyang.onlylive.utils;

import me.sheepyang.onlylive.utils.data.EventUtil;
import me.sheepyang.onlylive.utils.data.GoodsUtil;
import me.sheepyang.onlylive.utils.data.NumberUtil;

/**
 * 游戏数据相关工具类
 * Created by SheepYang on 2016/10/13 00:24.
 */
public class DataUtil {

    public static void initGame() {
        initGoods();
        initEvent();
        initNews();
    }

    /**
     * 初始化所有物品信息
     */
    private static void initGoods() {
        GoodsUtil.deleteAll();
        new GoodsUtil.Builder()
                .setName("假冒茅台")
                .setUnit("瓶")
                .setPrice(NumberUtil.create("1200", "2", "1"))
                .create();

        new GoodsUtil.Builder()
                .setName("黑心棉")
                .setUnit("件")
                .setPrice(NumberUtil.create("999", "2", "1"))
                .create();

        new GoodsUtil.Builder()
                .setName("北京户口")
                .setUnit("本")
                .setPrice(NumberUtil.create("120000", "2", "1"))
                .create();

        new GoodsUtil.Builder()
                .setName("名校学历")
                .setUnit("本")
                .setPrice(NumberUtil.create("80000", "2", "1"))
                .create();

        new GoodsUtil.Builder()
                .setName("走私海洛因")
                .setUnit("包")
                .setPrice(NumberUtil.create("10000", "2", "1"))
                .create();

        new GoodsUtil.Builder()
                .setName("高考答案")
                .setUnit("份")
                .setPrice(NumberUtil.create("700000", "2", "1"))
                .create();

        new GoodsUtil.Builder()
                .setName("走私汽车")
                .setUnit("辆")
                .setPrice(NumberUtil.create("100000", "2", "1"))
                .create();

        new GoodsUtil.Builder()
                .setName("水货手机")
                .setUnit("部")
                .setPrice(NumberUtil.create("15000", "2", "1"))
                .create();

        new GoodsUtil.Builder()
                .setName("劣质化妆品")
                .setUnit("盒")
                .setPrice(NumberUtil.create("1500", "2", "1"))
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
                .setCash(NumberUtil.create("-2000", "4", "0.1"))
                .create();

        new EventUtil.Builder()
                .setIsGood(true)
                .setTitle("类型三：仅有负债的事件")
                .setMessage("内容，仅有负债的事件")
                .setDebt(NumberUtil.create("3000", "5", "1"))
                .create();

        new EventUtil.Builder()
                .setIsGood(true)
                .setTitle("类型四：仅有存款的事件")
                .setMessage("内容，仅有存款的事件")
                .setDeposit(NumberUtil.create("-4000", "4", "0.2"))
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
                .setCash(NumberUtil.create("-6000", "2", "0.5"))
                .setDebt(NumberUtil.create("7000", "2", "0.5"))
                .setDeposit(NumberUtil.create("-8000", "2", "0.5"))
                .create();

        new EventUtil.Builder()
                .setIsGood(true)
                .setTitle("类型七：所有类型都有的事件")
                .setMessage("内容，所有类型都有的事件")
                .setCash(NumberUtil.create("-6000", "2", "0.5"))
                .setDebt(NumberUtil.create("7000", "2", "0.5"))
                .setDeposit(NumberUtil.create("-8000", "2", "0.5"))
                .setHealth(NumberUtil.create("-20", "1", "0.5"))
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
                .setDebt(NumberUtil.create("3000", "4", "-4"))
                .setDeposit(NumberUtil.create("4000", "4", "-4"))
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
                .setResultOKGoodMsg("老奶奶奖励你１００块")
                .setResultOKBadMsg("老奶奶抓住你不放，说是你撞倒了她")
                .setResultCancelGoodTitle("你假装没看到，结果是好的")
                .setResultCancelBadTitle("你假装没看到，结果是坏的")
                .setResultCancelGoodMsg("这个老太婆是骗子，你举报成功，奖励５０")
                .setResultCancelBadMsg("跑得太快，跌入下水道")
                .setCash(NumberUtil.create("6000", "2", "0.2"))
                .setDebt(NumberUtil.create("7000", "2", "0.2"))
                .setDeposit(NumberUtil.create("8000", "2", "0.2"))
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
