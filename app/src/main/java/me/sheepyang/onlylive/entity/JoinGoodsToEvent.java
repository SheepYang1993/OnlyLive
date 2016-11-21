package me.sheepyang.onlylive.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by SheepYang on 2016/10/16 22:07.
 */
@Entity
public class JoinGoodsToEvent {
    @Id(autoincrement = true)
    private Long id;
    private Long eventId;
    private Long goodsId;
    public Long getGoodsId() {
        return this.goodsId;
    }
    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }
    public Long getEventId() {
        return this.eventId;
    }
    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    @Generated(hash = 563448743)
    public JoinGoodsToEvent(Long id, Long eventId, Long goodsId) {
        this.id = id;
        this.eventId = eventId;
        this.goodsId = goodsId;
    }
    @Generated(hash = 1596794281)
    public JoinGoodsToEvent() {
    }
}
