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
    private int number;
    private int maxNumber;
    private int minNumber;
    public int getMinNumber() {
        return this.minNumber;
    }
    public void setMinNumber(int minNumber) {
        this.minNumber = minNumber;
    }
    public int getMaxNumber() {
        return this.maxNumber;
    }
    public void setMaxNumber(int maxNumber) {
        this.maxNumber = maxNumber;
    }
    public int getNumber() {
        return this.number;
    }
    public void setNumber(int number) {
        this.number = number;
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    @Generated(hash = 1397044366)
    public Number(Long id, int number, int maxNumber, int minNumber) {
        this.id = id;
        this.number = number;
        this.maxNumber = maxNumber;
        this.minNumber = minNumber;
    }
    @Generated(hash = 1191593292)
    public Number() {
    }
}
