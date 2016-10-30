package me.sheepyang.onlylive.utils.data;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

import me.sheepyang.onlylive.app.GameApplication;
import me.sheepyang.onlylive.entity.EventGoods;
import me.sheepyang.onlylive.entity.JoinPlayerGoodsToPlayer;
import me.sheepyang.onlylive.entity.Number;
import me.sheepyang.onlylive.entity.PlayerGoods;
import me.sheepyang.onlylive.entity.ShopGoods;
import me.sheepyang.onlylive.entity.dao.JoinPlayerGoodsToPlayerDao;
import me.sheepyang.onlylive.entity.dao.PlayerGoodsDao;

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

    public static void addPlayGoods(ShopGoods shopGoods, int shopGoodsNum) {
        QueryBuilder<PlayerGoods> qb = mPlayerGoodsDao.queryBuilder();
        qb.where(PlayerGoodsDao.Properties.Name.eq(shopGoods.getName()));
        List<PlayerGoods> list = qb.list();
        if (list != null && list.size() > 0) {
            PlayerGoods playerGoods = list.get(0);
            long paid = playerGoods.getPaid();
            long goodsNumber = playerGoods.getNumber();
            // 计算获得物品后，物品的平均价格
            float newPaid;
            if (paid == 0) {
                // 公式 = 总价 / (原数量 + 新增数量)
                newPaid = (float) (shopGoods.getPrice() * goodsNumber) / (float) (goodsNumber + shopGoodsNum);
            } else {
                // 公式 = 总价 / (原数量 + 新增数量)
                newPaid = (float) (paid * goodsNumber) / (float) (goodsNumber + shopGoodsNum);
            }
            paid = Math.round(newPaid);
            playerGoods.setPaid(paid);// 设置进价
            playerGoods.setPrice(shopGoods.getPrice());// 设置市价

            // 设置物品数量
            long newGoodsNumber = goodsNumber + shopGoodsNum;
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

    public static void addPlayGoods(EventGoods eventGoods, int goodsNum) {
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
}
