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
    private int cash;// 当前现金值
    private int debt;// 当前负债值
    private int deposit;// 当前存款值
    private int health;// 当前健康值
    private int house;// 当前房子数量
    private int week;// 当前周数
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
    public int getWeek() {
        return this.week;
    }
    public void setWeek(int week) {
        this.week = week;
    }
    public int getHouse() {
        return this.house;
    }
    public void setHouse(int house) {
        this.house = house;
    }
    public int getHealth() {
        return this.health;
    }
    public void setHealth(int health) {
        this.health = health;
    }
    public int getDeposit() {
        return this.deposit;
    }
    public void setDeposit(int deposit) {
        this.deposit = deposit;
    }
    public int getDebt() {
        return this.debt;
    }
    public void setDebt(int debt) {
        this.debt = debt;
    }
    public int getCash() {
        return this.cash;
    }
    public void setCash(int cash) {
        this.cash = cash;
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
    @Generated(hash = 525045020)
    public Player(Long id, String name, boolean isFirst, int cash, int debt,
            int deposit, int health, int house, int week) {
        this.id = id;
        this.name = name;
        this.isFirst = isFirst;
        this.cash = cash;
        this.debt = debt;
        this.deposit = deposit;
        this.health = health;
        this.house = house;
        this.week = week;
    }
    @Generated(hash = 30709322)
    public Player() {
    }
}