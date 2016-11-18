package me.sheepyang.onlylive.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.JoinEntity;
import org.greenrobot.greendao.annotation.ToMany;

import java.util.List;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;
import me.sheepyang.onlylive.entity.dao.DaoSession;
import me.sheepyang.onlylive.entity.dao.PlayerDao;
import me.sheepyang.onlylive.entity.dao.PlayerGoodsDao;

/**
 * 玩家
 * Created by SheepYang on 2016/10/12 22:42.
 */
@Entity
public class Player {
    @Id(autoincrement = true)
    private Long id;
    private String name;
    private boolean isFirst;// 是否第一次进入游戏
    private String city;// 当前所在城市
    private String cash;// 当前现金值
    private String debt;// 当前负债值
    private String deposit;// 当前存款值
    private String health;// 当前健康值
    private String house;// 当前房子数量
    private String houseTotal;// 总房子数量
    private String week;// 当前周数
    private String weekTotal;// 总周数
    @ToMany
    @JoinEntity(
            entity = JoinPlayerGoodsToPlayer.class,
            sourceProperty = "playerId",
            targetProperty = "playerGoodsId"
    )
    private List<PlayerGoods> playerGoodsList;
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
    @Generated(hash = 1775365651)
    public synchronized void resetPlayerGoodsList() {
        playerGoodsList = null;
    }
    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 499914965)
    public List<PlayerGoods> getPlayerGoodsList() {
        if (playerGoodsList == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            PlayerGoodsDao targetDao = daoSession.getPlayerGoodsDao();
            List<PlayerGoods> playerGoodsListNew = targetDao._queryPlayer_PlayerGoodsList(id);
            synchronized (this) {
                if(playerGoodsList == null) {
                    playerGoodsList = playerGoodsListNew;
                }
            }
        }
        return playerGoodsList;
    }
    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1600887847)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getPlayerDao() : null;
    }
    /** Used for active entity operations. */
    @Generated(hash = 2108114900)
    private transient PlayerDao myDao;
    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    public String getWeekTotal() {
        return this.weekTotal;
    }
    public void setWeekTotal(String weekTotal) {
        this.weekTotal = weekTotal;
    }
    public String getWeek() {
        return this.week;
    }
    public void setWeek(String week) {
        this.week = week;
    }
    public String getHouseTotal() {
        return this.houseTotal;
    }
    public void setHouseTotal(String houseTotal) {
        this.houseTotal = houseTotal;
    }
    public String getHouse() {
        return this.house;
    }
    public void setHouse(String house) {
        this.house = house;
    }
    public String getHealth() {
        return this.health;
    }
    public void setHealth(String health) {
        this.health = health;
    }
    public String getDeposit() {
        return this.deposit;
    }
    public void setDeposit(String deposit) {
        this.deposit = deposit;
    }
    public String getDebt() {
        return this.debt;
    }
    public void setDebt(String debt) {
        this.debt = debt;
    }
    public String getCash() {
        return this.cash;
    }
    public void setCash(String cash) {
        this.cash = cash;
    }
    public String getCity() {
        return this.city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public boolean getIsFirst() {
        return this.isFirst;
    }
    public void setIsFirst(boolean isFirst) {
        this.isFirst = isFirst;
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
    @Generated(hash = 1253903671)
    public Player(Long id, String name, boolean isFirst, String city, String cash,
            String debt, String deposit, String health, String house,
            String houseTotal, String week, String weekTotal) {
        this.id = id;
        this.name = name;
        this.isFirst = isFirst;
        this.city = city;
        this.cash = cash;
        this.debt = debt;
        this.deposit = deposit;
        this.health = health;
        this.house = house;
        this.houseTotal = houseTotal;
        this.week = week;
        this.weekTotal = weekTotal;
    }
    @Generated(hash = 30709322)
    public Player() {
    }
}
