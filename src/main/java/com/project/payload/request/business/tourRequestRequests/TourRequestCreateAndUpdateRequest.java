package com.project.payload.request.business.tourRequestRequests;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class TourRequestCreateAndUpdateRequest {

    @NotNull(message = "Tour Date must not be empty")
    private LocalDate tourDate;

    @NotNull(message = "Tour Time must not be empty")
    private LocalTime tourTime;

    //@NotNull
    //private Long advert;
}
