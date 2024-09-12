package com.project.payload.response.business;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class DistrictResponse {

    private Long id;
    private String name;
    private Long cityId;
}
