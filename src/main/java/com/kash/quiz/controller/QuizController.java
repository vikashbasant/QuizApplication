package com.kash.quiz.controller;

import com.kash.quiz.exception.QuizException;
import com.kash.quiz.dto.quizdto.CreateQuizDTO;
import com.kash.quiz.dto.quizdto.QuizSubmitDTO;
import com.kash.quiz.dto.Response;
import com.kash.quiz.dto.quizdto.SubmitDTO;
import com.kash.quiz.util.QuizService;
import com.kash.quiz.util.QuizServiceFactory;
import com.kash.quiz.util.QuizServiceType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/quiz")
@RequiredArgsConstructor
@Slf4j
public class QuizController {

    @Autowired
    private final QuizServiceFactory factory;


    /**
     * <b>Handles the HTTP POST request for creating a new quiz by the ADMIN role.</b>
     *
     * @param category The category of the new quiz. It should be a valid category string.
     * @param num The number of questions to include in the quiz. It should be a valid positive integer.
     * @param title The title or name of the new quiz. It should be a non-empty string.
     * @return A {@link ResponseEntity} containing the response data and HTTP status code.
     *         If the quiz is successfully created, it returns HTTP status code 201 (CREATED) along with the response data.
     *         If there is an error during the creation process or the user does not have the required ADMIN role,
     *         it returns an appropriate HTTP status code along with the error details.
     * @throws QuizException If there is an unexpected error during the creation process.
     *
     * @see ResponseEntity
     * @see PreAuthorize
     */
    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Response> createQuiz(
            @RequestParam String category,
            @RequestParam Integer num,
            @RequestParam String title) throws QuizException
    {

        log.info("===: QuizController:: Inside createQuiz Method :===");
        QuizService service = factory.getService(QuizServiceType.CREATE_QUIZ);
        Response response = service.executeService(new CreateQuizDTO(category, num, title));
        return new ResponseEntity<>(response, HttpStatus.CREATED);

    }



    /**
     * <b>Handles the HTTP GET request for retrieving a quiz by its ID.</b>
     *
     * @param quizID The ID of the quiz to be retrieved. It should be a valid positive integer representing the quiz ID.
     * @return A {@link ResponseEntity} containing the response data and HTTP status code.
     *         If the quiz with the specified ID is found, it returns HTTP status code 200 (OK) along with the response data.
     *         If the quiz with the given ID is not found, it returns an appropriate HTTP status code along with an error message.
     * @throws QuizException If there is an unexpected error during the retrieval process.
     *
     * @see ResponseEntity
     */
    @GetMapping("/getQuiz/{quizID}")
    public ResponseEntity<Response> getQuiz(@PathVariable Integer quizID) throws QuizException {

        log.info("===: QuizController:: Inside getQuiz Method :===");
        QuizService service = factory.getService(QuizServiceType.GET_QUIZ);
        Response response = service.executeService(quizID);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }


    /**
     * <b>Handles the HTTP POST request for submitting quiz answers.</b>
     *
     * @param quizSubmitDTO A list of {@link QuizSubmitDTO} containing the user's submitted quiz answers.
     *                      It should be a valid list of quiz submission data.
     * @param quizID The ID of the quiz being submitted. It should be a valid positive integer representing the quiz ID.
     * @return A {@link ResponseEntity} containing the response data and HTTP status code.
     *         If the quiz answers are successfully submitted, it returns HTTP status code 200 (OK) along with the response data.
     *         If there is an error during the submission process or the specified quiz ID is not valid,
     *         it returns an appropriate HTTP status code along with an error message.
     * @throws QuizException If there is an unexpected error during the submission process.
     *
     * @see ResponseEntity
     * @see QuizSubmitDTO
     * @see SubmitDTO
     */
    @PostMapping("/submit/{quizID}")
    public ResponseEntity<Response> submitAnswers(
            @RequestBody List<QuizSubmitDTO> quizSubmitDTO,
            @PathVariable Integer quizID) throws QuizException
    {

        log.info("===: QuizController:: Inside submitAnswers Method :===");
        QuizService service = factory.getService(QuizServiceType.SUBMIT_QUIZ);
        Response response = service.executeService(new SubmitDTO(quizID, quizSubmitDTO));
        return new ResponseEntity<>(response, HttpStatus.OK);

    }
}
