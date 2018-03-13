package me.sheepyang.onlylive.utils.data;

import java.util.List;

import me.sheepyang.onlylive.app.GameApplication;
import me.sheepyang.onlylive.greenentity.Goods;
import me.sheepyang.onlylive.greenentity.ShopGoods;
import me.sheepyang.onlylive.greenentity.dao.ShopGoodsDao;
import me.sheepyang.onlylive.utils.RandomUtil;

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
        String price = RandomUtil.getRandomNum(goods.getPrice());
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
