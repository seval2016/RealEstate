package com.project.payload.response.business;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class CityAdvertResponse {

    //Bu DTO sınıfı, city adı ve bu city'nin reklamlarının sayısını temsil eder.
    private String cityName;
    private long amount;
}
