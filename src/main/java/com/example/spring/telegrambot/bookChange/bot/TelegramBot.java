package com.example.spring.telegrambot.bookChange.bot;

import com.example.spring.telegrambot.bookChange.command.CommandContainer;
import com.example.spring.telegrambot.bookChange.config.BotConfig;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
@AllArgsConstructor
public class TelegramBot extends TelegramLongPollingBot {
    BotConfig botConfig;

    private final CommandContainer commandContainer = new CommandContainer(this);

    public static String COMMAND_PREFIX = "/";


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
            String massageText = update.getMessage().getText().trim();
            long chatId = update.getMessage().getChatId();
            if (massageText.startsWith(COMMAND_PREFIX)) {
                String commandIdentifier = massageText.split(" ")[0].toLowerCase();
                commandContainer.findCommand(commandIdentifier).execute(update);
            }
        }

    }

}
