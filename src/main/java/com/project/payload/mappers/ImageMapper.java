package com.project.payload.mappers;

import com.project.entity.concretes.user.User;
import com.project.entity.image.Image;
import com.project.payload.response.UserResponse;
import com.project.payload.response.image.ImageResponse;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.stream.Collectors;

@Component
public class ImageMapper {

    public  ImageResponse mapImageToImageResponse(Image image) throws SQLException {
        if(image == null) return null;
        return ImageResponse.builder()
                .image(image.getData().getBytes((int) 0, (int) image.getData().length()))
                .build();
    }


}
