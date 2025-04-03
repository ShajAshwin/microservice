package com.telusko.quiz_service.controller;


import com.telusko.quiz_service.entity.QuestionWrapper;
import com.telusko.quiz_service.entity.QuizDto;
import com.telusko.quiz_service.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {

    @Autowired
    QuizService quizService;

    @PostMapping("/create")
    public ResponseEntity<String> createQuiz(@RequestBody QuizDto quizDto){
        return quizService.createQuiz(quizDto.getCategory(), quizDto.getNumQuestions(), quizDto.getTitle());
    }

    @GetMapping("/getQuiz/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuiz(@PathVariable int id){
        return quizService.getQuiz(id);

    }
}
