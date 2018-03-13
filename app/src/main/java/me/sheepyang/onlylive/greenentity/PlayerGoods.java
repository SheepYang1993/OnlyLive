package me.sheepyang.onlylive.greenentity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by SheepYang on 2016/10/16 22:32.
 */
@Entity
public class PlayerGoods {
    @Id(autoincrement = true)
    private Long id;
    private String name;
    private String price;// 市场价格
    private String number;// 数量
    private String paid;// 进价
    public String getPaid() {
        return this.paid;
    }
    public void setPaid(String paid) {
        this.paid = paid;
    }
    public String getNumber() {
        return this.number;
    }
    public void setNumber(String number) {
        this.number = number;
    }
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
    @Generated(hash = 1170174290)
    public PlayerGoods(Long id, String name, String price, String number,
            String paid) {
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
