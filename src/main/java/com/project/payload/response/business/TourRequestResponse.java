package com.project.payload.response.business;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.project.entity.concretes.user.User;
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

    private LocalDate tour_date;

    private LocalTime tour_time;

    private Integer status;

    private LocalDateTime create_at;

    private LocalDateTime update_at;

    private Advert advert;

    private User ownerUser;

    private User guestUser;
}
