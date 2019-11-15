package com.example.caloriecounter.data;

import com.example.caloriecounter.models.Food;
import com.example.caloriecounter.models.Person;

import java.util.List;

public interface DbHelper {
    Long addFoodToUser(Food food);
    Long removeFoodUser(Food food);
    Long removeFoodUser(Long id);

    Person getUser();

    Long saveFood(Food food);

    List<Food> getUserFood();

    List<Food> getFood();

    Food getFoodId(long id);

}
