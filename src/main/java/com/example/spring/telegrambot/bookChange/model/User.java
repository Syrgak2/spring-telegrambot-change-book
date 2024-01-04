package com.example.spring.telegrambot.bookChange.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.Objects;

@Data
@Entity
@Table(name = "user")
public class User {
    @Id
    private String userId;

    private String userFirstName;
    private String userLastName;
    private String userStatus;


    public User(String userId, String userFirstName, String userLastName, String status) {
        this.userId = userId;
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
        this.userStatus = status;
    }

    public User() {

    }
}
