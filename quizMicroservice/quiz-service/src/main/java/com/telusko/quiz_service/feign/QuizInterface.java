package com.telusko.quiz_service.feign;

import com.telusko.quiz_service.entity.QuestionWrapper;
import com.telusko.quiz_service.entity.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import com.telusko.quiz_service.entity.QuizWrapper;

import java.util.List;

@FeignClient("QUESTION-SERVICE")
public interface QuizInterface {

    @GetMapping("/question/generate")
    public ResponseEntity<List<Integer>> generateQuestionsForQuiz
            (@RequestParam String categoryName, @RequestParam int numberOfQuestions);

    @PostMapping("/question/getQuestions")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(@RequestBody List<Integer> questionIds);

    @PostMapping("/question/getScore")
    public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses);

}
