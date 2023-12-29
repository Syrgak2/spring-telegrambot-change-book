package com.example.spring.telegrambot.bookChange.bot;

import com.example.spring.telegrambot.bookChange.command.CommandContainer;
import com.example.spring.telegrambot.bookChange.service.impl.SendBotMessageServiceImpl;
import com.example.spring.telegrambot.bookChange.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;

@Component
public class TelegramBot extends TelegramLongPollingBot {

    @Value("${bot.username}")
    private String userName;

    @Value("${bot.token}")
    private String token;

    private final CommandContainer commandContainer;

    public static String COMMAND_PREFIX = "/";

    public TelegramBot(List<String> admins) {
        this.commandContainer = new CommandContainer(new SendBotMessageServiceImpl(this), admins);

    }

    @Override
    public String getBotUsername() {
        return userName;
    }

    @Override
    public String getBotToken() {
        return token;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String massageText = update.getMessage().getText().trim();
            String username = update.getMessage().getFrom().getFirstName();
            long chatId = update.getMessage().getChatId();
            if (massageText.startsWith(COMMAND_PREFIX)) {
                String commandIdentifier = massageText.split(" ")[0].toLowerCase();
                commandContainer.findCommand(commandIdentifier, username).execute(update);
            }
        }

    }

//    private void startCommandReceived(long chatId, String firstName) {
//        String answer = "Ассалому алейкум " + firstName + " танышканыма кубанычтамын";
//        sendMessage(chatId, answer);
//    }
//
//    private void sendMessage(long chatId, String answer) {
//        SendMessage sendMessage = new SendMessage();
//        sendMessage.setChatId(String.valueOf(chatId));
//        sendMessage.setText(answer);
//        try {
//            execute(sendMessage);
//        } catch (TelegramApiException ignored) {
//
//        }
//    }


}
