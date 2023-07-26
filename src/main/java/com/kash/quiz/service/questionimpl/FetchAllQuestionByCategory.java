package com.kash.quiz.service.questionimpl;

import com.kash.quiz.constant.QuizConstant;
import com.kash.quiz.repo.QuestionRepo;
import com.kash.quiz.exception.QuizException;
import com.kash.quiz.model.Question;
import com.kash.quiz.payload.Response;
import com.kash.quiz.util.QuizService;
import com.kash.quiz.util.QuizServiceType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class FetchAllQuestionByCategory implements QuizService {

    @Autowired
    private Response response;

    @Autowired
    private QuestionRepo qDAO;


    @Override
    public QuizServiceType getServiceType () {
        return QuizServiceType.GET_ALL_QUESTION_BY_CATEGORY;
    }

    @Override
    public <T> Response executeService (T t) throws QuizException {

        log.info(":==> FetchAllQuestionByCategory:: Inside executeService Method <==:");

        //=> Fetch category form t:
        String category = (String) t;

        //=> Fetch All Question by category:
        List<Question> allQuestionsByCategory = qDAO.findByCategory(category);

        //=> If No Question Found, then simply return exception:
        if (allQuestionsByCategory.isEmpty()) {
            throw new QuizException("No Record Found");
        }

        /*----Simply Return The Response----*/
        response.setStatus(QuizConstant.SUCCESS_STATUS);
        response.setStatusCode(QuizConstant.STATUS_CODE);
        response.setMessage("Fetch All Question By Category Successfully!");
        response.setData(allQuestionsByCategory);

        return response;

    }
}
