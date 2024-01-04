package com.example.spring.telegrambot.bookChange.command.commands;

import com.example.spring.telegrambot.bookChange.bot.TelegramBot;
import com.example.spring.telegrambot.bookChange.command.Command;
import com.example.spring.telegrambot.bookChange.repository.UserRepository;
import com.example.spring.telegrambot.bookChange.service.UserService;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import static com.example.spring.telegrambot.bookChange.command.CommandUtils.*;
import static com.example.spring.telegrambot.bookChange.model.UserStatusName.ACTIVE;


public class StartCommand extends BotCommand implements Command {
    private TelegramBot telegramBot;
    private UserService userService;


    public StartCommand(TelegramBot telegramBot, UserService userService) {
        this.telegramBot = telegramBot;
        this.userService = userService;
    }


    @Override
    public void execute(Update update) {
        Long chatId = getChatId(update);
        String userFullName = getFirstName(update) + " " + getLastName(update);
        String startMessage = " ";
        boolean additionStatus = userService.save(getUserName(update),
                getFirstName(update),
                getLastName(update),
                ACTIVE.getStatus());

        if (additionStatus) {
            startMessage = """
                    Привет я самый полезный бот на в мире.\s
                     Я могу найти книгу для обмена, покупки.\s
                     Человека для продажи и обмена ваших книг.\s
                     Для начало мне нужно зарегистрировать вас\s
                     Я буду использовать""" + " " + userFullName + " \n" +
                    "Eсли хотите изменить данные нажмите /register \n" +
                    "Для вывода всех команд нажмите /help ";
        } else {
            startMessage = "Рад ва видеть " + getFirstName(update) + getLastName(update) + "\n" +
                    """
                        Я могу найти книгу для обмена, покупки.\s
                        Человека для продажи и обмена ваших книг.\s""";
        }

        sendMessage(chatId, startMessage);
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
