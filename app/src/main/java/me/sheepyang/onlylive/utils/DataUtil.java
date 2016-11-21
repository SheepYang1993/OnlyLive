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
        initJoinGoodsToEvent();
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
        // 仅有物品的事件
        EventUtil.create("标题一：仅有物品的事件", "内容，仅有物品的事件");
        // 仅有金钱的事件
        EventUtil.create("标题二：仅有金钱的事件", "内容，仅有金钱的事件", NumberUtil.getNumber("200", "4", "0.2"));
        // 有物品有金钱的事件
        EventUtil.create("标题三：有物品有金钱的事件", "内容，有物品有金钱的事件", NumberUtil.getNumber("500", "2", "0.5"));
    }

    private static void initSelectEvent() {

    }

    private static void initJoinGoodsToEvent() {
        GoodsUtil.joinGoodsToEvent(GoodsUtil.getGoods("北京户口"), EventUtil.getEvent("标题一：仅有物品的事件"));
        GoodsUtil.joinGoodsToEvent(GoodsUtil.getGoods("走私海洛因"), EventUtil.getEvent("标题一：仅有物品的事件"));
        GoodsUtil.joinGoodsToEvent(GoodsUtil.getGoods("高考答案"), EventUtil.getEvent("标题一：仅有物品的事件"));
        GoodsUtil.joinGoodsToEvent(GoodsUtil.getGoods("劣质化妆品"), EventUtil.getEvent("标题一：仅有物品的事件"));
        GoodsUtil.joinGoodsToEvent(GoodsUtil.getGoods("水货手机"), EventUtil.getEvent("标题三：有物品有金钱的事件"));
        GoodsUtil.joinGoodsToEvent(GoodsUtil.getGoods("黑心棉"), EventUtil.getEvent("标题三：有物品有金钱的事件"));
    }

    /**
     * 初始化所有新聞事件
     */
    private static void initNews() {

    }

}
