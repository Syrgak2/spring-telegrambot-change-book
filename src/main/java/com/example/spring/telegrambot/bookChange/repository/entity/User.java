package com.example.spring.telegrambot.bookChange.repository.entity;

import lombok.Data;

@Data
public class User {

    private String userName;

    private Long chatId;

    private Boolean active;
}
