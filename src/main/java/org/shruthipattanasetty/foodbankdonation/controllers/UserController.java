package org.shruthipattanasetty.foodbankdonation.controllers;

import jakarta.validation.Valid;
import org.shruthipattanasetty.foodbankdonation.daos.AddressRepoI;
import org.shruthipattanasetty.foodbankdonation.dtos.UserDto;
import org.shruthipattanasetty.foodbankdonation.models.Address;
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
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    FoodService foodService;

    @Autowired
    AddressRepoI addressRepoI;

    @GetMapping("/")
    public String loginPage(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @GetMapping("/logout")
    public String loginPagel(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }


    //Signup form
    @GetMapping("/signup")
    public String signupPage(Model model) {
        model.addAttribute("user" ,new User());
        model.addAttribute("address" , new Address());
        model.addAttribute("userDto", new UserDto());
        return "signup";
    }

    //To login depending on Restaurant or Volunteer
    @PostMapping("user/login-th")
    public String loginTh(@ModelAttribute("user") User user, Model model) throws Exception {
        User userObj = userService.getUserByEmailAndPassword(user.getEmail(),user.getPassword());
        UserDto userDto = new UserDto();
        if (userObj != null) {
            userDto.setUser(userObj);
            userDto.setMessage("Logged in");
            userDto.setStatus("ok");
            model.addAttribute("user",userObj);
            model.addAttribute("foods",userObj.getFoods());
            model.addAttribute("userDto",userDto);
            model.addAttribute("food",new Food());

            if (userObj.getUserType().name().equals("RESTAURANT")) {
                List<Menu> menus = foodService.getMenuList();
                model.addAttribute("menus",menus);
                return "index";
            } else {
                return "userindex";
            }
        } else {
            userDto.setMessage("Invalid Username/Password");
            model.addAttribute("userDto",userDto);
        }
        return "login";
    }

    //To register user -->Restaurant or Volunteer
    @PostMapping("user/register-th")
    public String registerTh(@Valid @ModelAttribute("user") User user, @ModelAttribute Address address, BindingResult bindingResult, Model model) throws Exception {
        User userObj = userService.getUserByEmail(user.getEmail());
        UserDto userDto = new UserDto();
        if (bindingResult.hasErrors()) {
            return "/signup"; // return the form template with validation errors
        }
        if (userObj == null) {
            Address addr = addressRepoI.save(address);
            user.setAddress(addr);
            user.setZipcode(addr.getZipcode());
            userService.saveUser(user);
            userDto.setUser(user);
            userDto.setStatus("ok");
            return "redirect:/";
        } else {
            userDto.setUser(user);
            userDto.setStatus("");
            userDto.setMessage("Already Registered");
        }
        model.addAttribute("userDto",userDto);
        return "/signup";
    }
}
