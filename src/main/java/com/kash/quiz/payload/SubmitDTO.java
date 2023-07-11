package com.kash.quiz.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubmitDTO {
    private Integer id;
    private List<QuizSubmitDTO> quizSubmitDTO;
}
