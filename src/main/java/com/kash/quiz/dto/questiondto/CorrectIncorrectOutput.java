package com.kash.quiz.dto.questiondto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class CorrectIncorrectOutput {
    private Integer id;
    private String question;
    private String correctAnswer;
    private String userAnswer;
}
