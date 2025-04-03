package com.telusko.question_service.controller;

import com.telusko.question_service.entity.Question;
import com.telusko.question_service.entity.QuestionWrapper;
import com.telusko.question_service.entity.Response;
import com.telusko.question_service.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @PostMapping("/addQuestion")
    public ResponseEntity<String> readQuestions(@RequestBody Question question){
        return new ResponseEntity<>(questionService.addQuestion(question), HttpStatus.CREATED);
    }

    @GetMapping("/getAllQuestions")
    public ResponseEntity<List<Question>> getAllQuestions(){
        return new ResponseEntity<>(questionService.getAllQuestions(),HttpStatus.FOUND);
    }

    @GetMapping("category/{category}")
    public ResponseEntity<List<Question>> getQuestionByCategory(@PathVariable String category){
        return new ResponseEntity<>(questionService.getQuestionByCategory(category),HttpStatus.FOUND);
    }

    @PutMapping("/updateQuestion/{id}")
    public ResponseEntity<String> updateQuestionById(@PathVariable int id,@RequestBody Question question){
        return new ResponseEntity<>(questionService.updateQuestion(id,question),HttpStatus.OK);
    }

    @DeleteMapping("/deleteQuestion/{id}")
    public ResponseEntity<Question> deleteQuestion(@PathVariable int id){
        return new ResponseEntity<>(questionService.deleteContact(id),HttpStatus.OK);
    }

    @PostMapping("/generate")
    public ResponseEntity<List<Integer>> generateQuestionsForQuiz
            (@RequestParam String categoryName, @RequestParam int numberOfQuestions){
        return questionService.getQuestionsForQuiz(categoryName, numberOfQuestions);
    }

    @PostMapping("/getQuestions")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(@RequestBody List<Integer> questionIds){
        return questionService.getQuestionsFromIds(questionIds);
    }

    @PostMapping("/getScore")
    public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses){
        return questionService.getScore(responses);
    }


}
