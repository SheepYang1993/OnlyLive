package me.sheepyang.onlylive.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.ToOne;
import org.greenrobot.greendao.DaoException;

import me.sheepyang.onlylive.entity.dao.DaoSession;
import me.sheepyang.onlylive.entity.dao.PlayerGoodsDao;
import me.sheepyang.onlylive.entity.dao.NumberDao;

import org.greenrobot.greendao.annotation.NotNull;

/**
 * Created by SheepYang on 2016/10/16 22:32.
 */
@Entity
public class PlayerGoods {
    @Id(autoincrement = true)
    private Long id;
    private String name;
    private long price;// 市场价格
    private long number;// 数量
    private long paid;// 进价
    public long getPaid() {
        return this.paid;
    }
    public void setPaid(long paid) {
        this.paid = paid;
    }
    public long getNumber() {
        return this.number;
    }
    public void setNumber(long number) {
        this.number = number;
    }
    public long getPrice() {
        return this.price;
    }
    public void setPrice(long price) {
        this.price = price;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    @Generated(hash = 1400910085)
    public PlayerGoods(Long id, String name, long price, long number, long paid) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.number = number;
        this.paid = paid;
    }
    @Generated(hash = 1542970823)
    public PlayerGoods() {
    }
}
