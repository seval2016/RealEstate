package com.project.payload.response.business;

import com.project.entity.concretes.business.*;
import com.project.entity.concretes.user.User;
import com.project.entity.enums.TourRequestStatus;
import com.project.payload.response.user.UserResponseForTourRequest;
import lombok.*;

import java.awt.*;
import java.awt.Image;
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
//    private UserResponseForTourRequest ownerUserId;
//    private UserResponseForTourRequest guestUserId;
    private UserResponseForTourRequest ownerUser;
    private UserResponseForTourRequest guestUser;
    /*private Advert advertId;
    private String advertTitle;
    private Image featuredImage;
    private District advertDistrict;
    private City advertCity;
    private Country advertCountry;
    */
    //private AdvertResponseForTourRequest advert;
    private AdvertResponse advert;
    private ImageResponse image;


}