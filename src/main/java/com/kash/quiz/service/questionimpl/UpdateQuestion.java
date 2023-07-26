package com.kash.quiz.service.questionimpl;

import com.kash.quiz.constant.QuizConstant;
import com.kash.quiz.repo.QuestionRepo;
import com.kash.quiz.exception.QuizException;
import com.kash.quiz.model.Question;
import com.kash.quiz.dto.questiondto.QuestionDTO;
import com.kash.quiz.dto.Response;
import com.kash.quiz.util.QuizService;
import com.kash.quiz.util.QuizServiceType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UpdateQuestion implements QuizService {

    @Autowired
    private final Response response;

    @Autowired
    private final QuestionRepo qDAO;


    @Override
    public QuizServiceType getServiceType () {
        return QuizServiceType.UPDATE_QUESTION;
    }

    @Override
    public <T> Response executeService (T t) throws QuizException {

        log.info(":==> UpdateQuestion:: Inside executeService Method <==:");

        //=> Fetch QuestionDTO form t:
        QuestionDTO questionDTO = (QuestionDTO) t;

        //=> Fetch question with questionId, if not found, then simply return exception:
        Question question = qDAO.findById(questionDTO.getId()).orElseThrow(() -> new QuizException("Question Not " +
                "Found With QuestionId = " + questionDTO.getId()));

        //=> Update Question:
        question.setCategory(questionDTO.getCategory());
        question.setDifficultyLevel(questionDTO.getDifficultyLevel());
        question.setOption1(questionDTO.getOption1());
        question.setOption2(questionDTO.getOption2());
        question.setOption3(questionDTO.getOption3());
        question.setOption4(questionDTO.getOption4());
        question.setQuestionTitle(questionDTO.getQuestionTitle());
        question.setRightAnswer(questionDTO.getRightAnswer());

        /*----Save post into DB:----*/
        Question updateQuestion = qDAO.save(question);

        /*----Simply Return The Response----*/
        response.setStatus(QuizConstant.SUCCESS_STATUS);
        response.setStatusCode(QuizConstant.STATUS_CODE);
        response.setMessage("Successfully Update The Question With questionId = " + questionDTO.getCategory());
        response.setData(updateQuestion);

        return response;


    }
}
