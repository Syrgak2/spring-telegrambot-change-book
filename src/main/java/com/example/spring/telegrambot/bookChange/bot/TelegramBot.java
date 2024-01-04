package com.example.spring.telegrambot.bookChange.bot;

import com.example.spring.telegrambot.bookChange.command.CommandContainer;
import com.example.spring.telegrambot.bookChange.config.BotConfig;
import com.example.spring.telegrambot.bookChange.model.User;
import com.example.spring.telegrambot.bookChange.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

import static com.example.spring.telegrambot.bookChange.command.CommandUtils.getMessage;
import static com.example.spring.telegrambot.bookChange.command.CommandUtils.getUserName;
import static com.example.spring.telegrambot.bookChange.model.UserStatusName.WAITING;

@Component
public class TelegramBot extends TelegramLongPollingBot {
    BotConfig botConfig = new BotConfig();
    private UserService userService;
    private CommandContainer commandContainer = new CommandContainer(this, userService);

    public TelegramBot(String botToken, UserService userService, CommandContainer commandContainer) {
        super(botToken);
        this.userService = userService;
        this.commandContainer = commandContainer;
    }

    public TelegramBot() {

    }

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
        User user = userService.getUserReference(getUserName(update));
        if (update.hasMessage() && update.getMessage().hasText()) {
            String massageText = getMessage(update).trim();

            if (massageText.startsWith(COMMAND_PREFIX)) {
                String commandIdentifier = massageText.split(" ")[0].toLowerCase();
                commandContainer.findCommand(commandIdentifier).execute(update);
            } else {
                if (user.getUserStatus().equals(WAITING.getStatus())) {
                    commandContainer.findCommand("/add").execute(update);
                }
            }
        }
    }

}
