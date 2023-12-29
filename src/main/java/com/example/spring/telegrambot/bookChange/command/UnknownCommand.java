package com.example.spring.telegrambot.bookChange.command;

import com.example.spring.telegrambot.bookChange.bot.TelegramBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import static com.example.spring.telegrambot.bookChange.command.CommandUtils.getChatId;

public class UnknownCommand implements Command {
    private TelegramBot telegramBot;

    private final String unknownMessage = "Я не понимаю вас";

    public UnknownCommand(TelegramBot telegramBot) {
        this.telegramBot = telegramBot;
    }

    @Override
    public void execute(Update update) {
        sendMessage(getChatId(update));
    }

    private void sendMessage(Long chatId) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId.toString());
        sendMessage.setText(unknownMessage);

        try {
            telegramBot.execute(sendMessage);
        } catch (TelegramApiException ignored) {

        }
    }
}
