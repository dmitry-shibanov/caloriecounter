package com.example.caloriecounter.models;


import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;

@Entity(active = true, nameInDb = "Food")
public class Food {

    @Id
    private long id;

    @Property
    private String title;

    @Property
    private String content;

    @Property
    private String calories;

    @Property
    private long id_food_user;

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated(hash = 1296197325)
    private transient FoodDao myDao;


    @Generated(hash = 504340335)
    public Food(long id, String title, String content, String calories,
            Long id_food_user) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.calories = calories;
        this.id_food_user = id_food_user;
    }
    @Generated(hash = 866324199)
    public Food() {
    }


    public long getId() {
        return this.id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getContent() {
        return this.content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getCalories() {
        return this.calories;
    }
    public void setCalories(String calories) {
        this.calories = calories;
        daoSession.getFoodDao().update(this);
    }
    public Long getId_food_user() {
        return this.id_food_user;
    }
    public void setId_food_user(Long id_food_user) {
        this.id_food_user = id_food_user;
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
    @Generated(hash = 505459956)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getFoodDao() : null;
    }

}
