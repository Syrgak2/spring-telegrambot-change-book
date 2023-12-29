package com.example.spring.telegrambot.bookChange.command;

import com.example.spring.telegrambot.bookChange.service.SendBotMessageService;
import org.telegram.telegrambots.meta.api.objects.Update;

import static com.example.spring.telegrambot.bookChange.command.CommandUtils.getChatId;

public class UnknownCommand implements Command {

    private static final String UNKNOWN_MESSAGE = "Я не понимаю вас";

    private final SendBotMessageService sendBotMessageService;

    public UnknownCommand(SendBotMessageService sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public void execute(Update update) {
        sendBotMessageService.sendMessage(getChatId(update), UNKNOWN_MESSAGE);
    }
}
