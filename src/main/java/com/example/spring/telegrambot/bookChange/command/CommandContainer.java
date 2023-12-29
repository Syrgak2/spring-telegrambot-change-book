package com.example.spring.telegrambot.bookChange.command;

import com.example.spring.telegrambot.bookChange.bot.TelegramBot;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

import static com.example.spring.telegrambot.bookChange.command.CommandName.START;

@Component
public class CommandContainer {

    private Map<String, Command> commandMap;
    private final Command unknowCommand;

    private TelegramBot telegramBot;


    public CommandContainer(TelegramBot telegramBot) {
        this.telegramBot = telegramBot;
        this.unknowCommand = new UnknownCommand(telegramBot);
    }


    public Command findCommand(String commandId) {
        return getCommand().getOrDefault(commandId, unknowCommand);
    }

    private Map<String, Command> getCommand() {
        commandMap = new HashMap<>();
        commandMap.put(START.getCommand(), new StartCommand(telegramBot));
        return commandMap;
    }

}
