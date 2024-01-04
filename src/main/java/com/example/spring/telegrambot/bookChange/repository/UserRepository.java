package com.example.spring.telegrambot.bookChange.repository;

import com.example.spring.telegrambot.bookChange.model.User;

public interface UserRepository {

//**    Метод для добавления пользователя в базу.
//**    Возвращает true если при добавлении
//**    Если user сушествует в базе данных возвращает false
    boolean add(User user);

    boolean add(String userId, String userFirstName, String userLastName, String userStatus);

    User find(String userId);


    //    меняет статус пользователя
    void setUserStatus(User user, String status);
}
