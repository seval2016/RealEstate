package com.project.payload.response.business;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class CityResponse {

    private Long id;
    private String name;
    private Long CountryId;
    private long amount; // Bu alan sadece DTO'da yer alacak ve her şehir için reklam sayısını döndürecektir.
}
