package com.project.contactmessage.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class ContactResponse {


    private String firstName;
    private String lastName;
    private String email;
    private String message;

    private LocalDateTime createAt;

}