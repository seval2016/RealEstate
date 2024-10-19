package com.project.payload.mappers;

import com.project.entity.concretes.business.Category;
import com.project.entity.concretes.business.CategoryPropertyKey;
import com.project.payload.request.business.CategoryRequest;
import com.project.payload.response.business.category.CategoryPropertyKeyResponse;
import com.project.payload.response.business.category.CategoryResponse;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;



@Data
@Component
public class CategoryMapper {
    // Category ====> DTO:

    public CategoryResponse mapCategoryToCategoryResponse(Category category){

        CategoryResponse.CategoryResponseBuilder builder = CategoryResponse.builder();
        builder.id(category.getId());
        builder.title(category.getTitle());
        builder.icon(category.getIcon());
        builder.builtIn(category.getBuiltIn());
        builder.seq(category.getSeq());
        builder.slug(category.getSlug());
        builder.isActive(category.getIsActive());
        builder.createAt(category.getCreateAt());
        builder.updateAt(category.getUpdateAt());
        return builder
                .build();
    }

    // DTO ====> Category(POJO) :

    public CategoryRequest mapCategoryRequestToCategory(CategoryRequest categoryRequest){
        CategoryRequest.CategoryRequestBuilder builderRequest = CategoryRequest.builder();

          builderRequest.seq(categoryRequest.getSeq());
          builderRequest.title(categoryRequest.getTitle());
          builderRequest.icon(categoryRequest.getIcon());
          builderRequest.slug(categoryRequest.getSlug());
          builderRequest.isActive(categoryRequest.getIsActive());
          builderRequest.createAt(LocalDateTime.now());
          builderRequest.updateAt(LocalDateTime.now());
          builderRequest.builtIn(false);
          return builderRequest.build();


    }


    //Property key pojo ---> Property key response
    public CategoryPropertyKeyResponse mapPropertyKeytoPropertyKeyResponse(CategoryPropertyKey categoryPropertyKey) {
        return CategoryPropertyKeyResponse.builder()
                .id(categoryPropertyKey.getId())
                .name(categoryPropertyKey.getName())
                .builtIn(false)
                .build();
    }
}
