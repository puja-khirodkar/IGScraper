package com.instagram.scaper.controller;

import com.instagram.scaper.model.Users;
import com.instagram.scaper.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    UserService userService;

/*    private List<Users> users = new ArrayList<>(
            List.of(new Users(1, "puja","p@123"),
                    new Users(2, "ashish", "a@123")));*/

    @PostMapping("/register")
    public Users register(@RequestBody Users user){
        return userService.register(user);
    }


    @PostMapping("/login")
    public String login(@RequestBody Users user){
       return userService.verify(user);
//        return "Successful login";
    }
}
