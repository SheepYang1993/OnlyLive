package me.sheepyang.onlylive.utils.data;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

import me.sheepyang.onlylive.app.GameApplication;
import me.sheepyang.onlylive.entity.EventGoods;
import me.sheepyang.onlylive.entity.JoinPlayerGoodsToPlayer;
import me.sheepyang.onlylive.entity.Number;
import me.sheepyang.onlylive.entity.PlayerGoods;
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

    public static void addPlayGoods(EventGoods eventGoods, int goodsNum) {
        QueryBuilder<PlayerGoods> qb = mPlayerGoodsDao.queryBuilder();
        qb.where(PlayerGoodsDao.Properties.Name.eq(eventGoods.getName()));
        List<PlayerGoods> list = qb.list();
        if (list != null && list.size() > 0) {
            PlayerGoods playerGoods = list.get(0);
            Number newPrice = playerGoods.getPrice();
            Number newGoodsNumber = playerGoods.getNumber();
            // 计算获得物品后，物品的平均价格
            // 公式 = 总价 / (原数量 + 新增数量)
            float price = (float) (newPrice.getNumber() * newGoodsNumber.getNumber()) / (float) (newGoodsNumber.getNumber() + goodsNum);
            newPrice.setNumber(Math.round(price));
            playerGoods.setPrice(newPrice);
            // 设置物品数量
            newGoodsNumber.setNumber(newGoodsNumber.getNumber() + goodsNum);
            playerGoods.setNumber(newGoodsNumber);

            JoinPlayerGoodsToPlayer joinGoods = new JoinPlayerGoodsToPlayer();
            joinGoods.setPlayerGoodsId(playerGoods.getId());
            joinGoods.setPlayerId(PlayerUtil.getPlayer().getId());
            mJoinPlayerGoodsToPlayerDao.insertOrReplace(joinGoods);
            mPlayerGoodsDao.insertOrReplace(playerGoods);
        } else {// 玩家物品列表中没有该物品，则新建物品
            PlayerGoods playerGoods = new PlayerGoods();
            Number price = eventGoods.getPrice();
            Number newGoodsNum = eventGoods.getNumber();
            price.setNumber(0);
            newGoodsNum.setNumber(0);
            playerGoods.setPrice(price);
            playerGoods.setNumber(newGoodsNum);
            playerGoods.setName(eventGoods.getName());
            playerGoods.setUnit(eventGoods.getUnit());

            JoinPlayerGoodsToPlayer joinGoods = new JoinPlayerGoodsToPlayer();
            joinGoods.setPlayerGoodsId(playerGoods.getId());
            joinGoods.setPlayerId(PlayerUtil.getPlayer().getId());
            mJoinPlayerGoodsToPlayerDao.insertOrReplace(joinGoods);
            mPlayerGoodsDao.insertOrReplace(playerGoods);
        }
    }
}
