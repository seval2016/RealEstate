package com.project.payload.response.business.advert;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CategoryAdvertResponse { //A03 - /adverts/categories

    @JsonProperty("categoryName")
    private String categoryName;  // Sorgudaki categoryName ile uyumlu hale getirildi

    @JsonProperty("amount")
    private Long advertCount; //advertCount alanı JSON yanıtında "amount" olarak serileştirir.


}
