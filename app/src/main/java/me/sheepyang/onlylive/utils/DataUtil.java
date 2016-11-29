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
                .setName("桔红糕")
                .setUnit("块")
                .setPrice(NumberUtil.create("20", "4", "0.2"))// 0.8
                .create();
        new GoodsUtil.Builder()
                .setName("秉正石花膏")
                .setUnit("碗")
                .setPrice(NumberUtil.create("50", "2", "0.4"))// 0.8
                .create();
        new GoodsUtil.Builder()
                .setName("面线糊")
                .setUnit("碗")
                .setPrice(NumberUtil.create("100", "1.5", "0.5"))// 0.75
                .create();
        new GoodsUtil.Builder()
                .setName("泉州牛排")
                .setUnit("块")
                .setPrice(NumberUtil.create("250", "1.6", "0.72"))// 1.152
                .create();
        new GoodsUtil.Builder()
                .setName("匹克校服")
                .setUnit("件")
                .setPrice(NumberUtil.create("500", "1.2", "0.4"))// 0.48
                .create();
        new GoodsUtil.Builder()
                .setName("阿迪王跑鞋")
                .setUnit("双")
                .setPrice(NumberUtil.create("700", "1.43", "0.57"))// 0.815
                .create();
        new GoodsUtil.Builder()
                .setName("鸡圈")
                .setUnit("条")
                .setPrice(NumberUtil.create("1200", "3", "0.5"))// 1.5
                .create();


        new GoodsUtil.Builder()
                .setName("蚵仔煎")
                .setUnit("份")
                .setPrice(NumberUtil.create("1200", "3.5", "0.2"))
                .create();
        new GoodsUtil.Builder()
                .setName("土笋冻")
                .setUnit("块")
                .setPrice(NumberUtil.create("1200", "3.5", "0.2"))
                .create();
        new GoodsUtil.Builder()
                .setName("高考答案")
                .setUnit("块")
                .setPrice(NumberUtil.create("1200", "3.5", "0.2"))
                .create();
        new GoodsUtil.Builder()
                .setName("五香卷")
                .setUnit("条")
                .setPrice(NumberUtil.create("1200", "3.5", "0.2"))
                .create();
        new GoodsUtil.Builder()
                .setName("沙茶面")
                .setUnit("碗")
                .setPrice(NumberUtil.create("1200", "3.5", "0.2"))
                .create();
        new GoodsUtil.Builder()
                .setName("四果汤")
                .setUnit("碗")
                .setPrice(NumberUtil.create("1200", "3.5", "0.2"))
                .create();
        new GoodsUtil.Builder()
                .setName("黄冈数学")
                .setUnit("本")
                .setPrice(NumberUtil.create("1200", "3.5", "0.2"))
                .create();
        new GoodsUtil.Builder()
                .setName("福伯烧仙草")
                .setUnit("杯")
                .setPrice(NumberUtil.create("1200", "3.5", "0.2"))
                .create();
        new GoodsUtil.Builder()
                .setName("润饼菜")
                .setUnit("份")
                .setPrice(NumberUtil.create("1200", "3.5", "0.2"))
                .create();
        new GoodsUtil.Builder()
                .setName("烧肉粽")
                .setUnit("份")
                .setPrice(NumberUtil.create("1200", "3.5", "0.2"))
                .create();
        new GoodsUtil.Builder()
                .setName("洪濑鸡爪")
                .setUnit("斤")
                .setPrice(NumberUtil.create("1200", "3.5", "0.2"))
                .create();
        new GoodsUtil.Builder()
                .setName("姜母鸭")
                .setUnit("只")
                .setPrice(NumberUtil.create("1200", "3.5", "0.2"))
                .create();
        new GoodsUtil.Builder()
                .setName("崇武鱼卷")
                .setUnit("条")
                .setPrice(NumberUtil.create("1200", "3.5", "0.2"))
                .create();
        new GoodsUtil.Builder()
                .setName("兰州拉面")
                .setUnit("碗")
                .setPrice(NumberUtil.create("1200", "3.5", "0.2"))
                .create();
        new GoodsUtil.Builder()
                .setName("牛肉羹")
                .setUnit("碗")
                .setPrice(NumberUtil.create("1200", "3.5", "0.2"))
                .create();
        new GoodsUtil.Builder()
                .setName("永春白鸭")
                .setUnit("只")
                .setPrice(NumberUtil.create("1200", "3.5", "0.2"))
                .create();
        new GoodsUtil.Builder()
                .setName("炸醋肉")
                .setUnit("份")
                .setPrice(NumberUtil.create("1200", "3.5", "0.2"))
                .create();
        new GoodsUtil.Builder()
                .setName("绿豆饼")
                .setUnit("包")
                .setPrice(NumberUtil.create("1200", "3.5", "0.2"))
                .create();
        new GoodsUtil.Builder()
                .setName("安溪铁观音")
                .setUnit("罐")
                .setPrice(NumberUtil.create("1200", "3.5", "0.2"))
                .create();
        new GoodsUtil.Builder()
                .setName("浪琴手表")
                .setUnit("只")
                .setPrice(NumberUtil.create("1200", "3.5", "0.2"))
                .create();
        new GoodsUtil.Builder()
                .setName("小沙弥摆件")
                .setUnit("尊")
                .setPrice(NumberUtil.create("1200", "3.5", "0.2"))
                .create();
        new GoodsUtil.Builder()
                .setName("李志演唱会门票")
                .setUnit("张")
                .setPrice(NumberUtil.create("1200", "3.5", "0.2"))
                .create();
        new GoodsUtil.Builder()
                .setName("迷笛音乐节门票")
                .setUnit("张")
                .setPrice(NumberUtil.create("1200", "3.5", "0.2"))
                .create();
        new GoodsUtil.Builder()
                .setName("民国老花镜")
                .setUnit("副")
                .setPrice(NumberUtil.create("1200", "3.5", "0.2"))
                .create();
        new GoodsUtil.Builder()
                .setName("小叶紫檀手串")
                .setUnit("串")
                .setPrice(NumberUtil.create("1200", "3.5", "0.2"))
                .create();
        new GoodsUtil.Builder()
                .setName("深沪鱼丸")
                .setUnit("包")
                .setPrice(NumberUtil.create("1200", "3.5", "0.2"))
                .create();
        new GoodsUtil.Builder()
                .setName("死飞自行车")
                .setUnit("辆")
                .setPrice(NumberUtil.create("1200", "3.5", "0.2"))
                .create();
        new GoodsUtil.Builder()
                .setName("凤凰牌单车")
                .setUnit("辆")
                .setPrice(NumberUtil.create("1200", "3.5", "0.2"))
                .create();
        new GoodsUtil.Builder()
                .setName("泰国洗面奶")
                .setUnit("支")
                .setPrice(NumberUtil.create("1200", "3.5", "0.2"))
                .create();
        new GoodsUtil.Builder()
                .setName("宫崎骏手稿")
                .setUnit("份")
                .setPrice(NumberUtil.create("1200", "3.5", "0.2"))
                .create();
        new GoodsUtil.Builder()
                .setName("盗墓笔记")
                .setUnit("本")
                .setPrice(NumberUtil.create("1200", "3.5", "0.2"))
                .create();
        new GoodsUtil.Builder()
                .setName("全单吉他")
                .setUnit("把")
                .setPrice(NumberUtil.create("1200", "3.5", "0.2"))
                .create();
        new GoodsUtil.Builder()
                .setName("全自动麻将桌")
                .setUnit("张")
                .setPrice(NumberUtil.create("1200", "3.5", "0.2"))
                .create();
        new GoodsUtil.Builder()
                .setName("德国牧羊犬")
                .setUnit("只")
                .setPrice(NumberUtil.create("1200", "3.5", "0.2"))
                .create();
        new GoodsUtil.Builder()
                .setName("哈士奇")
                .setUnit("只")
                .setPrice(NumberUtil.create("1200", "3.5", "0.2"))
                .create();
        new GoodsUtil.Builder()
                .setName("阿拉斯加雪橇犬")
                .setUnit("只")
                .setPrice(NumberUtil.create("1200", "3.5", "0.2"))
                .create();
        new GoodsUtil.Builder()
                .setName("中华田园犬")
                .setUnit("只")
                .setPrice(NumberUtil.create("1200", "3.5", "0.2"))
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
                .setTitle("类型七：所有类型都有的事件")
                .setMessage("内容，所有类型都有的事件")
                .create();
//        new EventUtil.Builder()
//                .setIsGood(true)
//                .setTitle("类型七：所有类型都有的事件")
//                .setMessage("内容，所有类型都有的事件")
//                .setCash(NumberUtil.create("-3000", "2", "0.5"))
//                .setDebt(NumberUtil.create("3500", "2", "0.5"))
//                .setDeposit(NumberUtil.create("-4000", "2", "0.5"))
//                .setHealth(NumberUtil.create("-40", "1", "0.5"))
//                .addGoods(GoodsUtil.getGoods("北京户口"))
//                .addGoods(GoodsUtil.getGoods("走私海洛因"))
//                .addGoods(GoodsUtil.getGoods("高考答案"))
//                .addGoods(GoodsUtil.getGoods("劣质化妆品"))
//                .addGoods(GoodsUtil.getGoods("假冒茅台"))
//                .create();
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
