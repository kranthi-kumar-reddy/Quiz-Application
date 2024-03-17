package com.digit.quizservice.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class QuestionWrapper {
    private int id;
    private String category;
    private String difficultyLevel;
    private String questionTitle;
    private String option1;
    private String option2;
    private String option3;
    private String option4;

}
