package com.example.spring.telegrambot.bookChange.command.commands;

import com.example.spring.telegrambot.bookChange.bot.TelegramBot;
import com.example.spring.telegrambot.bookChange.command.Command;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class RegisterCommand implements Command {

    private enum BotState{
        FIRST, SECOND
    }

    private BotState botState = BotState.FIRST;

    TelegramBot telegramBot;

    private final String registerMessage = "Для начало мне нужно ваше имя и фамилия";

    public RegisterCommand(TelegramBot telegramBot) {
        this.telegramBot = telegramBot;
    }

    @Override
    public void execute(Update update) {

        Long chatId = update.getMessage().getChatId();
        String afterRegister = " \n в качестве никнейма я буду использовать " + update.getMessage().getChat().getUserName();

        switch (botState) {
            case FIRST:
                botState = BotState.SECOND;
                sendMessage(chatId, registerMessage);
                break;
            case SECOND:
                sendMessage(chatId, "Ваша имя " + update.getMessage().getText() + afterRegister);
                break;
            default:
        }
    }

    private void sendMessage(Long chatId, String sendedMessage) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId.toString());
        sendMessage.setText(registerMessage);

        try {
            telegramBot.execute(sendMessage);
        } catch (TelegramApiException ignored) {

        }
    }
}
