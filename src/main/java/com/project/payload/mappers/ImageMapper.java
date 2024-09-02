package com.project.payload.mappers;

import com.project.entity.concretes.business.Images;
import com.project.payload.response.business.ImagesResponse;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Base64;

@Data
@Component
@RequiredArgsConstructor
public class ImageMapper {



    public ImagesResponse toImageResponse(Images savedImage) {
        return ImagesResponse.builder()
                .id(savedImage.getId())
                .name(savedImage.getName())
                .type(savedImage.getType())
                .featured(savedImage.isFeatured())
                .data(savedImage.getData())
                .build();
    }

    private String encodeImage(byte[] imageData) {
        return Base64.getEncoder().encodeToString(imageData);
    }
}


