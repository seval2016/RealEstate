package com.project.payload.response.CategoryResponse;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.project.payload.response.CategoryResponse.CategoryPropertyKeyResponse;
import com.project.entity.Category.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CategoryResponse {

    private Long id;
    private String title;
    private String icon;
    private Boolean builtIn;
    private int seq;
    private String slug;
    private Boolean isActive;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private Set<CategoryPropertyKeyResponse> categoryPropertyKeys;

    // Category entity'sinden CategoryResponse'ye dönüştürme constructor'ı
    public CategoryResponse(Category category) {
        this.id = category.getId();
        this.title = category.getTitle();
        this.icon = category.getIcon();
        this.builtIn = category.getBuiltIn();
        this.seq = category.getSeq();
        this.slug = category.getSlug();
        this.isActive = category.getIsActive();
        this.createAt = category.getCreateAt();
        this.updateAt = category.getUpdateAt();
        // this.categoryPropertyKeys = category.getCategoryPropertyKey()
        //     .stream()
        //     .map(CategoryPropertyKeyResponse::new)
//         .collect(Collectors.toSet());
    }
}