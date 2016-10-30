package me.sheepyang.onlylive.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToOne;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;

import me.sheepyang.onlylive.entity.dao.DaoSession;
import me.sheepyang.onlylive.entity.dao.ShopGoodsDao;
import me.sheepyang.onlylive.entity.dao.NumberDao;

/**
 * Created by SheepYang on 2016/10/27 22:51.
 */
@Entity
public class ShopGoods {
    @Id(autoincrement = true)
    private Long id;
    private String name;
    private long price;
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
    @Generated(hash = 376052819)
    public ShopGoods(Long id, String name, long price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }
    @Generated(hash = 606621011)
    public ShopGoods() {
    }
}
