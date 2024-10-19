package com.project.payload.mappers;

import com.project.entity.image.Images;
import com.project.payload.response.business.image.ImagesResponse;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Base64;

@Data
@Component
@RequiredArgsConstructor
public class ImageMapper {
    public ImagesResponse mapToImagesResponse(Images images) {
        if (images == null) return null;
        return (ImagesResponse) Images.builder()
                .id(images.getId())
                .name(images.getName())
                .type(images.getType())
                .featured(images.getFeatured())
                .data(encodeImage(images.getData()).getBytes())
                .advertId(images.getId())
                .build();
    }


    private String encodeImage(byte[] imageData) {
        return Base64.getEncoder().encodeToString(imageData);
    }



}


