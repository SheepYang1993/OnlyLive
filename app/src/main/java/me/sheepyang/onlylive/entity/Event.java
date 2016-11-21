package me.sheepyang.onlylive.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * 突发事件
 * Created by SheepYang on 2016/10/14 22:51.
 */
@Entity
public class Event {
    @Id(autoincrement = true)
    private Long id;
    private String title;// 标题
    private String message;// 信息
    private boolean isGood;// 是否是好的事件
    public boolean getIsGood() {
        return this.isGood;
    }
    public void setIsGood(boolean isGood) {
        this.isGood = isGood;
    }
    public String getMessage() {
        return this.message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    @Generated(hash = 154815724)
    public Event(Long id, String title, String message, boolean isGood) {
        this.id = id;
        this.title = title;
        this.message = message;
        this.isGood = isGood;
    }
    @Generated(hash = 344677835)
    public Event() {
    }
}
