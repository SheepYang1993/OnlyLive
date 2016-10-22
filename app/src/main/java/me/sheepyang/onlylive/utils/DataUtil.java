package me.sheepyang.onlylive.utils;

import me.sheepyang.onlylive.app.Constants;
import me.sheepyang.onlylive.utils.data.EventGoodsUtil;
import me.sheepyang.onlylive.utils.data.EventUtil;
import me.sheepyang.onlylive.utils.data.GoodsUtil;

/**
 * 游戏数据相关工具类
 * Created by SheepYang on 2016/10/13 00:24.
 */
public class DataUtil {

    public static void initGameData() {
        initGoodsData();
        initEventData();
        initEventGoodsData();
        initJoinEventGoodsToEvent();
        initNewsData();
    }

    /**
     * 初始化所有突发事件
     */
    private static void initEventData() {
        EventUtil.deleteAll();
        String msg1 = "老乡送你 " + Constants.REPLACE_GOODS_NUMBER_0 + Constants.REPLACE_GOODS_UNIT_0 + "化妆品。\n\n" + Constants.REPLACE_GOODS_NAME_0 + " x" + Constants.REPLACE_GOODS_NUMBER_0;
        EventUtil.create(true, "老乡见老乡，两眼泪汪汪", msg1);

        String msg2 = "学生妹看你太帅，送你 " + Constants.REPLACE_GOODS_NUMBER_0 + Constants.REPLACE_GOODS_UNIT_0 + "有她艳照的手机。\n\n" + Constants.REPLACE_GOODS_NAME_0 + " x" + Constants.REPLACE_GOODS_NUMBER_0;
        EventUtil.create(true, "美男传说", msg2);

        String msg3 = "路边捡到 " + Constants.REPLACE_GOODS_NUMBER_0 + Constants.REPLACE_GOODS_UNIT_0 + Constants.REPLACE_GOODS_NAME_0 + "\n\n" + Constants.REPLACE_GOODS_NAME_0 + " x" + Constants.REPLACE_GOODS_NUMBER_0;
        EventUtil.create(true, "捡到地沟油", msg3);

        String msg4 = "朋友送你 " + Constants.REPLACE_GOODS_NUMBER_0 + Constants.REPLACE_GOODS_UNIT_0 + "陈年白酒。\n\n" + Constants.REPLACE_GOODS_NAME_0 + " x" + Constants.REPLACE_GOODS_NUMBER_0;
        EventUtil.create(true, "出门靠朋友", msg4);

        String msg5 = "突然有人大喊一声“城管来了”，只见好多人飞速消失。然后你捡到" + Constants.REPLACE_MONEY + "块和" + Constants.REPLACE_GOODS_NUMBER_0 + Constants.REPLACE_GOODS_UNIT_0 + Constants.REPLACE_GOODS_NAME_0;
        EventUtil.create(true, "城管来了", msg5, 200, 200);

        String msg6 = "我哩个擦滴~洗衣服的时候兜里竟发现" + Constants.REPLACE_MONEY + "块钱";
        EventUtil.create(true, "要多做家务", msg6, 200, 200);

        String msg7 = "朋友用你" + Constants.REPLACE_GOODS_NUMBER_0 + Constants.REPLACE_GOODS_UNIT_0 + Constants.REPLACE_GOODS_NAME_0;
        EventUtil.create(true, "转基因试验", msg7);
    }

    /**
     * 初始化事件物品
     */
    private static void initEventGoodsData() {
        EventGoodsUtil.deleteAll();
        EventGoodsUtil.create("劣质化妆品", "盒", 4, 1);
        EventGoodsUtil.create("水货手机", "部", 2, 1);
        EventGoodsUtil.create("地沟油", "桶", 5, 1);
        EventGoodsUtil.create("假冒茅台", "瓶", 5, 1);
        EventGoodsUtil.create("转基因大豆", "包", 10, 2);
    }

    private static void initJoinEventGoodsToEvent() {
        EventGoodsUtil.joinDeleteAll();
        EventGoodsUtil.joinEventGoodsToEvent("劣质化妆品", "老乡见老乡，两眼泪汪汪");
        EventGoodsUtil.joinEventGoodsToEvent("水货手机", "美男传说");
        EventGoodsUtil.joinEventGoodsToEvent("地沟油", "捡到地沟油");
        EventGoodsUtil.joinEventGoodsToEvent("假冒茅台", "出门靠朋友");
        EventGoodsUtil.joinEventGoodsToEvent("转基因大豆", "转基因试验");
        EventGoodsUtil.joinEventGoodsToEvent("转基因大豆", "城管来了");
    }

    /**
     * 初始化所有物品信息
     */
    private static void initGoodsData() {
        GoodsUtil.deleteAll();
        GoodsUtil.create("假冒茅台", 1200, 100);
        GoodsUtil.create("黑心棉", 999, 90);
        GoodsUtil.create("北京户口", 120000, 25000);
        GoodsUtil.create("名校学历", 80000, 6000);
        GoodsUtil.create("走私海洛因", 10000, 4000);
        GoodsUtil.create("高考答案", 700000, 300000);
        GoodsUtil.create("走私汽车", 100000, 20000);
        GoodsUtil.create("水货手机", 15000, 1800);
        GoodsUtil.create("劣质化妆品", 1500, 200);
    }

    /**
     * 初始化所有新聞事件
     */
    private static void initNewsData() {

    }

}
