package com.project.payload.response.business.category;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.project.entity.concretes.business.CategoryPropertyValue ;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@JsonInclude(JsonInclude.Include.NON_NULL)

public class CategoryPropertyValueResponse   {
    private Long id;

    private String value;

    private Long advertId; // Advert entity's id

    private Long categoryPropertyKeyId; // CategoryPropertyKey entity's i

    public CategoryPropertyValueResponse(CategoryPropertyValue updatedValue) {
    }
    private Long categoryPropertyKey ;

}
