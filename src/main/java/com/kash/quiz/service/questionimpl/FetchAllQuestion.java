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
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class FetchAllQuestion implements QuizService {

    private final Response response;
    private final QuestionRepo questionDAO;

    @Override
    public QuizServiceType getServiceType () {
        return QuizServiceType.GET_ALL_QUESTION;
    }

    @Override
    public <T> Response executeService (T t) throws QuizException {

        log.info(":==> FetchAllQuestion:: Inside executeService Method <==:");

        //=> Fetch All Question From DB:
        List<Question> allQuestions = questionDAO.findAll();

        //=> If No Question Found, then simply return exception:
        if (allQuestions.isEmpty()) {
            throw new QuizException("No Records Found!");
        }

        /*----Simply Return The Response----*/
        response.setStatus(QuizConstant.SUCCESS_STATUS);
        response.setStatusCode(QuizConstant.STATUS_CODE);
        response.setMessage("Fetch All Question Successfully! DB");
        response.setData(allQuestions);

        return response;
    }
}
