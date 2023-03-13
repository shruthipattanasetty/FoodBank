package org.shruthipattanasetty.foodbankdonation.services;

import org.shruthipattanasetty.foodbankdonation.models.Food;
import org.shruthipattanasetty.foodbankdonation.models.Menu;

import java.util.List;

public interface FoodService {
    Food saveFood(Food food);

    List<Food> getAllFoods();

    List<Food> getByZipcode(String zipcode) throws Exception;

    void deleteById(int id);

    Food getById(int id) throws Exception;

    List<Menu> getMenuList();
}
