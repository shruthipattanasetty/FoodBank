package org.shruthipattanasetty.foodbankdonation.controllers;

import jakarta.validation.Valid;
import org.shruthipattanasetty.foodbankdonation.dtos.FoodDto;
import org.shruthipattanasetty.foodbankdonation.models.Food;
import org.shruthipattanasetty.foodbankdonation.models.Menu;
import org.shruthipattanasetty.foodbankdonation.models.User;
import org.shruthipattanasetty.foodbankdonation.services.FoodService;
import org.shruthipattanasetty.foodbankdonation.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
public class FoodController {

    @Autowired
    FoodService foodService;
    @Autowired
    UserService userService;


    //To add food by the Restaurant
    @PostMapping("food/add-food/{userId}")
    public String saveFoodTh(@ModelAttribute("food") Food food, @PathVariable("userId")int userId, Model model) throws Exception{
    User user = userService.getUserById(userId);
    food.setStatus("AVAILABLE");
    food.setZipcode(user.getZipcode());
    food.setAddress(user.getAddress().getAddressone());
    Food food1 = foodService.saveFood(food);

    List<Food> foodList = user.getFoods();
    foodList.add(food1);
    userService.saveUser(user);
    FoodDto foodDto = new FoodDto();
    foodDto.setMessage("Food Added");
    foodDto.setStatus("ok");
    model.addAttribute("food",new Food());
    model.addAttribute("user",user);
    model.addAttribute("foods",user.getFoods());
    model.addAttribute("foodDto", foodDto);
    model.addAttribute("menus",foodService.getMenuList());
    return "index";
    }

    //To search food based on zipcode by volunteer
    @PostMapping("foods-search-th/{userId}")
    public String getFoodsByZipcodeTh(Model model, @ModelAttribute("food") Food food, @PathVariable int userId) throws Exception {
       User user = userService.getUserById(userId);
       List<Food> foods = foodService.getByZipcode(food.getZipcode());
       List<Food> nonPickedFoods = new ArrayList<>();
       for (Food food1: foods) {
           if(food1.getBlockedOn()!=null) {
               Date blockedOn = food1.getBlockedOn();
               Calendar cal = Calendar.getInstance();
               cal.setTime(blockedOn);
               cal.add(Calendar.HOUR, 1);
               Date timeAfterOneHour = cal.getTime();
               Date currentTime = new Date();
               if(currentTime.after(timeAfterOneHour)) {
                   if (food1.getStatus().equals("PICKING")) {
                       food1.setStatus("AVAILABLE");
                       food1.setBlockedOn(null);
                       foodService.saveFood(food1);
                   }
                   nonPickedFoods.add(food1);
               } else if (food1.getVolunteerId()==userId) {
                   nonPickedFoods.add(food1);
               }
           } else {
               nonPickedFoods.add(food1);
           }
       }
       model.addAttribute("foods",nonPickedFoods);
        if (nonPickedFoods.isEmpty()){
            FoodDto foodDto = new FoodDto();
            foodDto.setMessage("No Foods available for this zipcode");
            model.addAttribute("foodDto", foodDto);
        }
       model.addAttribute("user",user);
       return "userindex";
    }


    //To delete food by restaurant
    @GetMapping("foods-delete-th/{id}/{userId}")
    public String getFoodsByZipcodeTh(@PathVariable int id,@PathVariable int userId, Model model) throws Exception{
        User user = userService.getUserById(userId);
        Food food = foodService.getById(id);
        if(user!=null) {
            user.getFoods().remove(food);
            userService.saveUser(user);
        }
        foodService.deleteById(id);
        model.addAttribute("user",user);
        model.addAttribute("foods",user.getFoods());
        model.addAttribute("food",new Food());
        List<Menu> menus = foodService.getMenuList();
        model.addAttribute("menus",menus);
        return "index";
    }


    //To pick food by volunteer
    @GetMapping("foods-pick-th/{id}/{userId}")
    public String pickFoodsByIdTh(@PathVariable int id,@PathVariable int userId, Model model) throws Exception{
         User user = userService.getUserById(userId);
         Food food = foodService.getById(id);

         food.setStatus("PICKING");
         food.setBlockedOn(new Date());
         food.setVolunteerId(userId);
         foodService.saveFood(food);
         model.addAttribute("user",user);
         model.addAttribute("foods",user.getFoods());
         model.addAttribute("food",new Food());
         List<Menu> menus = foodService.getMenuList();
         model.addAttribute("menus",menus);
        return "userindex";
    }

    //To display list of foods from menu dropdown
    @GetMapping("menu-list")
    public String getMenuList(Model model,@ModelAttribute("user")User user) {
         List<Menu> menus =foodService.getMenuList();
         model.addAttribute("menus",menus);
         model.addAttribute("user",user);
        return "index";
    }

    //To display edit popup
    @GetMapping("/edit/{userId}/{id}")
    public String showUpdateForm(@PathVariable("userId") int userId,@PathVariable("id") int id, Model model) throws Exception {
        Food food = foodService.getById(id);
        model.addAttribute("food", food);
        User user = userService.getUserById(userId);
        model.addAttribute("user", user);
        return "update-food";
    }

    //To update added food and quantity by restaurant
    @PostMapping("/update/{userId}/{id}")
    public String updateUser(@PathVariable("userId") int userId, @PathVariable("id") int id, @Valid Food food,
                             BindingResult result, Model model) throws Exception {
        if (result.hasErrors()) {
            food.setId(id);
            return "update-user";
        }

        Food food1 = foodService.getById(id);
        food1.setName(food.getName());
        food1.setQuantity(food.getQuantity());
        foodService.saveFood(food1);
        User user = userService.getUserById(userId);
        model.addAttribute("user", user);
        model.addAttribute("foods", user.getFoods());
        return "index";
    }

}
