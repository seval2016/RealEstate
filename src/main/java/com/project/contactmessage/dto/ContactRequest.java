package com.project.contactmessage.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class ContactRequest {

    @NotNull(message = "Please enter your first name")
    @Size(max = 30, message = "First name should not exceed 30 characters")
    private String firstName;

    @Size(max = 30, message = "Last name should not exceed 30 characters")
    private String lastName;

    @Email(message = "Please enter a valid email address")
    @NotNull(message = "Please enter your email")
    @Size(max = 60, message = "Email should not exceed 60 characters")
    private String email;

    @NotNull(message = "Please enter a message")
    @Size(max = 300, message = "Message should not exceed 300 characters")
    private String message;
}