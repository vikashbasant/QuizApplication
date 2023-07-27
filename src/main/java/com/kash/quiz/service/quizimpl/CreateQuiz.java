package com.kash.quiz.service.quizimpl;

import com.kash.quiz.constant.QuizConstant;
import com.kash.quiz.dto.Response;
import com.kash.quiz.dto.quizdto.CreateQuizDTO;
import com.kash.quiz.exception.QuizException;
import com.kash.quiz.model.Question;
import com.kash.quiz.model.Quiz;
import com.kash.quiz.repo.QuestionRepo;
import com.kash.quiz.repo.QuizRepo;
import com.kash.quiz.util.QuizService;
import com.kash.quiz.util.QuizServiceType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CreateQuiz implements QuizService {

    @Autowired
    private final QuestionRepo qDAO;

    @Autowired
    private final QuizRepo quizDAO;

    @Autowired
    private final Response response;

    @Override
    public QuizServiceType getServiceType () {
        return QuizServiceType.CREATE_QUIZ;
    }

    @Override
    public <T> Response executeService (T t) throws QuizException {

        log.info(":==> CreateQuiz:: Inside executeService Method <==:");

        //=> Fetch CreateQuizDTO from t:
        CreateQuizDTO quizDTO = (CreateQuizDTO) t;

        //=> Fetch Random Question By Category from DB:
        List<Question> randomQuestions = qDAO.findRandomQuestionByCategory(quizDTO.getCategory(), quizDTO.getNoOfQuestion());


        // Now insert into Quiz Object:
        Quiz quiz = new Quiz();
        quiz.setTitle(quizDTO.getTitle());
        quiz.setQuestions(randomQuestions);

        //=> Now simply save quiz into DB:
        Quiz saveQuiz = quizDAO.save(quiz);

        /*----Simply Return The Response----*/
        response.setStatus(QuizConstant.SUCCESS_STATUS);
        response.setStatusCode(QuizConstant.STATUS_CODE);
        response.setMessage("Fetch Random " + quizDTO.getNoOfQuestion() +" Question Successfully!");
        response.setData(saveQuiz);

        return response;

    }
}
