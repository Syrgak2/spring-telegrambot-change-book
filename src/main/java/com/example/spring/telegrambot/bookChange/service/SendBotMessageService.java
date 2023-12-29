package com.example.spring.telegrambot.bookChange.service;

public interface SendBotMessageService {

    void sendMessage(Long chatId, String message);

}
