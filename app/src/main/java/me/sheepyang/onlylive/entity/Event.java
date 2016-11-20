package me.sheepyang.onlylive.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.JoinEntity;
import org.greenrobot.greendao.annotation.ToMany;
import org.greenrobot.greendao.annotation.ToOne;

import java.util.List;

import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;

import me.sheepyang.onlylive.entity.dao.DaoSession;
import me.sheepyang.onlylive.entity.dao.EventDao;
import me.sheepyang.onlylive.entity.dao.NumberDao;
import me.sheepyang.onlylive.entity.dao.EventGoodsDao;

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
    private boolean isGoodEvent;
    private boolean isSelect;// 是否需要选择
    private String selectYes;
    private String selectNo;
    @ToOne(joinProperty = "moneyId")
    private Number money;
    private Long moneyId;
    @ToOne(joinProperty = "healthId")
    private Number health;
    private Long healthId;
    @ToMany
    @JoinEntity(
            entity = JoinEventGoodsToEvent.class,
            sourceProperty = "eventId",
            targetProperty = "eventGoodsId"
    )
    private List<EventGoods> eventGoodsList;

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

    /**
     * Resets a to-many relationship, making the next get call to query for a fresh result.
     */
    @Generated(hash = 714419965)
    public synchronized void resetEventGoodsList() {
        eventGoodsList = null;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 1494291556)
    public List<EventGoods> getEventGoodsList() {
        if (eventGoodsList == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            EventGoodsDao targetDao = daoSession.getEventGoodsDao();
            List<EventGoods> eventGoodsListNew = targetDao._queryEvent_EventGoodsList(id);
            synchronized (this) {
                if (eventGoodsList == null) {
                    eventGoodsList = eventGoodsListNew;
                }
            }
        }
        return eventGoodsList;
    }

    /**
     * called by internal mechanisms, do not call yourself.
     */
    @Generated(hash = 279909944)
    public void setHealth(Number health) {
        synchronized (this) {
            this.health = health;
            healthId = health == null ? null : health.getId();
            health__resolvedKey = healthId;
        }
    }

    /**
     * To-one relationship, resolved on first access.
     */
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

    /**
     * called by internal mechanisms, do not call yourself.
     */
    @Generated(hash = 577161037)
    public void setMoney(Number money) {
        synchronized (this) {
            this.money = money;
            moneyId = money == null ? null : money.getId();
            money__resolvedKey = moneyId;
        }
    }

    /**
     * To-one relationship, resolved on first access.
     */
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

    /**
     * called by internal mechanisms, do not call yourself.
     */
    @Generated(hash = 1459865304)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getEventDao() : null;
    }

    /**
     * Used for active entity operations.
     */
    @Generated(hash = 1542254534)
    private transient EventDao myDao;
    /**
     * Used to resolve relations
     */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    public Long getHealthId() {
        return this.healthId;
    }

    public void setHealthId(Long healthId) {
        this.healthId = healthId;
    }

    public Long getMoneyId() {
        return this.moneyId;
    }

    public void setMoneyId(Long moneyId) {
        this.moneyId = moneyId;
    }

    public boolean getIsSelect() {
        return this.isSelect;
    }

    public void setIsSelect(boolean isSelect) {
        this.isSelect = isSelect;
    }

    public boolean getIsGoodEvent() {
        return this.isGoodEvent;
    }

    public void setIsGoodEvent(boolean isGoodEvent) {
        this.isGoodEvent = isGoodEvent;
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

    public String getSelectNo() {
        return this.selectNo;
    }

    public void setSelectNo(String selectNo) {
        this.selectNo = selectNo;
    }

    public String getSelectYes() {
        return this.selectYes;
    }

    public void setSelectYes(String selectYes) {
        this.selectYes = selectYes;
    }

    @Generated(hash = 277671164)
    public Event(Long id, String title, String message, boolean isGoodEvent,
            boolean isSelect, String selectYes, String selectNo, Long moneyId, Long healthId) {
        this.id = id;
        this.title = title;
        this.message = message;
        this.isGoodEvent = isGoodEvent;
        this.isSelect = isSelect;
        this.selectYes = selectYes;
        this.selectNo = selectNo;
        this.moneyId = moneyId;
        this.healthId = healthId;
    }

    @Generated(hash = 344677835)
    public Event() {
    }
}
