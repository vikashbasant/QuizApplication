package com.kash.quiz.controller;

import com.kash.quiz.dto.Response;
import com.kash.quiz.dto.registoruserdto.RegisterRequestDTO;
import com.kash.quiz.exception.QuizException;
import com.kash.quiz.util.QuizService;
import com.kash.quiz.util.QuizServiceFactory;
import com.kash.quiz.util.QuizServiceType;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private final QuizServiceFactory factory;


    /**
     * <b>Handles the HTTP PUT request for updating a user by their ID.</b>
     *
     * @param request The {@link RegisterRequestDTO} containing the updated user details. It should be a valid object.
     * @param userId The ID of the user to be updated. It should be a valid positive integer representing the user ID.
     * @return A {@link ResponseEntity} containing the response data and HTTP status code.
     *         If the user is successfully updated, it returns HTTP status code 200 (OK) along with the response data.
     *         If the user with the specified ID is not found or there is an error during the update process,
     *         it returns an appropriate HTTP status code along with an error message.
     * @throws QuizException If there is an unexpected error during the update process.
     *
     * @see RegisterRequestDTO
     * @see ResponseEntity
     */
    @PutMapping("/update/{userId}")
    public ResponseEntity<Response> updateUser (@RequestBody @Valid RegisterRequestDTO request, @PathVariable @Valid Integer userId) throws QuizException {

        LOGGER.info("=>>UserController:: Inside updateUser Method<<=");
        QuizService service = factory.getService(QuizServiceType.UPDATE_USER);

        // => we want set userId into request it-self:
        request.setUserId(userId);
        Response response = service.executeService(request);

        return ResponseEntity.ok(response);

    }




    /**
     * <b>Handles the HTTP GET request for retrieving a user by their ID.</b>
     *
     * @param userId The ID of the user to be retrieved. It should be a valid positive integer representing the user ID.
     * @return A {@link ResponseEntity} containing the response data and HTTP status code.
     *         If the user with the specified ID is found, it returns HTTP status code 200 (OK) along with the response data.
     *         If the user with the given ID is not found, it returns an appropriate HTTP status code along with an error message.
     * @throws QuizException If there is an unexpected error during the retrieval process.
     *
     * @see ResponseEntity
     */
    @GetMapping("/get-user/{userId}")
    public ResponseEntity<Response> getUser (@PathVariable @Valid Integer userId) throws QuizException {

        LOGGER.info("=>>UserController:: Inside getUser Method<<=");
        QuizService service = factory.getService(QuizServiceType.GET_USER);
        Response response = service.executeService(userId);
        return ResponseEntity.ok(response);

    }



    /**
     * <b>Handles the HTTP GET request for retrieving all users by the ADMIN role.</b>
     *
     * @return A {@link ResponseEntity} containing the response data and HTTP status code.
     *         If the users are successfully retrieved, it returns HTTP status code 200 (OK) along with the response data.
     *         If there is an error during the retrieval process or the user does not have the required ADMIN role,
     *         it returns an appropriate HTTP status code along with an error message.
     * @throws QuizException If there is an unexpected error during the retrieval process.
     *
     * @see ResponseEntity
     * @see PreAuthorize
     */
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/get-all-user")
    public ResponseEntity<Response> getAllUsers () throws QuizException {

        LOGGER.info("=>>UserController:: Inside getAllUsers Method<<=");
        QuizService service = factory.getService(QuizServiceType.GET_ALL_USER);
        Response response = service.executeService("");
        return ResponseEntity.ok(response);

    }



    /**
     * <b>Handles the HTTP DELETE request for deleting a user by the ADMIN role.</b>
     *
     * @param uId The ID of the user to be deleted. It should be a valid positive integer representing the user ID.
     * @return A {@link ResponseEntity} containing the response data and HTTP status code.
     *         If the user is successfully deleted, it returns HTTP status code 200 (OK) along with the response data.
     *         If there is an error during the deletion process or the user does not have the required ADMIN role,
     *         it returns an appropriate HTTP status code along with an error message.
     * @throws QuizException If there is an unexpected error during the deletion process.
     *
     * @see ResponseEntity
     * @see PreAuthorize
     */
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete-user/{userId}")
    public ResponseEntity<Response> deleteUser (@PathVariable("userId") @Valid Integer uId) throws QuizException {

        LOGGER.info("=>>UserController:: Inside deleteUser Method<<=");
        QuizService service = factory.getService(QuizServiceType.DELETE_USER);
        Response response = service.executeService(uId);
        return ResponseEntity.ok(response);

    }
}
