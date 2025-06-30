package com.instagram.scaper.service;

import com.instagram.scaper.model.User;
import com.instagram.scaper.repository.IgScraperRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class InstagramScrapeService {
    @Autowired
    IgScraperRepository repository;

    private final String BASE_URL = "https://www.instagram.com/explore/tags/";
    Logger logger = Logger.getLogger(InstagramScrapeService.class.getName());


    public List<String> getAccountsByHashTag(String keyword){
        List<String> resultAccounts = new ArrayList<>();
        String url = BASE_URL + keyword + "/";
        try {
            logger.info("Calling IG service endpoint : "+url);
            Document doc = Jsoup.connect(url).userAgent("Mozilla/5.0").timeout(10000).get();
            String html = doc.toString();
//            logger.info("Received response: "+ html);

            Pattern pattern = Pattern.compile("\"username\":\"(.*?)\"");
            Matcher matcher = pattern.matcher(html);
            while (matcher.find()){
                String username = matcher.group(1);
                logger.info("Username: "+username);
                if(!resultAccounts.contains(username)){
                    resultAccounts.add(username);
                }
            }

        } catch (IOException e) {
            logger.warning("Something went wrong, please try again!");
            throw new RuntimeException(e);
        }
        return resultAccounts;
    }

    public List<User> getUsers(){
       return repository.findAll();
    }

    public User getUserById(String userId){
        return repository.findById(userId).orElse(new User());
    }

    public void addUser(User user){
        repository.save(user);
    }

    public void updateUser(User user){
        repository.save(user);
    }
    public void deleteUser(String userId){
        repository.deleteById(userId);
    }
}
