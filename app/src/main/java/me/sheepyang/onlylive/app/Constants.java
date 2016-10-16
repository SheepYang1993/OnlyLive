package me.sheepyang.onlylive.app;

/**
 * Created by SheepYang on 2016/10/13 00:47.
 */

public class Constants {
    // 重新开始一盘游戏的数值
    public static int INIT_GAME_CASH = 999;// 现金
    public static int INIT_GAME_DEBT = 9000;// 负债
    public static int INIT_GAME_DEPOSIT = 50;// 存款
    public static int INIT_GAME_HEALTH = 80;// 健康
    public static int INIT_GAME_HOUSE = 0;// 房子数量
    public static int INIT_GAME_WEEK = 0;// 周数

    public static int CONFIG_TOTAL_HOUSE = 80;// 总房子数量
    public static int CONFIG_TOTAL_WEEK = 52;// 游戏总周数

    // 突发事件中，需要被替换掉的字符串
    public static final String GOODS_NUMBER = "%number%";// 物品数量
    public static final String GOODS_UNIT = "%unit%";// 物品单位
    public static final String GOODS_NAME = "%name%";// 物品名称
}
