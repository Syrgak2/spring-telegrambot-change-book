package com.example.spring.telegrambot.bookChange.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String author;
}
