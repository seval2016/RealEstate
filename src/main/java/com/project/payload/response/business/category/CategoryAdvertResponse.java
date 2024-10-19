package com.project.payload.response.business.category;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class CategoryAdvertResponse {

    private String category; //İlanın bağlı olduğu kategori adını tutuyor.
    private long amount; //Bu kategoriye ait ilanların sayısını tutuyor.   //int den longa dönüştürüldü


}
