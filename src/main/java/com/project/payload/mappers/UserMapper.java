package com.project.payload.mappers;

import com.project.entity.concretes.user.User;
import com.project.payload.response.UserResponse;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserResponse mapUserToUserResponse(User user) {
        return null;
    }
}