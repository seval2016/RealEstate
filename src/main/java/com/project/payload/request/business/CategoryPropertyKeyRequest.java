package com.project.payload.request.business;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class CategoryPropertyKeyRequest {
    @NotBlank(message = "Name cannot be blank")
    @Size(max = 80, message = "Name length must be up to 80 characters")
    private String name;

    @NotBlank(message = "Type cannot be blank")
    private String type;

    @NotNull(message = "BuiltIn cannot be null")
    private Boolean builtIn;

    @NotNull(message = "Category ID cannot be null")
    private Long categoryId;
}
