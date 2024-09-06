package com.project.payload.response.user;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseForTourRequest {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
}
