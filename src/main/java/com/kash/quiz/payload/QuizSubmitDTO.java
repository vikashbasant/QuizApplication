package com.kash.quiz.payload;

import lombok.Data;

@Data
public class QuizSubmitDTO {
    private Integer id;
    private String userResponse;
}
