package me.sheepyang.onlylive.utils.data;

import java.util.ArrayList;
import java.util.List;

import me.sheepyang.onlylive.app.GameApplication;
import me.sheepyang.onlylive.entity.Goods;
import me.sheepyang.onlylive.entity.ShopGoods;
import me.sheepyang.onlylive.entity.dao.JoinPlayerGoodsToPlayerDao;
import me.sheepyang.onlylive.entity.dao.PlayerGoodsDao;
import me.sheepyang.onlylive.entity.dao.ShopGoodsDao;
import me.sheepyang.onlylive.utils.RandomUtil;

import static me.sheepyang.onlylive.utils.RandomUtil.getRandomNum;

/**
 * Created by SheepYang on 2016/10/28 20:58.
 */

public class ShopGoodsUtil {
    private static ShopGoodsDao mShopGoodsDao;

    static {
        mShopGoodsDao = GameApplication.getInstances().getDaoSession().getShopGoodsDao();
    }

    public static long create(Goods goods) {
        ShopGoods shopGoods = new ShopGoods();
        shopGoods.setName(goods.getName());
        int price = RandomUtil.getRandomNum(goods.getPrice());
        shopGoods.setPrice(price);
        return mShopGoodsDao.insertOrReplace(shopGoods);
    }

    public static List<ShopGoods> getShopGoodsList(List<Goods> goodsList) {
        mShopGoodsDao.deleteAll();
        for (int i = 0; i < goodsList.size(); i++) {
            create(goodsList.get(i));
        }
        return mShopGoodsDao.loadAll();
    }
}
