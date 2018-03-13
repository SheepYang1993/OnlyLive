package me.sheepyang.onlylive.greenentity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * 游戏记录
 * Created by SheepYang on 2016/11/28.
 */
@Entity
public class Rank {
    @Id(autoincrement = true)
    private Long id;
    private String date;// 日期
    private String score;// 分数
    private String name;// 姓名
    private String desc;// 称号

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScore() {
        return this.score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDesc() {
        return this.desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Generated(hash = 1917003264)
    public Rank(Long id, String date, String score, String name, String desc) {
        this.id = id;
        this.date = date;
        this.score = score;
        this.name = name;
        this.desc = desc;
    }

    @Generated(hash = 531117843)
    public Rank() {
    }
}
