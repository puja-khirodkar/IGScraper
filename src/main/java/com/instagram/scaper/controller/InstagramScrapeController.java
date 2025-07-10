package com.instagram.scaper.controller;


import com.instagram.scaper.model.Users;
import com.instagram.scaper.service.InstagramScrapeService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/instagram")
public class InstagramScrapeController {
    @Autowired
    InstagramScrapeService service;
    Logger logger = Logger.getLogger(InstagramScrapeController.class.getName());

// Use Autowire or constructor DI
// public InstagramScrapeController(InstagramScrapeService service) {
//        this.service = service;
//    }

    @GetMapping("/")
    public String getWelcomeMessage(){
        return "Hello, Welcome to our User Portal!!";
    }

    @GetMapping("/csrf-token")
    public CsrfToken getToken(HttpServletRequest request){
        return (CsrfToken) request.getAttribute("_csrf");
    }

    @GetMapping("/accounts")
    public List<String> getAccounts(@RequestParam(defaultValue = "remotework") String hashtag){
        logger.info("Received request for hashtag:" + hashtag);
        List<String> resultAccounts = service.getAccountsByHashTag(hashtag);
        return resultAccounts;
    }

    @GetMapping("/users")
    public List<Users> getUsers() {
        logger.info("Received request for all users");
        return service.getUsers();
    }

     @GetMapping("/user/{userId}")
     public Users getUserById(@PathVariable String userId) {
         logger.info("Received request for user by ID:" + userId);
        return service.getUserById(userId);
    }

    @PostMapping("/users")
    public void addUsers(@RequestBody Users user) {
        logger.info("Received request for all users");
        service.addUser(user);
    }

    @PutMapping("/users")
    public String updateUser(@RequestBody Users user) {
        logger.info("Received request for update user");
        service.updateUser(user);
        return "User updated successfully";
    }

    @DeleteMapping("/users/{userId}")
    public String deleteUser(@PathVariable String userId) {
        logger.info("Received request for delete user");
        service.deleteUser(userId);
        return "User updated successfully";
    }
}
