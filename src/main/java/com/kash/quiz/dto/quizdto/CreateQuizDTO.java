package com.kash.quiz.dto.quizdto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class CreateQuizDTO {
    private String category;
    private Integer noOfQuestion;
    private String title;
}
