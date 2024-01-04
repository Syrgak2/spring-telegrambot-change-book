package com.example.spring.telegrambot.bookChange.command.commands;

import com.example.spring.telegrambot.bookChange.bot.TelegramBot;
import com.example.spring.telegrambot.bookChange.command.Command;
import com.example.spring.telegrambot.bookChange.model.User;
import com.example.spring.telegrambot.bookChange.repository.UserRepository;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import static com.example.spring.telegrambot.bookChange.command.CommandUtils.*;
import static com.example.spring.telegrambot.bookChange.model.UserStatusName.*;

public class AddBookCommand implements Command {
    private UserRepository userRepository;
    private TelegramBot telegramBot;
    String addMessage = " ";


    public AddBookCommand(TelegramBot telegramBot, UserRepository userRepository) {
        this.telegramBot = telegramBot;
        this.userRepository = userRepository;
    }

    @Override
    public void execute(Update update ) {
        Long chatId = getChatId(update);
        String userName = getUserName(update);

        User user = userRepository.find(userName);
        if (user.getUserStatus().equals(ACTIVE.getStatus())) {
            addMessage = "Ведите название книги";
            sendStartMessage(chatId, addMessage, user);
        } else if (user.getUserStatus().equals(WAITING.getStatus())) {
            addMessage = getMessage(update) + " книга добавлена";
            addBook(chatId, addMessage, user);
        }
    }

    private void sendStartMessage(Long chatId, String message, User user) {
        sendMessage(chatId, message, user);
        user.setUserStatus(WAITING.getStatus());
    }

    private void addBook(Long chatId, String message, User user) {
        sendMessage(chatId, message, user);
        user.setUserStatus(ACTIVE.getStatus());
    }

    private void sendMessage(Long chatId, String message, User user) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId.toString());
        sendMessage.setText(message);

        try {
            telegramBot.execute(sendMessage);
        } catch (TelegramApiException ignored) {

        }
    }
}
