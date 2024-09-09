package com.project.controller.image;



import com.project.payload.response.business.ResponseMessage;
import com.project.payload.response.image.ImageResponse;
import com.project.service.image.ImageService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/images")
@RequiredArgsConstructor
public class ImagesController {

    private final ImageService imageService;



    @GetMapping("/{imageId}") // http://localhost:8080/users/getImagesById + GET
    public ResponseMessage<ImageResponse> getImagesById(@PathVariable Long imageId){
        return imageService.getImageById(imageId);
    }




}
