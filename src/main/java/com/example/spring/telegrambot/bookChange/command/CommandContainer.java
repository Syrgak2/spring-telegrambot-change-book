package com.example.spring.telegrambot.bookChange.command;

import com.example.spring.telegrambot.bookChange.bot.TelegramBot;
import com.example.spring.telegrambot.bookChange.command.commands.*;
import com.example.spring.telegrambot.bookChange.repository.UserRepository;
import com.example.spring.telegrambot.bookChange.service.UserService;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

import static com.example.spring.telegrambot.bookChange.command.CommandName.*;

@Component
public class CommandContainer {

    private Map<String, Command> commandMap;
    private final Command unknowCommand;

    private TelegramBot telegramBot;
    private UserService userService;


    public CommandContainer(TelegramBot telegramBot, UserService userService) {
        this.telegramBot = telegramBot;
        this.userService = userService;
        this.unknowCommand = new UnknownCommand(telegramBot);
    }


    public Command findCommand(String commandId) {
        return getCommand().getOrDefault(commandId, unknowCommand);
    }

    private Map<String, Command> getCommand() {
        commandMap = new HashMap<>();
        commandMap.put(START.getCommand(), new StartCommand(telegramBot, userService));
        commandMap.put(REGISTER.getCommand(), new RegisterCommand(telegramBot));
        commandMap.put(HELP.getCommand(), new HelpCommand(telegramBot));
        commandMap.put(ADD_BOOK.getCommand(), new AddBookCommand(telegramBot, userService));
        commandMap.put(DELETE_BOOK.getCommand(), new DeleteBookCommand(telegramBot, userService));
        return commandMap;
    }

}
