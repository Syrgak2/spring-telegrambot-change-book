package com.example.spring.telegrambot.bookChange.command;


import lombok.Getter;

@Getter
public enum CommandName {
    START("/start"),
    STOP("/stop"),
    REGISTER("/register"),
    HELP("/help"),
    FIND_BOOK("/find"),
    ADD_BOOK("/add"),
    DELETE_BOOK("/delete");

    private final String command;

    CommandName(String command) {
        this.command = command;
    }


}
