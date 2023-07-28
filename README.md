
# Backend API's For Quiz Application

Welcome to our Quiz Application! This project is built using Spring Boot, Spring Data JPA, Hibernate, and PostgreSQL as the database.  The Spring Boot Quiz Application is an online quiz platform that allows users to participate in quizzes, view their scores, and manage quiz-related functionalities. The application aims to provide an intuitive and user-friendly interface for quiz enthusiasts to enjoy interactive quizzes on various topics & also interactive platform for both administrators and users to manage and participate in quizzes..

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
