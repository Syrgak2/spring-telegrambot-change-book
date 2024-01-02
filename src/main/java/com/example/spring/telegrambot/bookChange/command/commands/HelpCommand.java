package com.example.spring.telegrambot.bookChange.command.commands;

import com.example.spring.telegrambot.bookChange.bot.TelegramBot;
import com.example.spring.telegrambot.bookChange.command.Command;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import static com.example.spring.telegrambot.bookChange.command.CommandUtils.getChatId;
import static com.example.spring.telegrambot.bookChange.command.CommandUtils.getUserName;

public class HelpCommand implements Command {

    private TelegramBot telegramBot;


    public HelpCommand(TelegramBot telegramBot) {
        this.telegramBot = telegramBot;
    }


    @Override
    public void execute(Update update) {
        Long chatId = getChatId(update);
        String userFullName = getUserName(update);

        String helpMessage = """
                /start - начинает работу бота \s
                /register - регистрация или изменение данных \s
                /help - выводить все команды \s
                /find - поиск книг для обмена или покупки книг \s
                /add - добавление книг для обмена или продажи \s
                """;

        sendMessage(chatId, helpMessage);
    }

    private void sendMessage(Long chatId, String startMessage) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId.toString());
        sendMessage.setText(startMessage);

        try {
            telegramBot.execute(sendMessage);
        } catch (TelegramApiException ignored) {

        }
    }
}
