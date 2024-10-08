package com.project.payload.response.business.advert;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class CityAdvertTotalResponse {

    private String city;
    private int advertsTotal;
}