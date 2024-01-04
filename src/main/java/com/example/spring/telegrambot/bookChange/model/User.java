package com.example.spring.telegrambot.bookChange.model;

import lombok.Data;

import java.util.Objects;

@Data
public class User {
    private String userId;
    private String userFirstName;
    private String userLastName;
    private String userStatus;


    public User(String userId, String userFirstName, String userLastName, String status) {
        this.userId = userId;
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
        this.userStatus = status;
    }
}
