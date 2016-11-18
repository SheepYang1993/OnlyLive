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
    private double maxPercent;
    private double minPercent;
    private String maxNumber;
    private String minNumber;
    public String getMinNumber() {
        return this.minNumber;
    }
    public void setMinNumber(String minNumber) {
        this.minNumber = minNumber;
    }
    public String getMaxNumber() {
        return this.maxNumber;
    }
    public void setMaxNumber(String maxNumber) {
        this.maxNumber = maxNumber;
    }
    public double getMinPercent() {
        return this.minPercent;
    }
    public void setMinPercent(double minPercent) {
        this.minPercent = minPercent;
    }
    public double getMaxPercent() {
        return this.maxPercent;
    }
    public void setMaxPercent(double maxPercent) {
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
    @Generated(hash = 765808613)
    public Number(Long id, String number, double maxPercent, double minPercent,
            String maxNumber, String minNumber) {
        this.id = id;
        this.number = number;
        this.maxPercent = maxPercent;
        this.minPercent = minPercent;
        this.maxNumber = maxNumber;
        this.minNumber = minNumber;
    }
    @Generated(hash = 1191593292)
    public Number() {
    }
}
