package com.instagram.scaper.controller;


import com.instagram.scaper.model.User;
import com.instagram.scaper.service.InstagramScrapeService;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/instagram")
public class InstagramScrapeController {
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(InstagramScrapeController.class);
    InstagramScrapeService service;
    Logger logger = Logger.getLogger(InstagramScrapeController.class.getName());

    public InstagramScrapeController(InstagramScrapeService service) {
        this.service = service;
    }

    @GetMapping("/accounts")
    public List<String> getAccounts(@RequestParam(defaultValue = "remotework") String hashtag){
        logger.info("Received request for hashtag:" + hashtag);
        List<String> resultAccounts = service.getAccountsByHashTag(hashtag);
        return resultAccounts;
    }

    @GetMapping("/users")
    public List<User> getUsers(){
        logger.info("Received request for all users");
        return service.getUsers();
    }
     @GetMapping("/user/{userId}")
    public User getUserById(@RequestParam String userId){
        logger.info("Received request for all users");
        return service.getUserById(userId);
    }

    @PostMapping("/users")
    public void addUsers(@RequestBody User user){
        logger.info("Received request for all users");
        service.addUser(user);
    }

    @PutMapping("/users")
    public String updateUser(@RequestBody User user){
        logger.info("Received request for all users");
        service.updateUser(user);
        return "User updated successfully";
    }
    @DeleteMapping("/users/{userId}")
    public String deleteUser(@RequestParam String userId){
        logger.info("Received request for all users");
        service.deleteUser(userId);
        return "User updated successfully";
    }
}
