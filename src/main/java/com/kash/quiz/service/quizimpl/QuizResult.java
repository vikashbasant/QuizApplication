package com.kash.quiz.service.quizimpl;

import com.kash.quiz.constant.QuizConstant;
import com.kash.quiz.repo.QuizRepo;
import com.kash.quiz.exception.QuizException;
import com.kash.quiz.model.Question;
import com.kash.quiz.model.Quiz;
import com.kash.quiz.payload.CorrectIncorrectOutput;
import com.kash.quiz.payload.QuizSubmitDTO;
import com.kash.quiz.payload.Response;
import com.kash.quiz.payload.SubmitDTO;
import com.kash.quiz.util.QuizService;
import com.kash.quiz.util.QuizServiceType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class QuizResult implements QuizService {

    private final QuizRepo quizDAO;
    private final Response response;

    @Autowired
    public QuizResult(QuizRepo quizDAO, Response response) {
        this.quizDAO = quizDAO;
        this.response = response;
    }

    @Override
    public QuizServiceType getServiceType() {
        return QuizServiceType.SUBMIT_QUIZ;
    }

    @Override
    public <T> Response executeService(T t) throws QuizException {

        log.info(":==> QuizResult:: Inside executeService Method <==:");

        //=> Fetch SubmitDTO from t:
        SubmitDTO submitDTO = (SubmitDTO) t;

        //=> Fetch quizId from submitDTO:
        Integer quizId = submitDTO.getId();

        //=> Fetch List<QuizSubmitDTO> from submitDTO:
        List<QuizSubmitDTO> quizSubmitDTO = submitDTO.getQuizSubmitDTO();

        //=> Find quiz with quizId:
        Quiz quiz = quizDAO.findById(quizId)
                .orElseThrow(() -> new QuizException("Quiz Not Found with quizId = " + quizId));

        //=> Fetch List<Question> from quiz:
        List<Question> questions = quiz.getQuestions();

        //=> Create a list of CorrectIncorrectOutput:
        List<CorrectIncorrectOutput> ciOutputs = new ArrayList<>();

        //=> For Keep Track correct output:
        int rightAns = 0;
        for (int i = 0; i < Math.min(quizSubmitDTO.size(), questions.size()); i++) {
            QuizSubmitDTO userAnswer = quizSubmitDTO.get(i);
            Question question = questions.get(i);

            //=> Update the question, userAnswer, correctAnswer and id into CorrectIncorrectOutput:
            CorrectIncorrectOutput ci = new CorrectIncorrectOutput();
            ci.setId(userAnswer.getId());
            ci.setQuestion(question.getQuestionTitle());
            ci.setUserAnswer(userAnswer.getUserResponse());
            ci.setCorrectAnswer(question.getRightAnswer());

            //=> Simply add CorrectIncorrectOutput into List<CorrectIncorrectOutput>:
            ciOutputs.add(ci);

            //=> Check for correct answer:
            if (userAnswer.getUserResponse().equals(question.getRightAnswer())) {
                rightAns++;
            }
        }

        /*----Simply Return The Response----*/
        response.setStatus(QuizConstant.SUCCESS_STATUS);
        response.setStatusCode(QuizConstant.STATUS_CODE);
        response.setMessage("You are getting: " + rightAns + " out of: " + ciOutputs.size());
        response.setData(ciOutputs);

        return response;
    }
}
