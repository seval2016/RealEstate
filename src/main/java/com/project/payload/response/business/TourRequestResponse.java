package com.project.payload.response.business;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.project.entity.concretes.business.Advert;
import com.project.entity.concretes.user.User;
import com.project.payload.response.user.UserResponseForTourRequest;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TourRequestResponse {

    private Long id;
    private LocalDate tourDate;
    private LocalTime tourTime;
    private Integer status;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private AdvertResponseForTourRequest advert;
    private UserResponseForTourRequest ownerUser;
    private UserResponseForTourRequest guestUser;
}
