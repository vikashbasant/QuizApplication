#quiz-app-apis

### Live Demo: https://quizapplication-production.up.railway.app/quiz-app/v1/swagger-ui/index.html


# Backend API's For Quiz Application

Welcome to our Quiz Application! This project is built using Spring Boot, Spring Data JPA, Hibernate, and PostgreSQL as the database.  The Spring Boot Quiz Application is an online quiz platform that allows users to participate in quizzes, view their scores, and manage quiz-related functionalities. The application aims to provide an intuitive and user-friendly interface for quiz enthusiasts to enjoy interactive quizzes on various topics & also interactive platform for both administrators and users to manage and participate in quizzes..


# Quiz Application API Overview

The Quiz Application API provides a backend for managing quizzes, questions, users, and authentication. Below are the main functionalities covered by the API:

1. **User Authentication and Registration:**
   - User registration with basic details like username, email, and password.
   - User authentication using JWT (JSON Web Tokens) for secure and stateless authentication.
   - Role-based access control with roles like ADMIN and USER.

2. **Quiz Management:**
   - Creation of quizzes by the ADMIN with a specified category, number of questions, and title.
   - Retrieval of quizzes by their ID.
   - Submission of quiz answers by users, along with the quiz ID.

3. **Question Management:**
   - Retrieval of all questions or questions by category by the ADMIN.
   - Addition of new questions by the ADMIN.
   - Deletion of questions by the ADMIN.
   - Update of existing questions by the ADMIN.

4. **User Management:**
   - Retrieval of user details by their ID.
   - Retrieval of all users by the ADMIN.
   - Update of user details by their ID by the ADMIN.
   - Deletion of users by the ADMIN.

5. **Error Handling:**
   - Proper handling of exceptions and error messages for various scenarios.
   - Responses with appropriate HTTP status codes for successful and failed operations.

6. **Security:**
   - Implementation of Spring Security to handle user authentication and role-based access control.
   - Use of JWT for secure token-based authentication.

7. **Logging:**
   - Logging of important events and methods using SLF4J.

8. **Validation:**
   - Input validation using `javax.validation` annotations to ensure data integrity.

Overall, the Quiz Application API provides a secure and robust backend for managing quizzes and user data. It allows users to register, login, create and participate in quizzes, and view their results. The API is designed to be extensible and maintainable, and it follows best practices for building web applications in Spring Boot. The functionalities are organized into separate controllers to promote modularity and reusability. The application is also secured with role-based access control to ensure that only authorized users can perform certain actions.



## Functional Requirements

1. **User Registration and Authentication:**
   - Users should be able to register an account with the application.
   - Registered users should be able to log in securely using their credentials.
   - Authentication should be implemented using JWT (JSON Web Token) for secure access.

2. **Quiz Management:**
   - Admin users should be able to create new quizzes by providing the quiz title, description, and questions with multiple-choice options.
   - Admin users should be able to edit and delete existing quizzes.
   - Each quiz should have a time limit for completion.

3. **Quiz Participation:**
   - Registered users should be able to view and participate in available quizzes.
   - Users should be able to select a quiz and answer the multiple-choice questions within the specified time limit.
   - The application should provide real-time feedback on correct and incorrect answers during the quiz.

4. **Score Tracking:**
   - After completing a quiz, the application should display the user's score and the correct answers.
   - Users should be able to view their previous quiz attempts and scores.

5. **API Documentation:**
   - The application should provide comprehensive API documentation using OpenAPI (Swagger) for easy integration and understanding of the available endpoints.

## Non-Functional Requirements

1. **Security:**
   - User passwords should be securely stored using encryption techniques.
   - Access to sensitive operations and data should be restricted based on user roles (e.g., admin, regular user).

2. **Performance:**
   - The application should be able to handle multiple concurrent users without significant performance degradation.
   - Quiz submissions and score calculations should be efficient and responsive.

3. **Usability:**
   - The user interface should be intuitive, visually appealing, and responsive across different devices and screen sizes.
   - Error messages should be informative and user-friendly.

4. **Compatibility:**
   - The application should be compatible with modern web browsers and devices.

