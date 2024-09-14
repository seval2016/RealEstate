package com.project.payload.response.business;



import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdvertResponseForTourRequest {

    private Long id;
    private String title;
    private Float price;
    private String slug;

}