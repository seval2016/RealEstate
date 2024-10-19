package com.project.payload.request.business;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AdvertRequest {

    @NotBlank(message = "Title cannot be blank")
    private String title;

    @NotBlank(message = "Slug cannot be blank")
    private String slug;

    @NotNull(message = "Price cannot be null")
    private BigDecimal price;

    @NotNull(message = "Status cannot be null")
    private int status;

    private String description;

    private Boolean builtIn;

    private Boolean isActive;

    private Integer viewCount;

    private String location;

    @NotNull(message = "Advert Type ID cannot be null")
    private Long advertTypeId;

    @NotNull(message = "Country ID cannot be null")
    private Long countryId;

    @NotNull(message = "City ID cannot be null")
    private Long cityId;

    @NotNull(message = "District ID cannot be null")
    private Long districtId;

    @NotNull(message = "User ID cannot be null")
    private Long userId;

    @NotNull(message = "Category ID cannot be null")
    private Long categoryId;

    private LocalDateTime createAt;

    private LocalDateTime updateAt;

    private List<String> properties;

    private List<Long> imageIds;  // Resim ID'leri listesi (optional)

    private Set<Long> favoriteIds; // Favori ID'leri listesi (optional)

    private List<Long> tourRequestIds; // Tur talep ID'leri listesi (optional)
}