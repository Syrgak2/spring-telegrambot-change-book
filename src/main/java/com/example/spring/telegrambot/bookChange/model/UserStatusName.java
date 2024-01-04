package com.example.spring.telegrambot.bookChange.model;

import lombok.Getter;

@Getter
public enum UserStatusName {
    ACTIVE("active"),
    WAITING("waiting");

    private final String status;

    UserStatusName(String status) {
        this.status = status;
    }



}
