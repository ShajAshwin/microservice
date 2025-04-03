package com.telusko.quiz_service.service;

import com.telusko.quiz_service.dao.QuizRepository;
import com.telusko.quiz_service.entity.QuestionWrapper;
import com.telusko.quiz_service.entity.Quiz;
import com.telusko.quiz_service.entity.QuizWrapper;
import com.telusko.quiz_service.feign.QuizInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    QuizRepository quizRepository;

    @Autowired
    QuizInterface quizInterface;

    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {

        //generate Quiz is in Questions service
        List<Integer> questions = quizInterface.generateQuestionsForQuiz(category,numQ).getBody();

        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestionIds(questions);
        quizRepository.save(quiz);

        return new ResponseEntity<>("Success", HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuiz(Integer id) {
         Quiz quiz = quizRepository.findById(id).get();

         List<Integer> questionIds = quiz.getQuestionIds();
        ResponseEntity<List<QuestionWrapper>> questions = quizInterface.getQuestionsFromId(questionIds);

        return questions;
    }
}
