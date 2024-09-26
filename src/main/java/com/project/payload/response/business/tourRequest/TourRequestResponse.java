package com.project.payload.response.business.tourRequest;

import com.project.entity.concretes.business.Advert;
import com.project.entity.concretes.business.City;
import com.project.entity.concretes.business.Country;
import com.project.entity.concretes.business.District;
import com.project.entity.concretes.user.User;
import com.project.entity.enums.Status;
import com.project.payload.response.image.ImageResponse;
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
    private ImageResponse featuredImage;
    private District advertDistrict;
    private City advertCity;
    private Country advertCountry;
    private ImageResponse image;

}
