package com.project.payload.request.authentication;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UniqueElements;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {

  /* @NotNull(message = "Username must not be empty")
    private String username;*/


    @NotNull
    @Email
    private String email ;


    @NotNull(message = "Password must not be empty")
    private String password;


}