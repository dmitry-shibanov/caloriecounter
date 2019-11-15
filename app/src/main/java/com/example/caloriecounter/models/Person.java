package com.example.caloriecounter.models;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.JoinProperty;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.ToMany;

import java.util.Date;
import java.util.List;

import androidx.annotation.Nullable;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;

@Entity(active = true, nameInDb = "Person")
public class Person {
    @Id
    private long id;

    @Property
    private String name;

    @Property
    private java.util.Date birthday;

//    @Property
//    private String aimWeight;

    @Property
    @Nullable
    private String sex;

    @Property
    private String path;

    @ToMany(joinProperties = {@JoinProperty(name = "id",referencedName = "id_food_user")})
    private List<Food> mMenu;

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated(hash = 778611619)
    private transient PersonDao myDao;

    @Generated(hash = 900911232)
    public Person(long id, String name, java.util.Date birthday, String sex, String path) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.sex = sex;
        this.path = path;
    }


    @Generated(hash = 1024547259)
    public Person() {
    }

    public long getId() {
        return this.id;
    }


    public String getName() {
        return name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public String getSex() {
        return sex;
    }

    public void setName(String name) {
        this.name = name;
    }


    public void setBirthday(java.util.Date birthday) {
        this.birthday = birthday;
    }


    public void setSex(String sex) {
        this.sex = sex;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public String getPath() {
        return this.path;
    }


    public void setPath(String path) {
        this.path = path;
    }

    public List<Food> getMenu() {
        return mMenu;
    }

    public void setMenu(List<Food> menu) {
        mMenu = menu;
    }


    public void setId(long id) {
        this.id = id;
    }


    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 336568868)
    public List<Food> getMMenu() {
        if (mMenu == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            FoodDao targetDao = daoSession.getFoodDao();
            List<Food> mMenuNew = targetDao._queryPerson_MMenu(id);
            synchronized (this) {
                if (mMenu == null) {
                    mMenu = mMenuNew;
                }
            }
        }
        return mMenu;
    }


    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 596011734)
    public synchronized void resetMMenu() {
        mMenu = null;
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
    @Generated(hash = 2056799268)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getPersonDao() : null;
    }


}
