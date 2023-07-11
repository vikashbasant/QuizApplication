package com.kash.quiz.controller;

import com.kash.quiz.exception.QuizException;
import com.kash.quiz.payload.CreateQuizDTO;
import com.kash.quiz.payload.QuizSubmitDTO;
import com.kash.quiz.payload.Response;
import com.kash.quiz.payload.SubmitDTO;
import com.kash.quiz.util.QuizService;
import com.kash.quiz.util.QuizServiceFactory;
import com.kash.quiz.util.QuizServiceType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz")
@Slf4j
public class QuizController {

    @Autowired
    private QuizServiceFactory factory;

    @PostMapping("/create")
    public ResponseEntity<Response> createQuiz(@RequestParam String category, @RequestParam Integer num,
                                               @RequestParam String title) throws QuizException {
        log.info("===: QuizController:: Inside createQuiz Method :===");
        QuizService service = factory.getService(QuizServiceType.CREATE_QUIZ);
        Response response = service.executeService(new CreateQuizDTO(category, num, title));
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/getQuiz/{quizID}")
    public ResponseEntity<Response> getQuiz(@PathVariable Integer quizID) throws QuizException {
        log.info("===: QuizController:: Inside getQuiz Method :===");
        QuizService service = factory.getService(QuizServiceType.GET_QUIZ);
        Response response = service.executeService(quizID);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @PostMapping("/submit/{quizID}")
    public ResponseEntity<Response> submitAnswers(@RequestBody List<QuizSubmitDTO> quizSubmitDTO,
                                                  @PathVariable Integer quizID) throws QuizException {
        log.info("===: QuizController:: Inside submitAnswers Method :===");
        QuizService service = factory.getService(QuizServiceType.SUBMIT_QUIZ);
        Response response = service.executeService(new SubmitDTO(quizID, quizSubmitDTO));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
