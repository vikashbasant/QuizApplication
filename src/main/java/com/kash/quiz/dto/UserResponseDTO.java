package com.kash.quiz.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.kash.quiz.dto.roledto.RoleDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Component
public class UserResponseDTO {
    private int userId;
    private String name;
    private String userEmail;

    private String userPassword;
    private String userAbout;

    private Set<RoleDTO> roles = new HashSet<>();



    @JsonIgnore
    public String getUserPassword() {
        return this.userPassword;
    }

    @JsonProperty
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

}
