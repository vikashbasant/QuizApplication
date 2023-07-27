package com.kash.quiz.service.questionimpl;

import com.kash.quiz.constant.QuizConstant;
import com.kash.quiz.dto.Response;
import com.kash.quiz.exception.QuizException;
import com.kash.quiz.model.Question;
import com.kash.quiz.repo.QuestionRepo;
import com.kash.quiz.util.QuizService;
import com.kash.quiz.util.QuizServiceType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class DeleteQuestion implements QuizService {

    @Autowired
    private final QuestionRepo qDAO;

    @Autowired
    private final Response response;

    @Override
    public QuizServiceType getServiceType () {
        return QuizServiceType.DELETE_QUESTION;
    }

    @Override
    public <T> Response executeService (T t) throws QuizException {

        log.info(":==> DeleteQuestion:: Inside executeService Method <==:");

        //=> Fetch questionId from t:
        Integer questionId = (Integer) t;


        /*----Before delete the Question, Fetch the Question:----*/
        Question byQuestionId =
                qDAO.findById(questionId).orElseThrow(() -> new QuizException("Question Not " +
                        "Found With QuestionId = " + questionId));

        /*----Now Simply Delete the Category with CategoryId:----*/
        qDAO.deleteById(questionId);


        /*----Now Simply Return Response----*/
        response.setStatus(QuizConstant.SUCCESS_STATUS);
        response.setStatusCode(QuizConstant.STATUS_CODE);
        response.setMessage("Successfully Delete The Question With QuestionId = " + questionId);
        response.setData(byQuestionId);

        return response;
    }
}
