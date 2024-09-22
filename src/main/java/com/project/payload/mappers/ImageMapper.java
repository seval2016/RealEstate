package com.project.payload.mappers;

import com.project.entity.concretes.business.Image;

import com.project.payload.response.business.image.ImageResponse;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Base64;

@Data
@Component
@RequiredArgsConstructor
public class ImageMapper {
    public ImageResponse mapToImageResponse(Image image) {
        if (image == null) return null;
        return ImageResponse.builder()
                .id(image.getId())
                .name(image.getName())
                .type(image.getType())
                .featured(image.getFeatured())
                .data(encodeImage(image.getData()).getBytes())
                .advertId(image.getAdvert().getId())
                .build();
    }
    private String encodeImage(byte[] imageData) {
        return Base64.getEncoder().encodeToString(imageData);
    }
}