5. **Technology Stack:**
   - The project should use Spring Boot with Java 17 as the main technology stack.
   - Spring Data JPA should be used for interacting with the database.
   - PostgreSQL should be the database of choice for storing quiz-related data.
   - JWT should be used for implementing user authentication.

6. **Testing:**
   - Unit tests and integration tests should be written to ensure the correctness of application functionality.
   - Test coverage should be adequately maintained.

7. **Documentation:**
   - The project code should be well-documented with comments and explanatory notes for better maintainability.
   - The API documentation should be up-to-date and clear for external developers to consume the API.

8. **Version Control:**
   - The project should be maintained using version control (e.g., Git) to track changes and facilitate collaboration among team members.

These requirements serve as a foundation for the development of the Spring Boot Quiz Application. You can further expand and refine these requirements based on specific use cases and additional features you plan to implement in your application.



# Installation Instructions

## Prerequisites
Before proceeding with the installation, ensure that you have the following prerequisites installed on your system:

1. **Java Development Kit (JDK) 17:** The application is built using Java 17, so make sure you have the appropriate JDK installed.

2. **Apache Maven:** The project is managed using Maven, so you'll need Maven installed to build and manage the application dependencies.

3. **PostgreSQL Database:** Since the application uses PostgreSQL as the database, you should have a running PostgreSQL server and appropriate credentials to create the necessary database.

## Installation Steps

1. **Clone the Repository:**
   Clone the project repository from the source repository using Git:

   ```bash
   git clone <repository_url>
   cd QuizApplication
   ```

2. **Build the Application:**
    Build the application using Maven:

    ```bash
    mvn clean package
    ```

This command will compile the source code, run tests, and package the application as an executable JAR file.

3. **Configure the Database:**
Open the application-dev.properties file located in the src/main/resources directory and configure the PostgreSQL database connection properties:

```properties
spring.datasource.url=jdbc:postgresql://<database_host>:<port>/<database_name>
spring.datasource.username=<database_username>
spring.datasource.password=<database_password>
```

Replace <database_host>, <port>, <database_name>, <database_username>, and <database_password> with your PostgreSQL database connection details.


4. **Run the Application:**
After configuring the database, you can run the Spring Boot application using the following command:

```bash
mvn spring-boot:run
```
The application will start, and you should see log messages indicating that the server is up and running.

5. **Access the Application:**
Once the application is running, you can access it in your web browser at:

```arduino
http://localhost:8080
```

You should see the landing page of the Spring Boot Quiz Application.

## API Documentation:

1. **API Documentation (Swagger):**
The API documentation is available using the OpenAPI (Swagger) UI. You can access it in your web browser at:

```bash
http://localhost:8080/swagger-ui.html
```

This documentation provides details about the available API endpoints and how to interact with them.

## Admin Access:

1. **Creating Admin User:**
To access the admin features of the application, you will need to create an admin user account in the database. Use an appropriate PostgreSQL database client (e.g., psql or a GUI tool like pgAdmin) to insert the admin user details into the users table with the appropriate role.

Example SQL command to create an admin user:
```sql
INSERT INTO users (username, password, role)
VALUES ('admin', '<hashed_password>', 'ADMIN');
```
Replace <hashed_password> with the hashed password of the admin user. Ensure that the password is securely hashed for better security.


2. **Admin Access:**
Once the admin user is created, you can log in as an admin using the admin credentials.


# AuthController Functionalities

## `signUp` Method

### Description
The `signUp` method handles the HTTP POST request for user registration. It allows a new user to sign up and create an account with the application.

### Parameters
- `userDTO` (Request Body): The `RegisterRequestDTO` containing the user registration details. It should be a valid object.

### Return
- `ResponseEntity<Response>`: A `ResponseEntity` containing the response data and HTTP status code.
  - If the registration is successful, it returns HTTP status code 201 (CREATED) along with the response data.
  - If there is an error during the registration process, it returns an appropriate HTTP status code along with the error details.

### Exceptions
- `QuizException`: If there is an unexpected error during the registration process.

## `signIn` Method

### Description
The `signIn` method handles the HTTP POST request for user authentication (login). It allows registered users to log in to their accounts.

