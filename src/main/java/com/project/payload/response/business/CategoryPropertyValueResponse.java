package com.project.payload.response.business;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.project.entity.concretes.business.CategoryPropertyValue;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CategoryPropertyValueResponse {

    private Long id;

    private String value;

    private Long advertId;

    private Long categoryPropertyKeyId;

    public CategoryPropertyValueResponse(CategoryPropertyValue updatedValue) {
    }//????? neden var
}
