package com.kash.quiz.controller;

import com.kash.quiz.exception.QuizException;
import com.kash.quiz.payload.QuestionDTO;
import com.kash.quiz.payload.Response;
import com.kash.quiz.util.QuizService;
import com.kash.quiz.util.QuizServiceFactory;
import com.kash.quiz.util.QuizServiceType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/question")
@RequiredArgsConstructor
@Slf4j
public class QuestionController {

    @Autowired
    private final QuizServiceFactory factory;


    @GetMapping("/allQuestions")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Response> getAllQuestion() throws QuizException {

        log.info("===: QuestionController:: Inside getAllQuestion Method :===");
        QuizService service = factory.getService(QuizServiceType.GET_ALL_QUESTION);
        Response response = service.executeService("");
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @GetMapping("/allQuestions/{category}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Response> getAllQuestionByCategory(@PathVariable("category") String category) throws QuizException {

        log.info("===: QuestionController:: Inside getAllQuestionByCategory Method :===");
        QuizService service = factory.getService(QuizServiceType.GET_ALL_QUESTION_BY_CATEGORY);
        Response response = service.executeService(category);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @PostMapping("/addQuestion")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Response> addQuestion(@RequestBody QuestionDTO questionDTO) throws QuizException {

        log.info("===: QuestionController:: Inside addQuestion Method :===");
        QuizService service = factory.getService(QuizServiceType.ADD_QUESTION);
        Response response = service.executeService(questionDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);

    }

    @DeleteMapping("/deleteQuestion/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Response> deleteQuestion(@PathVariable("id") Integer questionId) throws QuizException {

        log.info("===: QuestionController:: Inside deleteQuestion Method :===");
        QuizService service = factory.getService(QuizServiceType.DELETE_QUESTION);
        Response response = service.executeService(questionId);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @PutMapping("/updateQuestion")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Response> updateQuestion(@RequestBody QuestionDTO questionDTO) throws QuizException {

        log.info("===: QuestionController:: Inside updateQuestion Method :===");
        QuizService service = factory.getService(QuizServiceType.UPDATE_QUESTION);
        Response response = service.executeService(questionDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }
}