### Parameters
- `userCredentials` (Request Body): The `JwtAuthRequest` containing the user login credentials. It should be a valid object.

### Return
- `ResponseEntity<Response>`: A `ResponseEntity` containing the response data and HTTP status code.
  - If the login is successful, it returns HTTP status code 200 (OK) along with the response data.
  - If the provided credentials are invalid or there is an error during the authentication process, it returns an appropriate HTTP status code along with the error details.

### Exceptions
- `QuizException`: If there is an unexpected error during the authentication process.


## API Documentation

### `@Operation` Annotation
The `@Operation` annotation is used to provide a summary and description of the API endpoints for Swagger documentation.

### `@PostMapping("/register")`
- Endpoint: `/auth/register`
- Method: POST
- Summary: Sign up a new user
- Description: This operation signs up a new user.
- Responses:
  - HTTP 400: Bad Request (In case of invalid input or validation failure).
  - HTTP 500: Internal Server Error (In case of unexpected server-side errors).
  - HTTP 201: Successfully SignUp (In case of successful user registration).

### `@PostMapping("/login")`
- Endpoint: `/auth/login`
- Method: POST
- Summary: User login (authentication)
- Description: This operation handles user authentication (login).
- Responses:
  - HTTP 400: Bad Request (In case of invalid input or validation failure).
  - HTTP 500: Internal Server Error (In case of unexpected server-side errors).
  - HTTP 200: OK (In case of successful user authentication).


# QuestionController Functionalities

## `getAllQuestion` Method

### Description
The `getAllQuestion` method handles the HTTP GET request for retrieving all questions by the ADMIN role.

### Return
- `ResponseEntity<Response>`: A `ResponseEntity` containing the response data and HTTP status code.
  - If the questions are successfully retrieved, it returns HTTP status code 200 (OK) along with the response data.
  - If there is an error during the retrieval process or the user does not have the required ADMIN role, it returns an appropriate HTTP status code along with the error details.

### Exceptions
- `QuizException`: If there is an unexpected error during the retrieval process.

### Annotations
- `@GetMapping("/allQuestions")`: Maps the method to handle the HTTP GET request for `/question/allQuestions`.
- `@PreAuthorize("hasRole('ADMIN')")`: Restricts access to the method to users with the ADMIN role.

## `getAllQuestionByCategory` Method

### Description
The `getAllQuestionByCategory` method handles the HTTP GET request for retrieving all questions by the ADMIN role based on the specified category.

### Parameters
- `category` (Path Variable): The category of questions to be retrieved. It should be a valid category string.

### Return
- `ResponseEntity<Response>`: A `ResponseEntity` containing the response data and HTTP status code.
  - If the questions are successfully retrieved, it returns HTTP status code 200 (OK) along with the response data.
  - If there is an error during the retrieval process or the user does not have the required ADMIN role, it returns an appropriate HTTP status code along with the error details.

### Exceptions
- `QuizException`: If there is an unexpected error during the retrieval process.

### Annotations
- `@GetMapping("/allQuestions/{category}")`: Maps the method to handle the HTTP GET request for `/question/allQuestions/{category}`.
- `@PreAuthorize("hasRole('ADMIN')")`: Restricts access to the method to users with the ADMIN role.

## `addQuestion` Method

### Description
The `addQuestion` method handles the HTTP POST request for adding a new question by the ADMIN role.

### Parameters
- `questionDTO` (Request Body): The `QuestionDTO` containing the details of the new question. It should be a valid object.

### Return
- `ResponseEntity<Response>`: A `ResponseEntity` containing the response data and HTTP status code.
  - If the question is successfully added, it returns HTTP status code 201 (CREATED) along with the response data.
  - If there is an error during the addition process or the user does not have the required ADMIN role, it returns an appropriate HTTP status code along with the error details.

### Exceptions
- `QuizException`: If there is an unexpected error during the addition process.

### Annotations
- `@PostMapping("/addQuestion")`: Maps the method to handle the HTTP POST request for `/question/addQuestion`.
- `@PreAuthorize("hasRole('ADMIN')")`: Restricts access to the method to users with the ADMIN role.

## `deleteQuestion` Method

### Description
The `deleteQuestion` method handles the HTTP DELETE request for deleting a question by the ADMIN role.

