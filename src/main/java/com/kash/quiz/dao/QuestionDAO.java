package com.kash.quiz.dao;

import com.kash.quiz.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionDAO extends JpaRepository<Question, Integer> {
    List<Question> findByCategory (String category);

    @Query(value = "SELECT * FROM question q Where q.category=:category ORDER BY RANDOM() LIMIT :noOfQuestion", nativeQuery = true)
    List<Question> findRandomQuestionByCategory (String category, Integer noOfQuestion);
}
