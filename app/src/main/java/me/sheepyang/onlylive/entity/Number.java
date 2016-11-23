package me.sheepyang.onlylive.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by SheepYang on 2016/10/16 18:05.
 */
@Entity
public class Number {
    @Id(autoincrement = true)
    private Long id;
    private String number;
    private String maxPercent;
    private String minPercent;
    public String getMinPercent() {
        return this.minPercent;
    }
    public void setMinPercent(String minPercent) {
        this.minPercent = minPercent;
    }
    public String getMaxPercent() {
        return this.maxPercent;
    }
    public void setMaxPercent(String maxPercent) {
        this.maxPercent = maxPercent;
    }
    public String getNumber() {
        return this.number;
    }
    public void setNumber(String number) {
        this.number = number;
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    @Generated(hash = 921527406)
    public Number(Long id, String number, String maxPercent, String minPercent) {
        this.id = id;
        this.number = number;
        this.maxPercent = maxPercent;
        this.minPercent = minPercent;
    }
    @Generated(hash = 1191593292)
    public Number() {
    }
}
