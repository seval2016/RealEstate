package com.project.payload.response.business;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AdvertResponse {

    private Long advertId;
    private String title;
    private String desc;
    private Float price;
    private int status;
    private Boolean isActive;
    private String location;
    private LocalDateTime create_at;
    private LocalDateTime update_at;
    private int viewCount;
    private AdvertType advertType;
    private Country country;
    private City city;
    private District district;
    private Category category;

}
