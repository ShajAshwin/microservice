package com.telusko.quiz_service.entity;

import lombok.Data;

@Data
public class QuizWrapper {

    private String question;
    private String option1;
    private String option2;
    private String option3;
    private String category;
}
