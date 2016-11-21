package me.sheepyang.onlylive.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.JoinEntity;
import org.greenrobot.greendao.annotation.ToMany;

import java.util.List;

import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.ToOne;

import me.sheepyang.onlylive.entity.dao.DaoSession;
import me.sheepyang.onlylive.entity.dao.EventDao;
import me.sheepyang.onlylive.entity.dao.NumberDao;
import me.sheepyang.onlylive.entity.dao.GoodsDao;

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
    private String resultGood;
    private String resultBad;
    private String btnOk;
    private String btnCancel;
    private boolean isGood;// 是否是好的事件
    private boolean isNeedSelect;// 是否需要选择
    @ToOne(joinProperty = "eventGoodsNumId")// 丢失或获得物品的数量
    private Number eventGoodsNum;
    private Long eventGoodsNumId;
    @ToOne(joinProperty = "healthId")
    private Number health;
    private Long healthId;
    @ToOne(joinProperty = "moneyId")
    private Number money;
    private Long moneyId;
    @ToMany
    @JoinEntity(
            entity = JoinGoodsToEvent.class,
            sourceProperty = "eventId",
            targetProperty = "goodsId"
    )
    private List<Goods> goodsList;
    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }
    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }
    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }
    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 1218049045)
    public synchronized void resetGoodsList() {
        goodsList = null;
    }
    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 1421931548)
    public List<Goods> getGoodsList() {
        if (goodsList == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            GoodsDao targetDao = daoSession.getGoodsDao();
            List<Goods> goodsListNew = targetDao._queryEvent_GoodsList(id);
            synchronized (this) {
                if(goodsList == null) {
                    goodsList = goodsListNew;
                }
            }
        }
        return goodsList;
    }
    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 577161037)
    public void setMoney(Number money) {
        synchronized (this) {
            this.money = money;
            moneyId = money == null ? null : money.getId();
            money__resolvedKey = moneyId;
        }
    }
    /** To-one relationship, resolved on first access. */
    @Generated(hash = 1495351971)
    public Number getMoney() {
        Long __key = this.moneyId;
        if (money__resolvedKey == null || !money__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            NumberDao targetDao = daoSession.getNumberDao();
            Number moneyNew = targetDao.load(__key);
            synchronized (this) {
                money = moneyNew;
                money__resolvedKey = __key;
            }
        }
        return money;
    }
    @Generated(hash = 599291695)
    private transient Long money__resolvedKey;
    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 279909944)
    public void setHealth(Number health) {
        synchronized (this) {
            this.health = health;
            healthId = health == null ? null : health.getId();
            health__resolvedKey = healthId;
        }
    }
    /** To-one relationship, resolved on first access. */
    @Generated(hash = 1041294309)
    public Number getHealth() {
        Long __key = this.healthId;
        if (health__resolvedKey == null || !health__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            NumberDao targetDao = daoSession.getNumberDao();
            Number healthNew = targetDao.load(__key);
            synchronized (this) {
                health = healthNew;
                health__resolvedKey = __key;
            }
        }
        return health;
    }
    @Generated(hash = 218444979)
    private transient Long health__resolvedKey;
    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1847801568)
    public void setEventGoodsNum(Number eventGoodsNum) {
        synchronized (this) {
            this.eventGoodsNum = eventGoodsNum;
            eventGoodsNumId = eventGoodsNum == null ? null : eventGoodsNum.getId();
            eventGoodsNum__resolvedKey = eventGoodsNumId;
        }
    }
    /** To-one relationship, resolved on first access. */
    @Generated(hash = 557087159)
    public Number getEventGoodsNum() {
        Long __key = this.eventGoodsNumId;
        if (eventGoodsNum__resolvedKey == null
                || !eventGoodsNum__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            NumberDao targetDao = daoSession.getNumberDao();
            Number eventGoodsNumNew = targetDao.load(__key);
            synchronized (this) {
                eventGoodsNum = eventGoodsNumNew;
                eventGoodsNum__resolvedKey = __key;
            }
        }
        return eventGoodsNum;
    }
    @Generated(hash = 1227731470)
    private transient Long eventGoodsNum__resolvedKey;
    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1459865304)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getEventDao() : null;
    }
    /** Used for active entity operations. */
    @Generated(hash = 1542254534)
    private transient EventDao myDao;
    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    public Long getMoneyId() {
        return this.moneyId;
    }
    public void setMoneyId(Long moneyId) {
        this.moneyId = moneyId;
    }
    public Long getHealthId() {
        return this.healthId;
    }
    public void setHealthId(Long healthId) {
        this.healthId = healthId;
    }
    public Long getEventGoodsNumId() {
        return this.eventGoodsNumId;
    }
    public void setEventGoodsNumId(Long eventGoodsNumId) {
        this.eventGoodsNumId = eventGoodsNumId;
    }
    public boolean getIsNeedSelect() {
        return this.isNeedSelect;
    }
    public void setIsNeedSelect(boolean isNeedSelect) {
        this.isNeedSelect = isNeedSelect;
    }
    public boolean getIsGood() {
        return this.isGood;
    }
    public void setIsGood(boolean isGood) {
        this.isGood = isGood;
    }
    public String getBtnCancel() {
        return this.btnCancel;
    }
    public void setBtnCancel(String btnCancel) {
        this.btnCancel = btnCancel;
    }
    public String getBtnOk() {
        return this.btnOk;
    }
    public void setBtnOk(String btnOk) {
        this.btnOk = btnOk;
    }
    public String getResultBad() {
        return this.resultBad;
    }
    public void setResultBad(String resultBad) {
        this.resultBad = resultBad;
    }
    public String getResultGood() {
        return this.resultGood;
    }
    public void setResultGood(String resultGood) {
        this.resultGood = resultGood;
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
    @Generated(hash = 304604348)
    public Event(Long id, String title, String message, String resultGood,
            String resultBad, String btnOk, String btnCancel, boolean isGood,
            boolean isNeedSelect, Long eventGoodsNumId, Long healthId, Long moneyId) {
        this.id = id;
        this.title = title;
        this.message = message;
        this.resultGood = resultGood;
        this.resultBad = resultBad;
        this.btnOk = btnOk;
        this.btnCancel = btnCancel;
        this.isGood = isGood;
        this.isNeedSelect = isNeedSelect;
        this.eventGoodsNumId = eventGoodsNumId;
        this.healthId = healthId;
        this.moneyId = moneyId;
    }
    @Generated(hash = 344677835)
    public Event() {
    }
}
