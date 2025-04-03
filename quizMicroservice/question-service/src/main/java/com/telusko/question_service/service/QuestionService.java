package com.telusko.question_service.service;


import com.telusko.question_service.entity.Question;
import com.telusko.question_service.entity.QuestionWrapper;
import com.telusko.question_service.entity.Response;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface QuestionService {

    public String addQuestion(Question question);
    public List<Question> getAllQuestions();
    public String updateQuestion(int id, Question question);
    public Question deleteContact(int id);

    List<Question> getQuestionByCategory(String category);

    ResponseEntity<List<Integer>> getQuestionsForQuiz(String categoryName, Integer numberOfQuestions);

    ResponseEntity<List<QuestionWrapper>> getQuestionsFromIds(List<Integer> questionIds);

    ResponseEntity<Integer> getScore(List<Response> responses);
}
