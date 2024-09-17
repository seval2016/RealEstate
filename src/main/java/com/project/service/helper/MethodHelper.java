package com.project.service.helper;

import com.project.entity.concretes.user.User;
import com.project.entity.concretes.user.UserRole;
import com.project.entity.enums.RoleType;
import com.project.exception.BadRequestException;
import com.project.exception.ResourceNotFoundException;
import com.project.payload.messages.ErrorMessages;
import com.project.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class MethodHelper {

    private final UserRepository userRepository;

    //!!! isUserExist
    public User isUserExist(Long userId) {
        return userRepository.findById(userId).orElseThrow(() ->
                new ResourceNotFoundException(String.format(ErrorMessages.NOT_FOUND_USER_MESSAGE, userId)));
    }

    //!!! builtIn kontrolu
    public void checkBuiltIn(User user) {
        if (Boolean.TRUE.equals(user.getBuiltIn())) {
            throw new BadRequestException(ErrorMessages.NOT_PERMITTED_METHOD_MESSAGE);
        }
    }

    //!!! Rol kontrolu yapan metod
    public void checkRole(User user, RoleType role) {
        if (!user.getUserRole()
                .stream()
                .anyMatch(userRole -> userRole.getRole().equals(role))) {

            throw new ResourceNotFoundException(
                    String.format(ErrorMessages.NOT_FOUND_USER_WITH_ROLE_MESSAGE, user.getId(), role));
        }
    }

    //!!! username ile kontrol
    public User isUserExistByUsername(String username) {
        User user = userRepository.findByUsernameEquals(username);

        if (user.getId() == null) {
            throw new ResourceNotFoundException(ErrorMessages.NOT_FOUND_USER_MESSAGE);
        }

        return user;
    }

    public void checkRoles(User user, RoleType... roleTypes) {

        Set<RoleType> roles = new HashSet<>();
        Collections.addAll(roles, roleTypes);

        for (UserRole userRole : user.getUserRole()) {
            if (roles.contains(userRole.getRole())) return;
        }
        throw new ResourceNotFoundException(ErrorMessages.ROLE_NOT_FOUND);
    }

    public User getUserByHttpRequest(HttpServletRequest request) {
        return findByUserByEmail(getEmailByRequest(request));
    }

    public User findByUserByEmail(String email) {
        if (email == null || email.isEmpty()) {
            throw new ResourceNotFoundException("Email can not be null or empty");
        }
        return userRepository.findByEmail(email).orElseThrow(() ->
                new ResourceNotFoundException("User not found with email: " + email));
    }

    public String getEmailByRequest(HttpServletRequest request) {
        return (String) request.getAttribute("email");
    }
}
