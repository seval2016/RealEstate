package com.project.payload.request.business;

import com.project.entity.concretes.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data

@AllArgsConstructor
@NoArgsConstructor
public class UserResponseForTourRequest extends User {
    private Long id;
    private String name;
    private String email;



}
