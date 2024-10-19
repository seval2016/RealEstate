package com.project.payload.request.business;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class CategoryRequest {

    @NotBlank(message = "Title cannot be blank")
    @Size(max = 150, message = "Title max length is 150 characters")
    private String title;

     // @NotBlank(message = "Icon cannot be blank")
    @Size(max = 50, message = "Icon max length is 50 characters")
    private String icon;

    @NotNull(message = "Built-in flag cannot be null")
    private Boolean builtIn;

    @NotNull(message = "Sequence number cannot be null")
    private int seq;

    @NotBlank(message = "Slug cannot be blank")
    @Size(max = 200, message = "Slug max length is 200 characters")
    private String slug;

    @NotNull(message = "IsActive flag cannot be null")
    private Boolean isActive;

    @NotNull(message = "CreateAt cannot be null")
    private LocalDateTime createAt;

    private LocalDateTime updateAt;



    private Set<CategoryPropertyKeyRequest> categoryPropertyKeys;





}
