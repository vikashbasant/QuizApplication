package com.kash.quiz.dao;

import com.kash.quiz.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
public interface RoleRepo extends JpaRepository<Role, Integer> {
}
