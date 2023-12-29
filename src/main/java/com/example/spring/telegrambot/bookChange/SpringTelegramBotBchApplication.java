package com.example.spring.telegrambot.bookChange;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SpringTelegramBotBchApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringTelegramBotBchApplication.class, args);
	}

}
