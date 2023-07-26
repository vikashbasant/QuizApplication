package com.kash.quiz.service.questionimpl;

import com.kash.quiz.constant.QuizConstant;
import com.kash.quiz.dao.QuestionDAO;
import com.kash.quiz.exception.QuizException;
import com.kash.quiz.model.Question;
import com.kash.quiz.payload.QuestionDTO;
import com.kash.quiz.payload.Response;
import com.kash.quiz.util.QuizService;
import com.kash.quiz.util.QuizServiceType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AddQuestion implements QuizService {


    private final QuestionDAO qDAO;

    private final ModelMapper modelMapper;

    private final Response response;

    @Override
    public QuizServiceType getServiceType () {
        return QuizServiceType.ADD_QUESTION;
    }

    @Override
    public <T> Response executeService (T t) throws QuizException {

        log.info(":==> AddQuestion:: Inside executeService Method <==:");

        //=> Get QuestionDTO Object from t:
        QuestionDTO addQuestionDTO = (QuestionDTO) t;


        //=> Convert the addQuestionDTO to Question by using model mapper:
        Question question = modelMapper.map(addQuestionDTO, Question.class);

        //=> Now question Save into DB
        Question saveQuestion = qDAO.save(question);

        /*----Simply Return The Response----*/
        response.setStatus(QuizConstant.STATUS);
        response.setStatusCode(QuizConstant.STATUS_CODE);
        response.setMessage("Saved Question Successfully Into DB!");
        response.setData(saveQuestion);

        return response;

    }
}
