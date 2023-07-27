package com.kash.quiz.service.quizimpl;

import com.kash.quiz.constant.QuizConstant;
import com.kash.quiz.dto.Response;
import com.kash.quiz.dto.questiondto.QuestionWrapper;
import com.kash.quiz.exception.QuizException;
import com.kash.quiz.model.Question;
import com.kash.quiz.model.Quiz;
import com.kash.quiz.repo.QuizRepo;
import com.kash.quiz.util.QuizService;
import com.kash.quiz.util.QuizServiceType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class GetQuiz implements QuizService {

    @Autowired
    private final Response response;

    @Autowired
    private final QuizRepo quizDAO;

    @Autowired
    private final ModelMapper modelMapper;

    @Override
    public QuizServiceType getServiceType () {
        return QuizServiceType.GET_QUIZ;
    }

    @Override
    public <T> Response executeService (T t) throws QuizException {

        log.info(":==> GetQuiz:: Inside executeService Method <==:");

        //=> Fetch quizId from t:
        Integer quizId = (Integer) t;

        //=> Fetch quiz with quizId, if not found, then simply return exception:
        Quiz quiz = quizDAO.findById(quizId).orElseThrow(() -> new QuizException("Quiz Not " +
                "Found With quizId = " + quizId));

        //=> Fetch List<Question> from quiz:
        List<Question> questions = quiz.getQuestions();

        //=> Convert Question to QuestionWrapper using model mapper:
        List<QuestionWrapper> listQuestionWrapper = questions.stream().map(question -> modelMapper.map(question, QuestionWrapper.class)).collect(Collectors.toList());


        /*----Simply Return The Response----*/
        response.setStatus(QuizConstant.SUCCESS_STATUS);
        response.setStatusCode(QuizConstant.STATUS_CODE);
        response.setMessage("Fetch All Question using quizId: " + quizId);
        response.setData(listQuestionWrapper);

        return response;

    }
}
