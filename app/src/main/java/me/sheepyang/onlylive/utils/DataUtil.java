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
        GoodsUtil.create("假冒茅台", "瓶", "1200", "100");
        GoodsUtil.create("黑心棉", "件", "999", "90");
        GoodsUtil.create("北京户口", "本", "120000", "25000");
        GoodsUtil.create("名校学历", "本", "80000", "6000");
        GoodsUtil.create("走私海洛因", "包", "10000", "4000");
        GoodsUtil.create("高考答案", "份", "700000", "300000");
        GoodsUtil.create("走私汽车", "辆", "100000", "20000");
        GoodsUtil.create("水货手机", "部", "15000", "1800");
        GoodsUtil.create("劣质化妆品", "盒", "1500", "200");
    }

    /**
     * 初始化所有突发事件
     */
    private static void initEvent() {
        EventUtil.deleteAll();
        // 好事件
        initGoodEvent();
        // 需要选择的事件
//        initSelectEvent();
    }

    private static void initGoodEvent() {
        new EventUtil.Builder()
                .setTitle("类型一：仅有物品的事件")
                .setMessage("内容，仅有物品的事件")
                .addGoods(GoodsUtil.getGoods("北京户口"))
                .addGoods(GoodsUtil.getGoods("走私海洛因"))
                .addGoods(GoodsUtil.getGoods("高考答案"))
                .addGoods(GoodsUtil.getGoods("劣质化妆品"))
                .addGoods(GoodsUtil.getGoods("假冒茅台"))
                .setIsGood(true)
                .create();

        new EventUtil.Builder()
                .setTitle("类型二：仅有现金的事件")
                .setMessage("内容，仅有现金的事件")
                .setCash(NumberUtil.getNumber("-2000", "4", "0.1"))
                .setIsGood(true)
                .create();

        new EventUtil.Builder()
                .setTitle("类型三：仅有负债的事件")
                .setMessage("内容，仅有负债的事件")
                .setDebt(NumberUtil.getNumber("3000", "5", "1"))
                .setIsGood(true)
                .create();

        new EventUtil.Builder()
                .setTitle("类型四：仅有存款的事件")
                .setMessage("内容，仅有存款的事件")
                .setDeposit(NumberUtil.getNumber("-4000", "4", "0.2"))
                .setIsGood(true)
                .create();

        new EventUtil.Builder()
                .setTitle("类型五：有物品有现金的事件")
                .setMessage("内容，有物品有现金的事件")
                .setCash(NumberUtil.getNumber("5000", "2", "0.5"))
                .addGoods(GoodsUtil.getGoods("水货手机"))
                .addGoods(GoodsUtil.getGoods("假冒茅台"))
                .addGoods(GoodsUtil.getGoods("黑心棉"))
                .setIsGood(true)
                .create();

        new EventUtil.Builder()
                .setTitle("类型六：有现金有负债有存款的事件")
                .setMessage("内容，有现金有负债有存款的事件")
                .setCash(NumberUtil.getNumber("-6000", "2", "0.5"))
                .setDebt(NumberUtil.getNumber("7000", "2", "0.5"))
                .setDeposit(NumberUtil.getNumber("-8000", "2", "0.5"))
                .setIsGood(true)
                .create();
    }

    private static void initSelectEvent() {

    }

    /**
     * 初始化所有新聞事件
     */
    private static void initNews() {

    }

}
