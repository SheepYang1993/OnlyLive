package me.sheepyang.onlylive.utils.data;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

import me.sheepyang.onlylive.app.GameApplication;
import me.sheepyang.onlylive.entity.Goods;
import me.sheepyang.onlylive.entity.JoinPlayerGoodsToPlayer;
import me.sheepyang.onlylive.entity.PlayerGoods;
import me.sheepyang.onlylive.entity.ShopGoods;
import me.sheepyang.onlylive.entity.dao.JoinPlayerGoodsToPlayerDao;
import me.sheepyang.onlylive.entity.dao.PlayerGoodsDao;
import me.sheepyang.onlylive.utils.MathUtil;

/**
 * Created by SheepYang on 2016/10/22 17:53.
 */

public class PlayerGoodsUtil {
    private static PlayerGoodsDao mPlayerGoodsDao;
    private static JoinPlayerGoodsToPlayerDao mJoinPlayerGoodsToPlayerDao;

    static {
        mPlayerGoodsDao = GameApplication.getInstances().getDaoSession().getPlayerGoodsDao();
        mJoinPlayerGoodsToPlayerDao = GameApplication.getInstances().getDaoSession().getJoinPlayerGoodsToPlayerDao();
    }

    public static List<PlayerGoods> loadAll() {
        return mPlayerGoodsDao.loadAll();
    }

    public static void deleteAll() {
        mPlayerGoodsDao.deleteAll();
    }

    public static void addPlayGoods(ShopGoods shopGoods, String shopGoodsNum) {
        QueryBuilder<PlayerGoods> qb = mPlayerGoodsDao.queryBuilder();
        qb.where(PlayerGoodsDao.Properties.Name.eq(shopGoods.getName()));
        List<PlayerGoods> list = qb.list();
        if (list != null && list.size() > 0) {
            PlayerGoods playerGoods = list.get(0);
            String paid = playerGoods.getPaid();
            String goodsNumber = playerGoods.getNumber();
            // 计算获得物品后，物品的平均价格
            String totalNum = MathUtil.add(goodsNumber, shopGoodsNum);
            String totalPrice;
            if (MathUtil.eq(paid, "0")) {
                totalPrice = MathUtil.multiply(shopGoods.getPrice(), shopGoodsNum);
            } else {
                totalPrice = MathUtil.add(MathUtil.multiply(paid, goodsNumber), MathUtil.multiply(shopGoods.getPrice(), shopGoodsNum));
            }
            playerGoods.setPaid(MathUtil.divide(totalPrice, totalNum));// 设置进价, 公式 = 总价 / 总数量
            playerGoods.setPrice(shopGoods.getPrice());// 设置市价

            // 设置物品数量
            String newGoodsNumber = MathUtil.add(goodsNumber, shopGoodsNum);
            playerGoods.setNumber(newGoodsNumber);
            mPlayerGoodsDao.insertOrReplace(playerGoods);

            JoinPlayerGoodsToPlayer joinGoods = new JoinPlayerGoodsToPlayer();
            joinGoods.setPlayerGoodsId(playerGoods.getId());
            joinGoods.setPlayerId(PlayerUtil.getPlayer().getId());
            mJoinPlayerGoodsToPlayerDao.insertOrReplace(joinGoods);
        } else {// 玩家物品列表中没有该物品，则新建物品
            PlayerGoods playerGoods = new PlayerGoods();
            playerGoods.setPaid(shopGoods.getPrice());// 设置进价
            playerGoods.setPrice(shopGoods.getPrice());// 设置市价
            playerGoods.setNumber(shopGoodsNum);
            playerGoods.setName(shopGoods.getName());
            mPlayerGoodsDao.insertOrReplace(playerGoods);

            JoinPlayerGoodsToPlayer joinGoods = new JoinPlayerGoodsToPlayer();
            joinGoods.setPlayerGoodsId(playerGoods.getId());
            joinGoods.setPlayerId(PlayerUtil.getPlayer().getId());
            mJoinPlayerGoodsToPlayerDao.insertOrReplace(joinGoods);
        }
    }

    public static void addPlayGoods(Goods goods, String goodsNum) {
//        QueryBuilder<PlayerGoods> qb = mPlayerGoodsDao.queryBuilder();
//        qb.where(PlayerGoodsDao.Properties.Name.eq(eventGoods.getName()));
//        List<PlayerGoods> list = qb.list();
//        if (list != null && list.size() > 0) {
//            PlayerGoods playerGoods = list.get(0);
//            Number newPrice = playerGoods.getPrice();
//            Number newGoodsNumber = playerGoods.getNumber();
//            // 计算获得物品后，物品的平均价格
//            // 公式 = 总价 / (原数量 + 新增数量)
//            float price = (float) (newPrice.getNumber() * newGoodsNumber.getNumber()) / (float) (newGoodsNumber.getNumber() + goodsNum);
//            newPrice.setNumber(Math.round(price));
//            playerGoods.setPrice(newPrice);
//            // 设置物品数量
//            newGoodsNumber.setNumber(newGoodsNumber.getNumber() + goodsNum);
//            playerGoods.setNumber(newGoodsNumber);
//
//            JoinPlayerGoodsToPlayer joinGoods = new JoinPlayerGoodsToPlayer();
//            joinGoods.setPlayerGoodsId(playerGoods.getId());
//            joinGoods.setPlayerId(PlayerUtil.getPlayer().getId());
//            mJoinPlayerGoodsToPlayerDao.insertOrReplace(joinGoods);
//            mPlayerGoodsDao.insertOrReplace(playerGoods);
//        } else {// 玩家物品列表中没有该物品，则新建物品
//            PlayerGoods playerGoods = new PlayerGoods();
//            Number price = eventGoods.getPrice();
//            Number newGoodsNum = eventGoods.getNumber();
//            price.setNumber(0);
//            newGoodsNum.setNumber(0);
//            playerGoods.setPrice(price);
//            playerGoods.setNumber(newGoodsNum);
//            playerGoods.setName(eventGoods.getName());
//            playerGoods.setUnit(eventGoods.getUnit());
//
//            JoinPlayerGoodsToPlayer joinGoods = new JoinPlayerGoodsToPlayer();
//            joinGoods.setPlayerGoodsId(playerGoods.getId());
//            joinGoods.setPlayerId(PlayerUtil.getPlayer().getId());
//            mJoinPlayerGoodsToPlayerDao.insertOrReplace(joinGoods);
//            mPlayerGoodsDao.insertOrReplace(playerGoods);
//        }
    }

    /**
     * 清除所有物品的市价
     */
    public static void clearPrice() {
        List<PlayerGoods> list = mPlayerGoodsDao.loadAll();
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setPrice("-1");
        }
        mPlayerGoodsDao.insertOrReplaceInTx(list);
    }


    /**
     * 设置物品的市价
     */
    public static void setPrice(List<ShopGoods> shopGoodsList) {
        for (int i = 0; i < shopGoodsList.size(); i++) {
            ShopGoods shopGoods = shopGoodsList.get(i);
            QueryBuilder<PlayerGoods> qb = mPlayerGoodsDao.queryBuilder();
            qb.where(PlayerGoodsDao.Properties.Name.eq(shopGoods.getName()));
            List<PlayerGoods> list = qb.list();
            if (list != null && list.size() > 0) {
                list.get(0).setPrice(shopGoods.getPrice());
            }
        }
    }

    public static void delete(PlayerGoods playerGoods) {
        mPlayerGoodsDao.delete(playerGoods);
    }

    public static void insertOrReplace(PlayerGoods playerGoods) {
        mPlayerGoodsDao.insertOrReplace(playerGoods);
    }
}