### Parameters
- `questionId` (Path Variable): The ID of the question to be deleted.

### Return
- `ResponseEntity<Response>`: A `ResponseEntity` containing the response data and HTTP status code.
  - If the question is successfully deleted, it returns HTTP status code 200 (OK) along with the response data.
  - If there is an error during the delete process or the user does not have the required ADMIN role, it returns an appropriate HTTP status code along with the error details.

### Exceptions
- `QuizException`: If there is an unexpected error during the delete process.

### Annotations
- `@DeleteMapping("/deleteQuestion/{id}")`: Maps the method to handle the HTTP DELETE request for `/question/deleteQuestion/{id}`.
- `@PreAuthorize("hasRole('ADMIN')")`: Restricts access to the method to users with the ADMIN role.

## `updateQuestion` Method

### Description
The `updateQuestion` method handles the HTTP PUT request for updating a question by the ADMIN role.

### Parameters
- `questionDTO` (Request Body): The `QuestionDTO` containing the updated question details. It should be a valid object.

### Return
- `ResponseEntity<Response>`: A `ResponseEntity` containing the response data and HTTP status code.
  - If the question is successfully updated, it returns HTTP status code 200 (OK) along with the response data.
  - If there is an error during the update process or the user does not have the required ADMIN role, it returns an appropriate HTTP status code along with the error details.

### Exceptions
- `QuizException`: If there is an unexpected error during the update process.

### Annotations
- `@PutMapping("/updateQuestion")`: Maps the method to handle the HTTP PUT request for `/question/updateQuestion`.
- `@PreAuthorize("hasRole('ADMIN')")`: Restricts access to the method to users with the ADMIN role.

# QuizController Functionalities

## `createQuiz` Method

### Description
The `createQuiz` method handles the HTTP POST request for creating a new quiz by the ADMIN role.

### Parameters
- `category` (Request Parameter): The category of the new quiz. It should be a valid category string.
- `num` (Request Parameter): The number of questions to include in the quiz. It should be a valid positive integer.
- `title` (Request Parameter): The title or name of the new quiz. It should be a non-empty string.

### Return
- `ResponseEntity<Response>`: A `ResponseEntity` containing the response data and HTTP status code.
  - If the quiz is successfully created, it returns HTTP status code 201 (CREATED) along with the response data.
  - If there is an error during the creation process or the user does not have the required ADMIN role, it returns an appropriate HTTP status code along with the error details.

### Exceptions
- `QuizException`: If there is an unexpected error during the creation process.

### Annotations
- `@PostMapping("/create")`: Maps the method to handle the HTTP POST request for `/quiz/create`.
- `@PreAuthorize("hasRole('ADMIN')")`: Restricts access to the method to users with the ADMIN role.

## `getQuiz` Method

### Description
The `getQuiz` method handles the HTTP GET request for retrieving a quiz by its ID.

### Parameters
- `quizID` (Path Variable): The ID of the quiz to be retrieved. It should be a valid positive integer representing the quiz ID.

### Return
- `ResponseEntity<Response>`: A `ResponseEntity` containing the response data and HTTP status code.
  - If the quiz with the specified ID is found, it returns HTTP status code 200 (OK) along with the response data.
  - If the quiz with the given ID is not found, it returns an appropriate HTTP status code along with an error message.

### Exceptions
- `QuizException`: If there is an unexpected error during the retrieval process.

### Annotations
- `@GetMapping("/getQuiz/{quizID}")`: Maps the method to handle the HTTP GET request for `/quiz/getQuiz/{quizID}`.

## `submitAnswers` Method

### Description
The `submitAnswers` method handles the HTTP POST request for submitting quiz answers.

### Parameters
- `quizSubmitDTO` (Request Body): A list of `QuizSubmitDTO` containing the user's submitted quiz answers. It should be a valid list of quiz submission data.
- `quizID` (Path Variable): The ID of the quiz being submitted. It should be a valid positive integer representing the quiz ID.

### Return
- `ResponseEntity<Response>`: A `ResponseEntity` containing the response data and HTTP status code.
  - If the quiz answers are successfully submitted, it returns HTTP status code 200 (OK) along with the response data.
  - If there is an error during the submission process or the specified quiz ID is not valid, it returns an appropriate HTTP status code along with an error message.

