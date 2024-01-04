package com.example.spring.telegrambot.bookChange.repository;

import com.example.spring.telegrambot.bookChange.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

}
