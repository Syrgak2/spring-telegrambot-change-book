package com.example.spring.telegrambot.bookChange.command;

import com.example.spring.telegrambot.bookChange.command.annotation.AdminCommand;
import com.example.spring.telegrambot.bookChange.service.SendBotMessageService;
import com.example.spring.telegrambot.bookChange.service.UserService;
import org.telegram.telegrambots.meta.api.objects.Update;

import static com.example.spring.telegrambot.bookChange.command.CommandUtils.getChatId;

@AdminCommand
public class StartCommand implements Command {
    private final SendBotMessageService sendBotMessageService;

    private final static String START_MESSAGE = "Привет я самый полезный бот на в мире";

    public StartCommand(SendBotMessageService sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override

    public void execute(Update update) {
        Long chatId = getChatId(update);
        sendBotMessageService.sendMessage(chatId, START_MESSAGE);
    }
}
