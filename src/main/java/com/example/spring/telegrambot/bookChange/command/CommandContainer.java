package com.example.spring.telegrambot.bookChange.command;

import com.example.spring.telegrambot.bookChange.bot.TelegramBot;
import com.example.spring.telegrambot.bookChange.command.commands.*;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

import static com.example.spring.telegrambot.bookChange.command.CommandName.*;

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
        commandMap.put(REGISTER.getCommand(), new RegisterCommand(telegramBot));
        commandMap.put(HELP.getCommand(), new HelpCommand(telegramBot));
        commandMap.put(ADD_BOOK.getCommand(), new AddBookCommand(telegramBot));
        commandMap.put(DELETE_BOOK.getCommand(), new DeleteBookCommand(telegramBot));
        return commandMap;
    }

}
