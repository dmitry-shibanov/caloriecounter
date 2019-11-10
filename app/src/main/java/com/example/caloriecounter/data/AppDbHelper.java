package com.example.caloriecounter.data;

import android.database.sqlite.SQLiteDatabase;

import com.example.caloriecounter.models.DaoMaster;
import com.example.caloriecounter.models.DaoSession;
import com.example.caloriecounter.models.Food;
import com.example.caloriecounter.models.Person;

import java.util.List;

import javax.inject.Inject;

public class AppDbHelper implements DbHelper{

    private final DaoSession mDaoSession;

    @Inject
    public AppDbHelper(DatabaseOpenHelper dbOpenHelper) {
        SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
//        daoMaster = new DaoMaster(db);
//        daoSession = daoMaster.newSession();
//        mDaoSession = new DaoMaster(dbOpenHelper.getWritableDb()).newSession();
        mDaoSession = new DaoMaster(db).newSession();
    }

    @Override
    public Long addFoodToUser(Food food) {
        return null;
    }

    @Override
    public Long removeFoodUser(Food food) {
        return null;
    }

    @Override
    public Long removeFoodUser(Long id) {
        return null;
    }

    @Override
    public Person getUser() {
        return null;
    }

    @Override
    public Long saveFood(Food food) {
        return null;
    }

    @Override
    public List<Food> getUserFood() {
        return null;
    }

    @Override
    public List<Food> getFood() {
        List<Food> foodList = mDaoSession.getFoodDao().loadAll();
        return foodList;
    }
}
