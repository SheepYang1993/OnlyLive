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

    private boolean isSelect;// 是否需要选择
    private String selectYes;
    private String selectNo;
    private String resultYes;
    private String resultNo;
    @ToOne(joinProperty = "moneyId")
    private Number moneyYes;
    private Long moneyIdYes;
    @ToOne(joinProperty = "healthId")
    private Number healthYes;
    private Long healthIdYes;
    @ToOne(joinProperty = "moneyId")
    private Number moneyNo;
    private Long moneyIdNo;
    @ToOne(joinProperty = "healthId")
    private Number healthNo;
    private Long healthIdNo;
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
                if(eventGoodsList == null) {
                    eventGoodsList = eventGoodsListNew;
                }
            }
        }
        return eventGoodsList;
    }
    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 945182403)
    public void setHealthNo(Number healthNo) {
        synchronized (this) {
            this.healthNo = healthNo;
            healthId = healthNo == null ? null : healthNo.getId();
            healthNo__resolvedKey = healthId;
        }
    }
    /** To-one relationship, resolved on first access. */
    @Generated(hash = 2102698929)
    public Number getHealthNo() {
        Long __key = this.healthId;
        if (healthNo__resolvedKey == null || !healthNo__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            NumberDao targetDao = daoSession.getNumberDao();
            Number healthNoNew = targetDao.load(__key);
            synchronized (this) {
                healthNo = healthNoNew;
                healthNo__resolvedKey = __key;
            }
        }
        return healthNo;
    }
    @Generated(hash = 1125738077)
    private transient Long healthNo__resolvedKey;
    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 251201693)
    public void setMoneyNo(Number moneyNo) {
        synchronized (this) {
            this.moneyNo = moneyNo;
            moneyId = moneyNo == null ? null : moneyNo.getId();
            moneyNo__resolvedKey = moneyId;
        }
    }
    /** To-one relationship, resolved on first access. */
    @Generated(hash = 2115034009)
    public Number getMoneyNo() {
        Long __key = this.moneyId;
        if (moneyNo__resolvedKey == null || !moneyNo__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            NumberDao targetDao = daoSession.getNumberDao();
            Number moneyNoNew = targetDao.load(__key);
            synchronized (this) {
                moneyNo = moneyNoNew;
                moneyNo__resolvedKey = __key;
            }
        }
        return moneyNo;
    }
    @Generated(hash = 1796366402)
    private transient Long moneyNo__resolvedKey;
    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 484459411)
    public void setHealthYes(Number healthYes) {
        synchronized (this) {
            this.healthYes = healthYes;
            healthId = healthYes == null ? null : healthYes.getId();
            healthYes__resolvedKey = healthId;
        }
    }
    /** To-one relationship, resolved on first access. */
    @Generated(hash = 2046098057)
    public Number getHealthYes() {
        Long __key = this.healthId;
        if (healthYes__resolvedKey == null || !healthYes__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            NumberDao targetDao = daoSession.getNumberDao();
            Number healthYesNew = targetDao.load(__key);
            synchronized (this) {
                healthYes = healthYesNew;
                healthYes__resolvedKey = __key;
            }
        }
        return healthYes;
    }
    @Generated(hash = 1507035183)
    private transient Long healthYes__resolvedKey;
    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1203144837)
    public void setMoneyYes(Number moneyYes) {
        synchronized (this) {
            this.moneyYes = moneyYes;
            moneyId = moneyYes == null ? null : moneyYes.getId();
            moneyYes__resolvedKey = moneyId;
        }
    }
    /** To-one relationship, resolved on first access. */
    @Generated(hash = 1842504392)
    public Number getMoneyYes() {
        Long __key = this.moneyId;
        if (moneyYes__resolvedKey == null || !moneyYes__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            NumberDao targetDao = daoSession.getNumberDao();
            Number moneyYesNew = targetDao.load(__key);
            synchronized (this) {
                moneyYes = moneyYesNew;
                moneyYes__resolvedKey = __key;
            }
        }
        return moneyYes;
    }
    @Generated(hash = 951742251)
    private transient Long moneyYes__resolvedKey;
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
    public Long getHealthIdNo() {
        return this.healthIdNo;
    }
    public void setHealthIdNo(Long healthIdNo) {
        this.healthIdNo = healthIdNo;
    }
    public Long getMoneyIdNo() {
        return this.moneyIdNo;
    }
    public void setMoneyIdNo(Long moneyIdNo) {
        this.moneyIdNo = moneyIdNo;
    }
    public Long getHealthIdYes() {
        return this.healthIdYes;
    }
    public void setHealthIdYes(Long healthIdYes) {
        this.healthIdYes = healthIdYes;
    }
    public Long getMoneyIdYes() {
        return this.moneyIdYes;
    }
    public void setMoneyIdYes(Long moneyIdYes) {
        this.moneyIdYes = moneyIdYes;
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
    public boolean getIsSelect() {
        return this.isSelect;
    }
    public void setIsSelect(boolean isSelect) {
        this.isSelect = isSelect;
    }
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
    @Generated(hash = 1311951174)
    public Event(Long id, String title, String message, boolean isGoodEvent,
            Long moneyId, Long healthId, boolean isSelect, String selectYes,
            String selectNo, String resultYes, String resultNo, Long moneyIdYes,
            Long healthIdYes, Long moneyIdNo, Long healthIdNo) {
        this.id = id;
        this.title = title;
        this.message = message;
        this.isGoodEvent = isGoodEvent;
        this.moneyId = moneyId;
        this.healthId = healthId;
        this.isSelect = isSelect;
        this.selectYes = selectYes;
        this.selectNo = selectNo;
        this.resultYes = resultYes;
        this.resultNo = resultNo;
        this.moneyIdYes = moneyIdYes;
        this.healthIdYes = healthIdYes;
        this.moneyIdNo = moneyIdNo;
        this.healthIdNo = healthIdNo;
    }
    @Generated(hash = 344677835)
    public Event() {
    }
}
