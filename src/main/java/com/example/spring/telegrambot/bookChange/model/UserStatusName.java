package com.example.spring.telegrambot.bookChange.model;

import lombok.Getter;

@Getter
public enum UserStatusName {
    ACTIVE("active"),
    ADD_BOOK("addBook"),
    FIND_BOOK("findBook"),
    REGISTER("register");

    private final String status;

    UserStatusName(String status) {
        this.status = status;
    }



}
