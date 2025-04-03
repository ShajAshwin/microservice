package com.telusko.question_service.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "question")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="question_no")
    private int questionNo;
    @Column(name="question", unique = true)
    private String question;
    @Enumerated(EnumType.STRING)
    @Column(name = "difficulty")
    private Level difficultyLevel;
    @Column(name = "option_1")
    private String option1;
    @Column(name = "option_2")
    private String option2;
    @Column(name = "option_3")
    private String option3;
    @Column(name = "correct_Answer")
    private String correctAnswer;
    @Column(name = "category")
    private String category;

    public int getQuestionNo() {
        return questionNo;
    }

    public void setQuestionNo(int questionNo) {
        this.questionNo = questionNo;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Level getDifficultyLevel() {
        return difficultyLevel;
    }

    public void setDifficultyLevel(Level difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }

    public String getOption1() {
        return option1;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public String getOption2() {
        return option2;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    public String getOption3() {
        return option3;
    }

    public void setOption3(String option3) {
        this.option3 = option3;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}

