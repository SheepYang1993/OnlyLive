package me.sheepyang.onlylive.greenentity;

import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToOne;

import me.sheepyang.onlylive.greenentity.dao.DaoSession;
import me.sheepyang.onlylive.greenentity.dao.GoodsDao;
import me.sheepyang.onlylive.greenentity.dao.NumberDao;

/**
 * 物品
 * Created by SheepYang on 2016/10/12 22:51.
 */
@Entity
public class Goods {
    @Id(autoincrement = true)
    private Long id;
    private String name;
    private String unit;// 物品单位
    @ToOne(joinProperty = "numberId")
    private Number number;
    private Long numberId;
    @ToOne(joinProperty = "priceId")
    private Number price;
    private Long priceId;
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
    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 528918466)
    public void setPrice(Number price) {
        synchronized (this) {
            this.price = price;
            priceId = price == null ? null : price.getId();
            price__resolvedKey = priceId;
        }
    }
    /** To-one relationship, resolved on first access. */
    @Generated(hash = 1932576357)
    public Number getPrice() {
        Long __key = this.priceId;
        if (price__resolvedKey == null || !price__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            NumberDao targetDao = daoSession.getNumberDao();
            Number priceNew = targetDao.load(__key);
            synchronized (this) {
                price = priceNew;
                price__resolvedKey = __key;
            }
        }
        return price;
    }
    @Generated(hash = 1564144330)
    private transient Long price__resolvedKey;
    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 447020683)
    public void setNumber(Number number) {
        synchronized (this) {
            this.number = number;
            numberId = number == null ? null : number.getId();
            number__resolvedKey = numberId;
        }
    }
    /** To-one relationship, resolved on first access. */
    @Generated(hash = 217196591)
    public Number getNumber() {
        Long __key = this.numberId;
        if (number__resolvedKey == null || !number__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            NumberDao targetDao = daoSession.getNumberDao();
            Number numberNew = targetDao.load(__key);
            synchronized (this) {
                number = numberNew;
                number__resolvedKey = __key;
            }
        }
        return number;
    }
    @Generated(hash = 791875021)
    private transient Long number__resolvedKey;
    /** Used for active entity operations. */
    @Generated(hash = 258717323)
    private transient GoodsDao myDao;
    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    public Long getPriceId() {
        return this.priceId;
    }
    public void setPriceId(Long priceId) {
        this.priceId = priceId;
    }
    public Long getNumberId() {
        return this.numberId;
    }
    public void setNumberId(Long numberId) {
        this.numberId = numberId;
    }
    public String getUnit() {
        return this.unit;
    }
    public void setUnit(String unit) {
        this.unit = unit;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 2112467424)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getGoodsDao() : null;
    }
    @Generated(hash = 584686666)
    public Goods(Long id, String name, String unit, Long numberId, Long priceId) {
        this.id = id;
        this.name = name;
        this.unit = unit;
        this.numberId = numberId;
        this.priceId = priceId;
    }
    @Generated(hash = 1770709345)
    public Goods() {
    }
}
