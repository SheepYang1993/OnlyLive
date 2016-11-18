package me.sheepyang.onlylive.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * 玩家拥有的物品列表
 * Created by SheepYang on 2016/10/13 00:06.
 */
@Entity
public class JoinPlayerGoodsToPlayer {
    @Id(autoincrement = true)
    private Long id;
    private Long playerId;
    private Long playerGoodsId;
    public Long getPlayerGoodsId() {
        return this.playerGoodsId;
    }
    public void setPlayerGoodsId(Long playerGoodsId) {
        this.playerGoodsId = playerGoodsId;
    }
    public Long getPlayerId() {
        return this.playerId;
    }
    public void setPlayerId(Long playerId) {
        this.playerId = playerId;
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    @Generated(hash = 1686104106)
    public JoinPlayerGoodsToPlayer(Long id, Long playerId, Long playerGoodsId) {
        this.id = id;
        this.playerId = playerId;
        this.playerGoodsId = playerGoodsId;
    }
    @Generated(hash = 1104661787)
    public JoinPlayerGoodsToPlayer() {
    }
}
