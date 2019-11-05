package com.example.caloriecounter.models;


import org.greenrobot.greendao.annotation.Entity;

@Entity(active = true, nameInDb = "Food")
public class Food {

    private long id;
    private String title;
    private String content;
    private String calories;
}
