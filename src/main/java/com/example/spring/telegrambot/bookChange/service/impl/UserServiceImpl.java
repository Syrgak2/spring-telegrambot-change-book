package com.example.spring.telegrambot.bookChange.service.impl;

import com.example.spring.telegrambot.bookChange.model.User;
import com.example.spring.telegrambot.bookChange.repository.UserRepository;
import com.example.spring.telegrambot.bookChange.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepo;

    public UserServiceImpl(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public boolean save(User user) {

        if (userRepo.existsById(user.getUserId())) {
            return false;
        }

        userRepo.save(user);
        return true;
    }

    @Override
    public boolean save(String userId, String userFirstName, String userLastName, String userStatus) {
        User user = new User(userId, userFirstName, userLastName, userStatus);
        return save(user);
    }

    @Override
    public Optional<User> findUser(String userId) {
        return userRepo.findById(userId);
    }

    @Override
    public User getUserReference(String userId) {
        return userRepo.getReferenceById(userId);
    }


    @Override
    public void setUserStatus(User user, String status) {
        user.setUserStatus(status);
    }
}
