package me.sheepyang.onlylive.utils;

import freemarker.template.utility.StringUtil;
import me.sheepyang.onlylive.app.Constants;
import me.sheepyang.onlylive.entity.EventGoods;

import static me.sheepyang.onlylive.utils.RandomUtil.getRandomNum;

/**
 * Created by SheepYang on 2016/10/19 22:51.
 */

public class StrUtil extends StringUtil {

    public static String replaceChar(int i, String msg, EventGoods eventGoods) {
        int goodsNum = getRandomNum(eventGoods.getNumber());
//        DataUtil.addPlayGoods(eventGoods, goodsNum);// 添加物品至玩家物品列表(就是给玩家送礼物啦！)
        switch (i) {
            case 0:
                msg = msg.replace(Constants.REPLACE_GOODS_NAME_0, eventGoods.getName());
                msg = msg.replace(Constants.REPLACE_GOODS_NUMBER_0, goodsNum + "");
                msg = msg.replace(Constants.REPLACE_GOODS_UNIT_0, eventGoods.getUnit());
                break;
            case 1:
                msg = msg.replace(Constants.REPLACE_GOODS_NAME_1, eventGoods.getName());
                msg = msg.replace(Constants.REPLACE_GOODS_NUMBER_1, goodsNum + "");
                msg = msg.replace(Constants.REPLACE_GOODS_UNIT_1, eventGoods.getUnit());
                break;
            case 2:
                msg = msg.replace(Constants.REPLACE_GOODS_NAME_2, eventGoods.getName());
                msg = msg.replace(Constants.REPLACE_GOODS_NUMBER_2, goodsNum + "");
                msg = msg.replace(Constants.REPLACE_GOODS_UNIT_2, eventGoods.getUnit());
                break;
            case 3:
                msg = msg.replace(Constants.REPLACE_GOODS_NAME_3, eventGoods.getName());
                msg = msg.replace(Constants.REPLACE_GOODS_NUMBER_3, goodsNum + "");
                msg = msg.replace(Constants.REPLACE_GOODS_UNIT_3, eventGoods.getUnit());
                break;
            case 4:
                msg = msg.replace(Constants.REPLACE_GOODS_NAME_4, eventGoods.getName());
                msg = msg.replace(Constants.REPLACE_GOODS_NUMBER_4, goodsNum + "");
                msg = msg.replace(Constants.REPLACE_GOODS_UNIT_4, eventGoods.getUnit());
                break;
            default:
                break;
        }
        return msg;
    }
}
