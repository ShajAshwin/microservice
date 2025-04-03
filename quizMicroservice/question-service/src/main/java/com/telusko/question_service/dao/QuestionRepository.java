package com.telusko.question_service.dao;

import com.telusko.question_service.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuestionRepository extends JpaRepository<Question,Integer > {


    List<Question> findByCategory(String Category);

    @Query(value = "SELECT q FROM question q Where q.category=:category ORDER BY RAND() LIMIT :numQ",nativeQuery = true)
    List<Integer> findRandomQuestionsByCategory(String category, int numQ);

    Optional<Object> findByQuestion(String question);
}
