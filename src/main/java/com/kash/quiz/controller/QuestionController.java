package com.kash.quiz.controller;

import com.kash.quiz.exception.QuizException;
import com.kash.quiz.payload.QuestionDTO;
import com.kash.quiz.payload.Response;
import com.kash.quiz.util.QuizService;
import com.kash.quiz.util.QuizServiceFactory;
import com.kash.quiz.util.QuizServiceType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/question")
@Slf4j
public class QuestionController {

    @Autowired
    private QuizServiceFactory factory;


    @GetMapping("/allQuestions")
    public ResponseEntity<Response> getAllQuestion() throws QuizException {
        log.info("===: QuestionController:: Inside getAllQuestion Method :===");
        QuizService service = factory.getService(QuizServiceType.GET_ALL_QUESTION);
        Response response = service.executeService("");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/allQuestions/{category}")
    public ResponseEntity<Response> getAllQuestionByCategory(@PathVariable("category") String category) throws QuizException {
        log.info("===: QuestionController:: Inside getAllQuestionByCategory Method :===");
        QuizService service = factory.getService(QuizServiceType.GET_ALL_QUESTION_BY_CATEGORY);
        Response response = service.executeService(category);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/addQuestion")
    public ResponseEntity<Response> addQuestion(@RequestBody QuestionDTO questionDTO) throws QuizException {
        log.info("===: QuestionController:: Inside addQuestion Method :===");
        QuizService service = factory.getService(QuizServiceType.ADD_QUESTION);
        Response response = service.executeService(questionDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteQuestion/{id}")
    public ResponseEntity<Response> deleteQuestion(@PathVariable("id") Integer questionId) throws QuizException {
        log.info("===: QuestionController:: Inside deleteQuestion Method :===");
        QuizService service = factory.getService(QuizServiceType.DELETE_QUESTION);
        Response response = service.executeService(questionId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/updateQuestion")
    public ResponseEntity<Response> updateQuestion(@RequestBody QuestionDTO questionDTO) throws QuizException {
        log.info("===: QuestionController:: Inside updateQuestion Method :===");
        QuizService service = factory.getService(QuizServiceType.UPDATE_QUESTION);
        Response response = service.executeService(questionDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
