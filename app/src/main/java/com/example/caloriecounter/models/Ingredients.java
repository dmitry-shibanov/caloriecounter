package com.example.caloriecounter.models;

import org.greenrobot.greendao.annotation.Entity;

@Entity(active = true, nameInDb = "Person")
public class Ingredients {
    private long id;
    private String title;
}
