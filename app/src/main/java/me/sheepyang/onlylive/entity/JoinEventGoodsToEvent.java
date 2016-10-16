package me.sheepyang.onlylive.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by SheepYang on 2016/10/16 22:07.
 */
@Entity
public class JoinEventGoodsToEvent {
    @Id(autoincrement = true)
    private Long id;
    private Long eventId;
    private Long eventGoodsId;
    public Long getEventGoodsId() {
        return this.eventGoodsId;
    }
    public void setEventGoodsId(Long eventGoodsId) {
        this.eventGoodsId = eventGoodsId;
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
    @Generated(hash = 139880334)
    public JoinEventGoodsToEvent(Long id, Long eventId, Long eventGoodsId) {
        this.id = id;
        this.eventId = eventId;
        this.eventGoodsId = eventGoodsId;
    }
    @Generated(hash = 348812276)
    public JoinEventGoodsToEvent() {
    }
}
