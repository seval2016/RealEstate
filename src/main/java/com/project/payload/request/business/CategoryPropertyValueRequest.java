package com.project.payload.request.business;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder

public class CategoryPropertyValueRequest {

    @NotNull(message = "Value cannot be null")
    @Size(max = 100, message = "Max length is 100 characters")
    private String value;

    @NotNull(message = "Advert cannot be null")
    private Long advertId; // Advert entity's id

    @NotNull(message = "Category property key cannot be null")
    private Long categoryPropertyKeyId; // CategoryPropertyKey entity's id



}
