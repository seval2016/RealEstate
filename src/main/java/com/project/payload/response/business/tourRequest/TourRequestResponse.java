package com.project.payload.response.business.tourRequest;

import com.project.entity.concretes.business.*;
import com.project.entity.concretes.user.User;
import com.project.entity.image.Images;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class TourRequestResponse {
    private Long id;
    private LocalDate tourDate;
    private LocalTime tourTime;
    private int status;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private User ownerUser;
    private User guestUser;
    private Advert advert;
    private String advertTitle;
    private District advertDistrict;
    private City advertCity;
    private Country advertCountry;
    private Images images ;


}
