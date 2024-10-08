package com.project.payload.response.business.advert;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.project.payload.response.business.image.ImagesResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AdvertResponse {

    private Long id;
    private String title;
    private String slug;
    private Object price;
    private int status;
    private String description;
    private Boolean builtIn;
    private Boolean isActive;
    private Integer viewCount;
    private String location;
    private Long advertTypeId;
    private Long countryId;
    private Long cityId;
    private Long districtId;
    private Long userId;
    private Long categoryId;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;

    // Yeni eklenen alanlar

    // Properties alanı
    private Map<String, String> properties;

    // Featured Images alanı
    private ImagesResponse featuredImages;

    // Diğer alanlar
    private List<ImagesResponse> images;
    private int tourRequestCount;
    private int favoritesCount;

}