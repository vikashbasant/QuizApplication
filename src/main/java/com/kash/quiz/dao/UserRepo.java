package com.kash.quiz.dao;

import com.kash.quiz.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Optional;

@EnableJpaRepositories
public interface UserRepo extends JpaRepository<User, Integer> {
    Optional<User> findByUserEmail(String userName);
}
