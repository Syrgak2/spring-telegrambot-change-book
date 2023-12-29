package com.example.spring.telegrambot.bookChange.command;

import com.example.spring.telegrambot.bookChange.command.annotation.AdminCommand;
import com.example.spring.telegrambot.bookChange.service.SendBotMessageService;
import static java.util.Objects.nonNull;

import com.example.spring.telegrambot.bookChange.service.UserService;

import com.google.common.collect.ImmutableMap;

import com.example.spring.telegrambot.bookChange.command.CommandName.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.spring.telegrambot.bookChange.command.CommandName.START;

public class CommandContainer {

    private Map<String, Command> commandMap;

    private List<String> admins;
    private final Command unknowCommand;


    public CommandContainer(SendBotMessageService sendBotMessageService, List<String> admins) {
        commandMap = new HashMap<>();
        commandMap.put(START.getCommand(), new StartCommand(sendBotMessageService));


        this.admins = admins;
        this.unknowCommand = new UnknownCommand(sendBotMessageService);
    }


    public Command findCommand(String commandId, String username) {
        Command orDefault = commandMap.getOrDefault(commandId, unknowCommand);
        if (isAdminCommand(orDefault)) {
            if (admins.contains(username)) {
                return orDefault;
            } else {
                return unknowCommand;
            }
        }
        return orDefault;
    }

    private boolean isAdminCommand(Command command) {
        return nonNull(command.getClass().getAnnotation(AdminCommand.class));
    }
}
