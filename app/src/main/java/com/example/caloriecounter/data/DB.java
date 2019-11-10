package com.example.caloriecounter.data;

import android.content.Context;

public class DB {

    private static DB sDB;
    private static AppDbHelper dbHelper;
    public static DB getDB(Context context){
        if(sDB == null){
            sDB = new DB(context);
        }

        return sDB;
    }

    public AppDbHelper getDbHelper() {
        return dbHelper;
    }

    private DB(Context context){
        dbHelper = new AppDbHelper(new com.example.caloriecounter.data.DatabaseOpenHelper(context.getApplicationContext(),"calorie",null));
    }
}
