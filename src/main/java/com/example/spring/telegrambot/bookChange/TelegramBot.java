package com.example.spring.telegrambot.bookChange;

import com.example.spring.telegrambot.bookChange.config.BotConfig;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
@AllArgsConstructor
public class TelegramBot extends TelegramLongPollingBot {
    private final BotConfig botConfig;

    @Override
    public String getBotUsername() {
        return botConfig.getBotName();
    }

    @Override
    public String getBotToken() {
        return botConfig.getBotToken();
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String massageText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();

            switch (massageText) {
                case "/start":
                    startCommandReceived(chatId, update.getMessage().getChat().getFirstName());
                    break;
                case "hi":
                    sendMessage(chatId, "Жакшы, озунчу " + update.getMessage().getChat().getFirstName());
                    break;
                case "кандай":
                    sendMessage(chatId, "Жакшы, сенчи " + update.getMessage().getChat().getFirstName());
                    break;
            }
        }
    }

    private void startCommandReceived(long chatId, String firstName) {
        String answer = "Ассалому алейкум " + firstName + " танышканыма кубанычтамын";
        sendMessage(chatId, answer);
    }

    private void sendMessage(long chatId, String answer) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(String.valueOf(chatId));
        sendMessage.setText(answer);
        try {
            execute(sendMessage);
        } catch (TelegramApiException ignored) {

        }
    }


}
