package com.example.spring.telegrambot.bookChange.repository;

import com.example.spring.telegrambot.bookChange.model.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {

}
