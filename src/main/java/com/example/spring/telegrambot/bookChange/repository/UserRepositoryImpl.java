package com.example.spring.telegrambot.bookChange.repository;

import com.example.spring.telegrambot.bookChange.model.User;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserRepositoryImpl implements UserRepository {
    Map<String, User> users = new HashMap<>();

    @Override
    public boolean add(User user) {

        if (users.containsKey(user.getUserId())) {
            return false;
        }

        users.put(user.getUserId(), user);
        return true;
    }

    @Override
    public boolean add(String userId, String userFirstName, String userLastName, String userStatus) {
        User user = new User(userId, userFirstName, userLastName, userStatus);
        return add(user);
    }

    @Override
    public User find(String userId) {
        return users.get(userId);
    }


    @Override
    public void setUserStatus(User user, String status) {
        user.setUserStatus(status);
    }


}
