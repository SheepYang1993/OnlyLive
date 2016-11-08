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
    private long number;
    private double maxPercent;
    private double minPercent;
    private long maxNumber;
    private long minNumber;

    public long getMinNumber() {
        return this.minNumber;
    }

    public void setMinNumber(long minNumber) {
        this.minNumber = minNumber;
    }

    public long getMaxNumber() {
        return this.maxNumber;
    }

    public void setMaxNumber(long maxNumber) {
        this.maxNumber = maxNumber;
    }

    public long getNumber() {
        return this.number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Generated(hash = 389939164)
    public Number(Long id, long number, long maxNumber, long minNumber) {
        this.id = id;
        this.number = number;
        this.maxNumber = maxNumber;
        this.minNumber = minNumber;
    }

    @Generated(hash = 1191593292)
    public Number() {
    }
}
