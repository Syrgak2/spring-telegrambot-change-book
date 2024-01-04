package com.example.spring.telegrambot.bookChange.service;

import com.example.spring.telegrambot.bookChange.model.User;

import java.util.Optional;

public interface UserService {
//**    Метод для добавления пользователя в базу.
//**    Возвращает true если при добавлении
//**    Если user сушествует в базе данных возвращает false
    boolean save(User user);

    boolean save(String userId, String firstName, String lastName, String userStatus);

    Optional<User> findUser(String userId);

    User getUserReference(String userId);

    void setUserStatus(User user, String status);


}
