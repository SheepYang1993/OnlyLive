package me.sheepyang.onlylive.greenentity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by SheepYang on 2016/10/27 22:51.
 */
@Entity
public class ShopGoods {
    @Id(autoincrement = true)
    private Long id;
    private String name;
    private String price;
    public String getPrice() {
        return this.price;
    }
    public void setPrice(String price) {
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
    @Generated(hash = 529930273)
    public ShopGoods(Long id, String name, String price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }
    @Generated(hash = 606621011)
    public ShopGoods() {
    }
}
