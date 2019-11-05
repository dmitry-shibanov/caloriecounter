package com.example.caloriecounter.models;


import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;

import java.sql.Date;

@Entity(active = true, nameInDb = "Statistic")
public class StatisticModel {

    @Id
    private long id;

    @Property
    private Date date;

    @Property
    private String weight;
}
