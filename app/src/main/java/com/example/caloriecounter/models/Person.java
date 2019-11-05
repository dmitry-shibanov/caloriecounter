package com.example.caloriecounter.models;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Property;

import java.sql.Date;

@Entity(active = true, nameInDb = "Person")
public class Person {
    @Id
    private long id;

    @Property
    private String name;

    @Property
    private Date birthday;

    @Property
    private String sex;

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
}
