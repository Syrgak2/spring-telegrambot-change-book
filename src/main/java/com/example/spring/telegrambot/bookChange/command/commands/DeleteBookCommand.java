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

public class DeleteBookCommand implements Command {
    private TelegramBot telegramBot;
    private UserRepository userRepository;

    private String deleteMessage = " ";


    public DeleteBookCommand(TelegramBot telegramBot, UserRepository userRepository) {
        this.userRepository = userRepository;
        this.telegramBot = telegramBot;
    }


    @Override
    public void execute(Update update) {
        Long chatId = getChatId(update);
        String userName = getUserName(update);

        User user = userRepository.find(userName);
        if (user.getUserStatus().equals(ACTIVE.getStatus())) {
            deleteMessage = "Выберите книгу для удаления";
            sendStartMessage(chatId, deleteMessage, user);
        } else if (user.getUserStatus().equals(WAITING.getStatus())) {
            deleteMessage = getMessage(update) + " книга уделена";
            addBook(chatId, deleteMessage, user);
        }
    }

    private void sendStartMessage(Long chatId, String message, User user) {
        sendMessage(chatId, message);
        user.setUserStatus(WAITING.getStatus());
    }

    private void addBook(Long chatId, String message, User user) {
        sendMessage(chatId, message);
        user.setUserStatus(ACTIVE.getStatus());
    }

    private void sendMessage(Long chatId, String message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId.toString());
        sendMessage.setText(message);

        try {
            telegramBot.execute(sendMessage);
        } catch (TelegramApiException ignored) {

        }
    }
}
