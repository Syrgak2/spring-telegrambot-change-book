package com.example.spring.telegrambot.bookChange.command;


public enum CommandName {
    START("/start"),
    STOP("/stop"),
    REGISTER("/register"),
    ADD_BOOK("/addbook"),
    DELETE("/deletebook");

    private final String command;

    CommandName(String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }


}
