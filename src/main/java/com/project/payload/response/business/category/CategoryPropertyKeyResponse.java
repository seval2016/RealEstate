package com.project.payload.response.business.category;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.project.entity.concretes.business.CategoryPropertyKey;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CategoryPropertyKeyResponse {


    private Long category_id ;
    private Long id;

    private String name;

    private Boolean builtIn;

private String type;

    // CategoryPropertyKey nesnesini kullanarak response oluşturulması
   public CategoryPropertyKeyResponse(CategoryPropertyKey categoryPropertyKey) {
        this.id = categoryPropertyKey.getId();
        this.category_id = categoryPropertyKey.getCategory().getId();
    }



}
