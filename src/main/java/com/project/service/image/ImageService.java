package com.project.service.image;


import com.project.entity.concretes.user.User;
import com.project.entity.enums.Role;
import com.project.entity.image.Image;
import com.project.exception.ResourceNotFoundException;
import com.project.payload.mappers.ImageMapper;
import com.project.payload.mappers.UserMapper;
import com.project.payload.messages.ErrorMessages;
import com.project.payload.messages.SuccessMessages;
import com.project.payload.response.abstracts.BaseUserResponse;
import com.project.payload.response.business.ResponseMessage;
import com.project.payload.response.image.ImageResponse;
import com.project.repository.image.ImageRepository;
import com.project.repository.user.UserRepository;
import com.project.service.validator.UniquePropertyValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final ImageRepository imageRepository;
    private final ImageMapper imageMapper;
    public ResponseMessage<ImageResponse> getImageById(Long imageId) {

        ImageResponse baseImageResponse = null;

        Image image = imageRepository.findById(imageId).orElseThrow(()->
                new ResourceNotFoundException(String.format(ErrorMessages.NOT_FOUND_IMAGE_MESSAGE, imageId)));
           try{
               baseImageResponse = imageMapper.mapImageToImageResponse(image);
        }catch ( SQLException e){
               new ResourceNotFoundException(String.format(ErrorMessages.NOT_FOUND_IMAGE_MESSAGE, imageId));
           }


        return ResponseMessage.<ImageResponse>builder()
                .message(SuccessMessages.IMAGE_FOUND)
                .httpStatus(HttpStatus.OK)
                .object(baseImageResponse)
                .build();
    }




}
