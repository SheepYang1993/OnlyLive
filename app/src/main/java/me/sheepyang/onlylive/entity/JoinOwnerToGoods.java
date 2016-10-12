package me.sheepyang.onlylive.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;

import static android.R.attr.id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by SheepYang on 2016/10/13 00:06.
 */
@Entity
public class JoinOwnerToGoods {
    @Id(autoincrement = true)
    private long id;
    private long ownerId;
    private long goodsId;
    public long getGoodsId() {
        return this.goodsId;
    }
    public void setGoodsId(long goodsId) {
        this.goodsId = goodsId;
    }
    public long getOwnerId() {
        return this.ownerId;
    }
    public void setOwnerId(long ownerId) {
        this.ownerId = ownerId;
    }
    public long getId() {
        return this.id;
    }
    public void setId(long id) {
        this.id = id;
    }
    @Generated(hash = 847576779)
    public JoinOwnerToGoods(long id, long ownerId, long goodsId) {
        this.id = id;
        this.ownerId = ownerId;
        this.goodsId = goodsId;
    }
    @Generated(hash = 1883786033)
    public JoinOwnerToGoods() {
    }
}
