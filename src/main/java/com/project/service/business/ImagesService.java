package com.project.service.business;

import java.io.IOException;


import com.project.entity.concretes.business.Images;
import com.project.exception.ResourceNotFoundException;
import com.project.payload.messages.ErrorMessages;
import com.project.payload.response.business.ImagesResponse;
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
            try {
                Images image = new Images();

                image.setData(file.getBytes());
                image.setName(file.getOriginalFilename());
                image.setType(file.getContentType());


                if (isFirstImage) {
                    image.setFeatured(true);
                    isFirstImage = false;
                } else {
                    image.setFeatured(false);
                }

                Images savedImage = imagesRepository.save(image);

                // ImageResponse objesi yarat (Base64 dönüşümü frontend'de yapılabilir)
                ImagesResponse response = ImagesResponse.builder()
                        .id(savedImage.getId())
                        .name(savedImage.getName())
                        .type(savedImage.getType())
                        .featured(savedImage.isFeatured())
                        .advertId(advertId)
                        .build();

                imageResponses.add(response);

            } catch (IOException e) {
                // Daha spesifik hata mesajı
                throw new RuntimeException("Görüntü yükleme sırasında bir hata oluştu: " + e.getMessage(), e);
            }
        }
        return imageResponses;
    }


    public void deleteImages(List<Long> ids) {


        List<Images> images = imagesRepository.findAllById(ids);

        for (Images image:images) {
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

    public List<Images> getALlImages(){

        return imagesRepository.findAll();
    }

    public byte[] updateFeaturedOfImage(Long imageId) {

        Images image = imagesRepository.findById(imageId).orElseThrow(()->
                new ResourceNotFoundException(ErrorMessages.IMAGE_NOT_FOUND));

        List<Images> images = imagesRepository.findByAdvertId(image.getAdvert().getId());

        images.forEach(item -> item.setFeatured(false));

        image.setFeatured(true);

        imagesRepository.save(image);

        return image.getData();
    }

    public void resetImageTables() {
        imagesRepository.deleteAll();
    }

}
