package com.project.service.business;

import com.project.entity.image.Images;
import com.project.exception.ResourceNotFoundException;
import com.project.payload.messages.ErrorMessages;
import com.project.payload.response.business.image.ImagesResponse;
import com.project.repository.business.ImagesRepository;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@NoArgsConstructor(force = true)

public class ImagesService {

    private final ImagesRepository imagesRepository;
    private final AdvertService advertService;
    private final String imageDirectory = "/path/to/image/directory"; // Doğru yolu ayarlayın

    @Autowired
    public ImagesService(AdvertService advertService, ImagesRepository imagesRepository) {
        ImagesRepository imagesRepository1;
        this.advertService = advertService;
        imagesRepository1 = null;
        imagesRepository1 = imagesRepository;
        this.imagesRepository = imagesRepository1;
    }

    public Optional<Images> getImageById(Long id) {
        return imagesRepository.findById(id);
    }

    public List<ImagesResponse> uploadImages(Long advertId, MultipartFile[] files) {
        List<ImagesResponse> imageResponses = new ArrayList<>();
        boolean isFirstImage = true;

        for (MultipartFile file : files) {
            try {
                Images image = new Images(); // `Image` değil, `Images` olmalı

                image.setName(file.getOriginalFilename());
                image.setType(file.getContentType());
                image.setData(file.getBytes()); // Dosya verilerini ayarla

                if (isFirstImage) {
                    image.setFeatured(true);
                    isFirstImage = false;
                } else {
                    image.setFeatured(false);
                }

                Images savedImage = imagesRepository.save(image);

                // ImageResponse objesi yarat
                ImagesResponse response = ImagesResponse.builder()
                        .id(savedImage.getId())
                        .name(savedImage.getName())
                        .type(savedImage.getType())
                        .featured(savedImage.getFeatured())
                        .advertId(advertId) // Advert ID'yi burada ayarlayın
                        .build();

                imageResponses.add(response);
            } catch (IOException e) {
                throw new RuntimeException("Error while uploading image: " + file.getOriginalFilename(), e);
            }
        }
        return imageResponses;
    }

    public void deleteImages(List<Long> ids) {
        List<Images> images = imagesRepository.findAllById(ids);

        for (Images image : images) {
            if (image == null) {
                throw new ResourceNotFoundException(ErrorMessages.IMAGE_NOT_FOUND);
            }

            Path imagePath = Paths.get(imageDirectory, image.getName());
            try {
                Files.deleteIfExists(imagePath);
            } catch (IOException e) {
                throw new RuntimeException("Failed to delete image file: " + image.getName(), e);
            }
        }

        imagesRepository.deleteAllById(ids);
    }

    public List<Images> getAllImages() {
        return imagesRepository.findAll();
    }

    public byte[] updateFeaturedOfImage(Long imageId) {
        Images image = imagesRepository.findById(imageId).orElseThrow(() ->
                new ResourceNotFoundException(ErrorMessages.IMAGE_NOT_FOUND));

        // Öncelikle aynı advert'e ait tüm resimleri güncelle
        List<Images> images = imagesRepository.findByAdvert_Id(image.getAdvert().getId());
        images.forEach(item -> item.setFeatured(false));

        // Ardından, güncellenen resmi öne çıkar
        image.setFeatured(true);
        imagesRepository.saveAll(images); // Tüm resimleri kaydet
        imagesRepository.save(image); // Güncellenen resmi kaydet

        return image.getData();
    }

    public void resetImageTables() {
        imagesRepository.deleteAll();
    }
}


































 /*package com.project.service.business;

import java.awt.*;
import java.io.IOException;


import com.project.entity.image.Images;
import com.project.exception.ResourceNotFoundException;
import com.project.payload.messages.ErrorMessages;
import com.project.payload.response.business.image.ImagesResponse;
import com.project.repository.business.ImagesRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@NoArgsConstructor
public class ImagesService {


    private ImagesRepository imagesRepository;
    private  AdvertService advertService;


    @Autowired
    public ImagesService(AdvertService advertService,ImagesRepository imagesRepository) {
        this.advertService = advertService;
        this.imagesRepository=imagesRepository;
    }


    private final String imageDirectory="/path/to/image/directory";

    public Optional<Images> getImageById(Long id) {
        return imagesRepository.findById(id);
    }





    public List<ImagesResponse> uploadImages(Long advertId, MultipartFile[] files) {


        List<ImagesResponse> imageResponses = new ArrayList<>();
        boolean isFirstImage = true;

        for (MultipartFile file : files) {
            Image image = new Images();


            image.setName(file.getOriginalFilename());
            image.setType(file.getContentType());


            if (isFirstImage) {
                image.setFeatured(true);
                isFirstImage = false;
            } else {
                image.setFeatured(false);
            }

            Image savedImage = imagesRepository.save(image);

            // ImageResponse objesi yarat (Base64 dönüşümü frontend'de yapılabilir)
            ImagesResponse response = ImagesResponse.builder()
                    .id(savedImage.getId())
                    .name(savedImage.getName())
                    .type(savedImage.getType())
                    .featured(savedImage.getFeatured())
                    .advertId(savedImage.getId())
                    .build();

            imageResponses.add(response);

        }
        return imageResponses;
    }


    public void deleteImages(List<Long> ids) {


        List<Image> images = imagesRepository.findAllById(ids);

        for (Image image:images) {
            if(image==null){
                throw new ResourceNotFoundException(ErrorMessages.IMAGE_NOT_FOUND);
            }

            Path imagePath = Paths.get(imageDirectory,image.getName());
            try {
                Files.deleteIfExists(imagePath);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        imagesRepository.deleteAllById(ids);
    }

    public List<Image> getALlImages(){

        return imagesRepository.findAll();
    }

    public byte[] updateFeaturedOfImage(Long imageId) {

        Image image = imagesRepository.findById(imageId).orElseThrow(()->
                new ResourceNotFoundException(ErrorMessages.IMAGE_NOT_FOUND));

        List<Image> images = imagesRepository.findByAdvertId(image.getAdvert().getId());

        images.forEach(item -> item.setFeatured(false));

        image.setFeatured(true);

        imagesRepository.save(image);

        return image.getData();
    }

    public void resetImageTables() {
        imagesRepository.deleteAll();
    }

}*/
