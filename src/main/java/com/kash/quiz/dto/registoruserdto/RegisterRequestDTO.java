package com.kash.quiz.dto.registoruserdto;



import com.kash.quiz.constant.RoleConstants;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class RegisterRequestDTO {
    private int userId;

    @NotNull(message = "Name can't be Null")
    @NotEmpty(message = "Name can't be Empty")
    @Size(min = 2, max = 20, message = "Character Of Name Must be Length of 2 to 20")
    @Pattern(regexp = "^[a-zA-Z_0-9.\\s]*$", message = "Please Enter Valid Name!")
    private String name;

    @NotNull(message = "Email can't be Null")
    @NotEmpty(message = "Email can't be Empty")
    @Email
    @Pattern(regexp = "^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,6}$", message = "Please Enter Valid Email!")
    @Column(unique = true, columnDefinition = "Please Enter Unique Email Id")
    private String userEmail;

    @NotNull(message = "password can't be Null")
    @NotEmpty(message = "password can't be Empty")
    @Size(min = 5, max = 15, message = "Password must be min of 5 characters and max of 15 characters")
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^=()])(?=\\S+$).{5,15}$")
    private String userPassword;

    @NotNull(message = "About can't be Null")
    @NotEmpty(message = "About can't be Empty")
    @Size(max = 500, message = "About maximum 500 character are allowed")
    private String userAbout;

    private String userRole = RoleConstants.NORMAL_USER_NAME;



}
