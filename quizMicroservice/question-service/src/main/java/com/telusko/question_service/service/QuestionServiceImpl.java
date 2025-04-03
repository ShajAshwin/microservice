package com.telusko.question_service.service;

import com.telusko.question_service.dao.QuestionRepository;
import com.telusko.question_service.entity.Question;
import com.telusko.question_service.entity.QuestionWrapper;
import com.telusko.question_service.entity.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class QuestionServiceImpl implements QuestionService{

    @Autowired
    QuestionRepository questionRepository;

    @Override
    public String addQuestion(Question question) {

        Optional<Object> isExists =  questionRepository.findByQuestion(question.getQuestion());

        if(!isExists.isPresent()) {
            questionRepository.save(question);
            return "Question Saved";
        }else return "Question already present";
    }


    @Override
    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    @Override
    public String updateQuestion(int id, Question question) {
        Question ifExist = questionRepository.findById(id).orElseThrow(()-> new NoSuchElementException("Id don't match"));

        ifExist.setQuestion(question.getQuestion());
        ifExist.setDifficultyLevel(question.getDifficultyLevel());
        ifExist.setOption1(question.getOption1());
        ifExist.setOption2(question.getOption2());
        ifExist.setOption3(question.getOption3());
        ifExist.setCorrectAnswer(question.getCorrectAnswer());

        questionRepository.save(ifExist);
        return "Updated";

    }

    @Override
    public Question deleteContact(int id) {
        Question question = questionRepository.findById(id).orElseThrow(()->new NoSuchElementException("Id not matched"));

        questionRepository.deleteById(id);
        return question;
    }

    @Override
    public List<Question> getQuestionByCategory(String category) {
        return questionRepository.findByCategory(category);
    }

    @Override
    public ResponseEntity<List<Integer>> getQuestionsForQuiz(String categoryName, Integer numberOfQuestions) {

        List<Integer> questions = questionRepository.findRandomQuestionsByCategory(categoryName,numberOfQuestions);
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromIds(List<Integer> questionIds) {

        List<Question> questions = new ArrayList<>();
        List<QuestionWrapper> questionWrappers = new ArrayList<>();

        for(int id: questionIds){
            questions.add(questionRepository.findById(id).get());
        }
        for (Question question: questions){
            QuestionWrapper wrapper = new QuestionWrapper();
            wrapper.setQuestion(question.getQuestion());
            wrapper.setOption1(question.getOption1());
            wrapper.setOption2(question.getOption2());
            wrapper.setOption3(question.getOption3());
            wrapper.setCategory(question.getCategory());
            questionWrappers.add(wrapper);
        }

        return new ResponseEntity<>(questionWrappers,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Integer> getScore(List<Response> responses) {

        int rightAnswer=0;

        for(Response response: responses){
            Question question = questionRepository.findById(response.getId()).get();
            if(question.getCorrectAnswer().equals(response.getResponse())){
                rightAnswer++;
            }
        }
        return new ResponseEntity<>(rightAnswer,HttpStatus.OK);
    }
}
