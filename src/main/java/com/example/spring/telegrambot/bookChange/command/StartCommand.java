package com.example.spring.telegrambot.bookChange.command;

import com.example.spring.telegrambot.bookChange.bot.TelegramBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import static com.example.spring.telegrambot.bookChange.command.CommandUtils.getChatId;

public class StartCommand implements Command {
    TelegramBot telegramBot;

    public StartCommand(TelegramBot telegramBot) {
        this.telegramBot = telegramBot;
    }


    private final String START_MESSAGE = "Привет я самый полезный бот на в мире";


    @Override
    public void execute(Update update) {
        Long chatId = getChatId(update);
        sendMessage(chatId);
    }

    private void sendMessage(Long chatId) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId.toString());
        sendMessage.setText(START_MESSAGE);

        try {
            telegramBot.execute(sendMessage);
        } catch (TelegramApiException ignored) {

        }
    }
}
