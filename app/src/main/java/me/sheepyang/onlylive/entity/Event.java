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
    private String resultYes;
    private String resultNo;
    private String btnYes;
    private String btnNo;
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

    public String getBtnNo() {
        return this.btnNo;
    }

    public void setBtnNo(String btnNo) {
        this.btnNo = btnNo;
    }

    public String getBtnYes() {
        return this.btnYes;
    }

    public void setBtnYes(String btnYes) {
        this.btnYes = btnYes;
    }

    public String getResultNo() {
        return this.resultNo;
    }

    public void setResultNo(String resultNo) {
        this.resultNo = resultNo;
    }

    public String getResultYes() {
        return this.resultYes;
    }

    public void setResultYes(String resultYes) {
        this.resultYes = resultYes;
    }

    @Generated(hash = 849016588)
    public Event(Long id, String title, String message, String resultYes,
            String resultNo, String btnYes, String btnNo, boolean isGood) {
        this.id = id;
        this.title = title;
        this.message = message;
        this.resultYes = resultYes;
        this.resultNo = resultNo;
        this.btnYes = btnYes;
        this.btnNo = btnNo;
        this.isGood = isGood;
    }

    @Generated(hash = 344677835)
    public Event() {
    }
}
