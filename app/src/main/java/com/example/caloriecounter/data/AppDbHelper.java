package com.example.caloriecounter.data;

import android.database.sqlite.SQLiteDatabase;

import com.example.caloriecounter.models.DaoMaster;
import com.example.caloriecounter.models.DaoSession;
import com.example.caloriecounter.models.Food;
import com.example.caloriecounter.models.FoodDao;
import com.example.caloriecounter.models.Person;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

import javax.inject.Inject;

public class AppDbHelper implements DbHelper {

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
        Person user = mDaoSession.getPersonDao().load(1L);
        List<Food> menu = user.getMenu();
        int index = menu.indexOf(food);

        if (index >= 0) {
            return -1L;
        }

        menu.add(food);

        return 1L;
    }

    @Override
    public Long addFoodToUser(Long id) {
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
        Person user = mDaoSession.getPersonDao().loadAll().get(0);
        return user;
    }

    public List<Food> mMenu(){
        QueryBuilder<Food> cityQueryBuilder = mDaoSession.getFoodDao().queryBuilder().where(FoodDao
                .Properties.Id_food_user.eq(1));
        cityQueryBuilder.build();

        List<Food> cityList=cityQueryBuilder.list();

        return cityList;
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

    @Override
    public Food getFoodId(long id) {
        Food food = mDaoSession.getFoodDao().load(id);
        return food;
    }


}