### Exceptions
- `QuizException`: If there is an unexpected error during the submission process.

### Annotations
- `@PostMapping("/submit/{quizID}")`: Maps the method to handle the HTTP POST request for `/quiz/submit/{quizID}`.



# UserController Functionalities

## `updateUser` Method

### Description
The `updateUser` method handles the HTTP PUT request for updating a user by their ID.

### Parameters
- `request` (Request Body): The `RegisterRequestDTO` containing the updated user details. It should be a valid object.
- `userId` (Path Variable): The ID of the user to be updated. It should be a valid positive integer representing the user ID.

### Return
- `ResponseEntity<Response>`: A `ResponseEntity` containing the response data and HTTP status code.
  - If the user is successfully updated, it returns HTTP status code 200 (OK) along with the response data.
  - If the user with the specified ID is not found or there is an error during the update process, it returns an appropriate HTTP status code along with an error message.

### Exceptions
- `QuizException`: If there is an unexpected error during the update process.

### Annotations
- `@PutMapping("/update/{userId}")`: Maps the method to handle the HTTP PUT request for `/user/update/{userId}`.

## `getUser` Method

### Description
The `getUser` method handles the HTTP GET request for retrieving a user by their ID.

### Parameters
- `userId` (Path Variable): The ID of the user to be retrieved. It should be a valid positive integer representing the user ID.

### Return
- `ResponseEntity<Response>`: A `ResponseEntity` containing the response data and HTTP status code.
  - If the user with the specified ID is found, it returns HTTP status code 200 (OK) along with the response data.
  - If the user with the given ID is not found, it returns an appropriate HTTP status code along with an error message.

### Exceptions
- `QuizException`: If there is an unexpected error during the retrieval process.

### Annotations
- `@GetMapping("/get-user/{userId}")`: Maps the method to handle the HTTP GET request for `/user/get-user/{userId}`.

## `getAllUsers` Method

### Description
The `getAllUsers` method handles the HTTP GET request for retrieving all users by the ADMIN role.

### Return
- `ResponseEntity<Response>`: A `ResponseEntity` containing the response data and HTTP status code.
  - If the users are successfully retrieved, it returns HTTP status code 200 (OK) along with the response data.
  - If there is an error during the retrieval process or the user does not have the required ADMIN role, it returns an appropriate HTTP status code along with an error message.

### Exceptions
- `QuizException`: If there is an unexpected error during the retrieval process.

### Annotations
- `@PreAuthorize("hasRole('ADMIN')")`: Restricts access to the method to users with the ADMIN role.
- `@GetMapping("/get-all-user")`: Maps the method to handle the HTTP GET request for `/user/get-all-user`.

## `deleteUser` Method

### Description
The `deleteUser` method handles the HTTP DELETE request for deleting a user by the ADMIN role.

### Parameters
- `uId` (Path Variable): The ID of the user to be deleted. It should be a valid positive integer representing the user ID.

### Return
- `ResponseEntity<Response>`: A `ResponseEntity` containing the response data and HTTP status code.
  - If the user is successfully deleted, it returns HTTP status code 200 (OK) along with the response data.
  - If there is an error during the deletion process or the user does not have the required ADMIN role, it returns an appropriate HTTP status code along with an error message.

### Exceptions
- `QuizException`: If there is an unexpected error during the deletion process.

### Annotations
- `@PreAuthorize("hasRole('ADMIN')")`: Restricts access to the method to users with the ADMIN role.
- `@DeleteMapping("/delete-user/{userId}")`: Maps the method to handle the HTTP DELETE request for `/user/delete-user/{userId}`.


### How can I support the developer ?
    Star my Github repo ⭐
    Create pull requests, submit bugs, suggest new features or documentation updates 🛠

### Somethings wrong!!
    If you find that something's wrong with this package, you can let me know by raising an issue on the GitHub issue tracker



### Here is Collection Documentation of Postman

[Collection Documentation of Postman](https://documenter.getpostman.com/view/19629540/2s9XxsVGWk)

