package org.shruthipattanasetty.foodbankdonation.services;


import jakarta.transaction.Transactional;
import org.shruthipattanasetty.foodbankdonation.daos.FoodRepoI;
import org.shruthipattanasetty.foodbankdonation.daos.MenuRepoI;
import org.shruthipattanasetty.foodbankdonation.models.Food;
import org.shruthipattanasetty.foodbankdonation.models.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class FoodServices implements FoodService {
    @Autowired
    FoodRepoI foodRepoI;

    @Autowired
    MenuRepoI menuRepoI;

    @Override
    public Food saveFood(Food food){
        return foodRepoI.save(food);
    }

    @Override
    public List<Food> getAllFoods() {
        return foodRepoI.findAll();
    }

    @Override
    public List<Food> getByZipcode(String zipcode) {
        List<Food> foods = foodRepoI.findByZipcode(zipcode);
        return foods;
    }

    @Override
    public void deleteById(int id) {
        try {
            foodRepoI.deleteById(id);
        }catch (Exception e) {
            System.out.println(e);
        }

    }

    @Override
    public Food getById(int id) {
        Food food = foodRepoI.getById(id);
        return food;
    }

    @Override
    public List<Menu> getMenuList(){
        try {
            return menuRepoI.findAll();
        } catch (Exception e){
            System.out.println();
        }
        return menuRepoI.findAll();
}


}
