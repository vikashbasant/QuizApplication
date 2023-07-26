package com.kash.quiz.controller;

import com.kash.quiz.exception.QuizException;
import com.kash.quiz.dto.questiondto.QuestionDTO;
import com.kash.quiz.dto.Response;
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


    /**
     * <b>Handles the HTTP GET request for retrieving all questions by the ADMIN role.</b>
     *
     * @return A {@link ResponseEntity} containing the response data and HTTP status code.
     *         If the questions are successfully retrieved, it returns HTTP status code 200 (OK) along with the response data.
     *         If there is an error during the retrieval process or the user does not have the required ADMIN role,
     *         it returns an appropriate HTTP status code along with the error details.
     * @throws QuizException If there is an unexpected error during the retrieval process.
     *
     * @see ResponseEntity
     * @see PreAuthorize
     */
    @GetMapping("/allQuestions")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Response> getAllQuestion() throws QuizException {

        log.info("===: QuestionController:: Inside getAllQuestion Method :===");
        QuizService service = factory.getService(QuizServiceType.GET_ALL_QUESTION);
        Response response = service.executeService("");
        return new ResponseEntity<>(response, HttpStatus.OK);

    }



    /**
     * <b>Handles the HTTP GET request for retrieving all questions by the ADMIN role based on the specified category.</b>
     *
     * @param category The category of questions to be retrieved. It should be a valid category string.
     * @return A {@link ResponseEntity} containing the response data and HTTP status code.
     *         If the questions are successfully retrieved, it returns HTTP status code 200 (OK) along with the response data.
     *         If there is an error during the retrieval process or the user does not have the required ADMIN role,
     *         it returns an appropriate HTTP status code along with the error details.
     * @throws QuizException If there is an unexpected error during the retrieval process.
     *
     * @see ResponseEntity
     * @see PreAuthorize
     */
    @GetMapping("/allQuestions/{category}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Response> getAllQuestionByCategory(@PathVariable("category") String category) throws QuizException {

        log.info("===: QuestionController:: Inside getAllQuestionByCategory Method :===");
        QuizService service = factory.getService(QuizServiceType.GET_ALL_QUESTION_BY_CATEGORY);
        Response response = service.executeService(category);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }



    /**
     * <b>Handles the HTTP POST request for adding a new question by the ADMIN role.</b>
     *
     * @param questionDTO The {@link QuestionDTO} containing the details of the new question. It should be a valid object.
     * @return A {@link ResponseEntity} containing the response data and HTTP status code.
     *         If the question is successfully added, it returns HTTP status code 201 (CREATED) along with the response data.
     *         If there is an error during the addition process or the user does not have the required ADMIN role,
     *         it returns an appropriate HTTP status code along with the error details.
     * @throws QuizException If there is an unexpected error during the addition process.
     *
     * @see QuestionDTO
     * @see ResponseEntity
     * @see PreAuthorize
     */
    @PostMapping("/addQuestion")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Response> addQuestion(@RequestBody QuestionDTO questionDTO) throws QuizException {

        log.info("===: QuestionController:: Inside addQuestion Method :===");
        QuizService service = factory.getService(QuizServiceType.ADD_QUESTION);
        Response response = service.executeService(questionDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);

    }


    /**
     * <b>Handles the HTTP DELETE request for deleting a question by the ADMIN role.</b>
     *
     * @param questionId The ID of the question to be deleted.
     * @return A {@link ResponseEntity} containing the response data and HTTP status code.
     *         If the question is successfully deleted, it returns HTTP status code 200 (OK) along with the response data.
     *         If there is an error during the delete process or the user does not have the required ADMIN role,
     *         it returns an appropriate HTTP status code along with the error details.
     * @throws QuizException If there is an unexpected error during the delete process.
     *
     * @see ResponseEntity
     * @see PreAuthorize
     */
    @DeleteMapping("/deleteQuestion/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Response> deleteQuestion(@PathVariable("id") Integer questionId) throws QuizException {

        log.info("===: QuestionController:: Inside deleteQuestion Method :===");
        QuizService service = factory.getService(QuizServiceType.DELETE_QUESTION);
        Response response = service.executeService(questionId);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }



    /**
     * <b>Handles the HTTP PUT request for updating a question by the ADMIN role.</b>
     *
     * @param questionDTO The {@link QuestionDTO} containing the updated question details. It should be a valid object.
     * @return A {@link ResponseEntity} containing the response data and HTTP status code.
     *         If the question is successfully updated, it returns HTTP status code 200 (OK) along with the response data.
     *         If there is an error during the update process or the user does not have the required ADMIN role,
     *         it returns an appropriate HTTP status code along with the error details.
     * @throws QuizException If there is an unexpected error during the update process.
     *
     * @see QuestionDTO
     * @see ResponseEntity
     * @see PreAuthorize
     */
    @PutMapping("/updateQuestion")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Response> updateQuestion(@RequestBody QuestionDTO questionDTO) throws QuizException {

        log.info("===: QuestionController:: Inside updateQuestion Method :===");
        QuizService service = factory.getService(QuizServiceType.UPDATE_QUESTION);
        Response response = service.executeService(questionDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }
}
