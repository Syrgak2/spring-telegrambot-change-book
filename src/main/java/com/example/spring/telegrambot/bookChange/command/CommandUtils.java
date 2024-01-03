package com.example.spring.telegrambot.bookChange.command;

import org.telegram.telegrambots.meta.api.objects.Update;

public class CommandUtils {

    public static Long getChatId(Update update) {
        return update.getMessage().getChatId();
    }

    public static String getMessage(Update update) {
        return update.getMessage().getText();
    }

    public static String getFirstName(Update update) {
        return update.getMessage().getFrom().getFirstName();
    }

    public static String getLastName(Update update) {
        return update.getMessage().getFrom().getLastName();
    }

    public static String getUserName(Update update) {
        return update.getMessage().getFrom().getUserName();
    }

}
