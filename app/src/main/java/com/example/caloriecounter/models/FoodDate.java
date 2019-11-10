package com.example.caloriecounter.models;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.ToOne;

import java.util.Date;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.NotNull;

@Entity(active = true, nameInDb = "FoodDate")
public class FoodDate {

    @Id
    private Long id;

    private long foodId;

    @ToOne(joinProperty = "foodId")
    private Food food;

    @Property
    private int count;

    @Property
    private Date mDate;

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated(hash = 699920553)
    private transient FoodDateDao myDao;


    @Generated(hash = 587499427)
    public FoodDate(Long id, long foodId, int count, Date mDate) {
        this.id = id;
        this.foodId = foodId;
        this.count = count;
        this.mDate = mDate;
    }

    @Generated(hash = 1647046223)
    public FoodDate() {
    }

    @Generated(hash = 1118738352)
    private transient Long food__resolvedKey;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getFoodId() {
        return foodId;
    }

    public void setFoodId(long foodId) {
        this.foodId = foodId;
    }

    public int getCount() {
        return this.count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Date getMDate() {
        return this.mDate;
    }

    public void setMDate(Date mDate) {
        this.mDate = mDate;
    }

    /** To-one relationship, resolved on first access. */
    @Generated(hash = 320829533)
    public Food getFood() {
        long __key = this.foodId;
        if (food__resolvedKey == null || !food__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            FoodDao targetDao = daoSession.getFoodDao();
            Food foodNew = targetDao.load(__key);
            synchronized (this) {
                food = foodNew;
                food__resolvedKey = __key;
            }
        }
        return food;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1975001119)
    public void setFood(@NotNull Food food) {
        if (food == null) {
            throw new DaoException(
                    "To-one property 'foodId' has not-null constraint; cannot set to-one to null");
        }
        synchronized (this) {
            this.food = food;
            foodId = food.getId();
            food__resolvedKey = foodId;
        }
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

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 2031701643)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getFoodDateDao() : null;
    }

}
