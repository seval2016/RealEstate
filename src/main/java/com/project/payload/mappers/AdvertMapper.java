package com.project.payload.mappers;


import com.project.entity.concretes.business.*;
import com.project.entity.concretes.user.User;
import com.project.entity.enums.AdvertStatus;
import com.project.entity.image.Images;
import com.project.payload.request.business.AdvertRequest;

import com.project.payload.response.business.advert.*;
import com.project.payload.response.business.category.CategoryAdvertResponse;
import com.project.payload.response.business.category.PropertyValueResponse;

import com.project.payload.response.business.image.ImagesResponse;
import com.project.payload.response.business.tourRequest.TourRequestResponseForSlug;
import com.project.service.MapperService;
import com.project.service.helper.AdvertHelper;
import com.project.service.helper.MethodHelper;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Data
@Component("AdvertMapperPayload")
public  class AdvertMapper  {

    //  private TourRequestMapper tourRequestMapper;
    private MethodHelper methodHelper;
    private  ImageMapper imagesMapper;
    private  CategoryPropertyValueMapper categoryPropertyValueMapper;
    private AdvertHelper advertHelper;

    private  AdvertMapper advertMapper;



public void setAdvertMapper (AdvertMapper advertMapper) {
    this.advertMapper = advertMapper;
}



    public void setCategoryPropertyValueMapper (CategoryPropertyValueMapper categoryPropertyValueMapper) {
        this.categoryPropertyValueMapper = categoryPropertyValueMapper;
    }



    public void serImageMapper (ImageMapper imageMapper) {
        this.imagesMapper = imageMapper;
    }


    @Autowired
    public void setMethodHelper (MethodHelper methodHelper) {
        this.methodHelper= methodHelper;
    }


  /*  @Autowired
    public void setTourRequestMapper(TourRequestMapper tourRequestMapper) {
        this.tourRequestMapper = tourRequestMapper;
    }*/


    @Autowired
    public void setAdvertHelper(AdvertHelper advertHelper) {
        this.advertHelper = advertHelper;
    }









    public Advert mapAdvertRequestToAdvert(AdvertRequest advertRequest, Category category, City city, User user, Country country, AdvertType advertType, District district) {
        return Advert.builder()
                .title(advertRequest.getTitle())
                .description(advertRequest.getDescription())
                .price(advertRequest.getPrice())
                .location(advertRequest.getLocation())
                .viewCount(0)
                .status(AdvertStatus.PENDING.getValue())
                .category(category)
                .country(country)
                .city(city)
                .user(user)
                .advertType(advertType)
                .district(district)

                .build();

    }

    private String generateSlug(String title) {
        return title.toLowerCase().replaceAll(" ", "-");
    }


    public AdvertDetailsForSlugResponse mapAdvertToAdvertResponseForSlug(Advert advert) {
        AdvertDetailsForSlugResponse build = AdvertDetailsForSlugResponse.builder()
                .id(advert.getId())
                .title(advert.getTitle())
                .properties(advert.getCategoryPropertyValuesList().stream()
                        .map(propertyValue -> new PropertyValueResponse(
                                propertyValue.getCategoryPropertyKey(),
                                propertyValue.getValue()
                        ))
                        .collect(Collectors.toList()))
                .images(advert.getImages().stream()
                        .map(image -> new ImagesResponse(
                                image.getId(),
                                image.getName(),
                                image.getType(),
                                image.getFeatured()
                        ))
                        .collect(Collectors.toList()))
                .tourRequests(advert.getTourRequestList().stream()
                        .map(tourRequestForSlug -> new TourRequestResponseForSlug(
                                tourRequestForSlug.getId(),
                                tourRequestForSlug.getTourDate(),
                                tourRequestForSlug.getTourTime()
                        ))
                        .collect(Collectors.toList())
                )
                .build();
        return build;
    }

    public AdvertResponse mapAdvertToAdvertResponseForAll(Advert advert) {
        if (advert == null) {
            throw new IllegalArgumentException("Advert cannot be null");
        }

        List<ImagesResponse> imagesResponseList = advert.getImages().stream()
                .map(imagesMapper::mapToImagesResponse)
                .collect(Collectors.toList());

        Long cityId = Optional.ofNullable(advert.getCity()).map(City::getId).orElse(null);
        Long districtId = Optional.ofNullable(advert.getDistrict()).map(District::getId).orElse(null);
        Long advertTypeId = Optional.ofNullable(advert.getAdvertType()).map(AdvertType::getId).orElse(null);
        Long categoryId = Optional.ofNullable(advert.getCategory()).map(Category::getId).orElse(null);

        int favoritesCount = Optional.ofNullable(advert.getFavoritesList()).map(List::size).orElse(0);
        int tourRequestCount = Optional.ofNullable(advert.getTourRequestList()).map(List::size).orElse(0);

        return AdvertResponse.builder()
                .id(advert.getId())
                .price(advert.getPrice())
                .builtIn(advert.getBuiltIn())
                .isActive(advert.getIsActive())
                .description(advert.getDescription())
                .title(advert.getTitle())
                .status(advertHelper.updateAdvertStatus(advert.getStatus(), advert))
                .cityId(cityId)
                .districtId(districtId)
                .createAt(advert.getCreateAt())
                .advertTypeId(advertTypeId)
                .categoryId(categoryId)
                .featuredImages(advertHelper.getFeaturedImages(advert.getImages()))
                .images(imagesResponseList)
                .favoritesCount(favoritesCount)
                .tourRequestCount(tourRequestCount)
                .build();
    }


    public Advert mapAdvertRequestToUpdateAdvert(Long id, AdvertRequest advertRequest, Category category, City city, Country country, AdvertType advertType, District district, User user) {
        return Advert.builder()
                .id(id)
                .user(user)
                .country(country)
                .builtIn(false)
                .viewCount(0)
                .city(city)
                .advertType(advertType)
                .price(advertRequest.getPrice())
                .title(advertRequest.getTitle())
                .description(advertRequest.getDescription())
                .district(district)
                .status(AdvertStatus.PENDING.getValue())
                .category(category)
                .location(advertRequest.getLocation())
                .slug(generateSlug(advertRequest.getTitle()))  // Slug alanı eklenmeli
                .build();
    }

    //For Category POJO==>DTO


    public CategoryAdvertResponse mapCategoryToCategoryForAdvertResponse(Category category) {
        return CategoryAdvertResponse.builder()
                .category(category.getTitle())
                .amount(category.getAdverts().size())
                .build();
    }


    //****************************

    public AdvertListResponse toAdvertListResponse(Advert advert) {
        return AdvertListResponse.builder()
                .id(advert.getId())
                .title(advert.getTitle())
                //.featuredImage(getFeaturedImage(advert))
                .build();
    }


    public AdvertResponseForUser mapAdvertToAdvertResponseForUser(Advert advert){ //A08

        // Dinamik özellikleri saklamak için bir harita oluştur
        Map<String, Object> properties = new HashMap<>();

        // Advert'ın özelliklerini dinamik olarak haritaya ekle
        for (CategoryPropertyValue cpv : advert.getCategoryPropertyValuesList()) {
            properties.put(cpv.getCategoryPropertyKey().getName(), cpv.getValue());
        }

        // Resim ve tur taleplerini hazırlama
        List<String> imageUrls = advert.getImages().stream()
                .map(Images::getUrl) // Resim URL'lerini al
                .collect(Collectors.toList());

        List<String> tourRequests = advert.getTourRequestList().stream()
                .map(tourRequest -> String.format("Date: %s, Time: %s, Status: %s",
                        tourRequest.getTourDate(), tourRequest.getTourTime(), tourRequest.getStatus()))
                .collect(Collectors.toList());

        return AdvertResponseForUser.builder()
                .id(advert.getId())
                .userId(advert.getUser().getId())
                .title(advert.getTitle())
                .description(advert.getDescription())
                .slug(advert.getSlug())
                .price(advert.getPrice())
                .status(advert.getStatus())
                .builtIn(advert.getBuiltIn())
                .isActive(advert.getIsActive())
                .viewCount(advert.getViewCount())
                .location(advert.getLocation())
                .createAt(advert.getCreateAt())
                .updateAt(advert.getUpdateAt())
                .properties(properties) // Dinamik özellikleri ekle
                .images(imageUrls) // Resim URL'lerini ekle
                .tourRequests(tourRequests) // Tur taleplerini ekle
                .build();
    }



    public Advert mapAdvertToAdvertResponseForTourRequest(Advert advert) {
        if (advert == null) {
            throw new IllegalArgumentException("Advert cannot be null");
        }

        // Resim URL'lerini al
        List<String> imageUrls = advert.getImages().stream()
                .map(Images::getUrl) // Resim URL'lerini al
                .collect(Collectors.toList());

        // Tour taleplerini hazırlama
        List<String> tourRequests = advert.getTourRequestList().stream()
                .map(tourRequest -> String.format("Date: %s, Time: %s, Status: %s",
                        tourRequest.getTourDate(), tourRequest.getTourTime(), tourRequest.getStatus()))
                .collect(Collectors.toList());

        // Advert yanıt nesnesini oluştur
        return AdvertResponseForTourRequest.builder()
                .id(advert.getId())
                .title(advert.getTitle())
                .description(advert.getDescription())
                .location(advert.getLocation())
                .updateAt(advert.getUpdateAt())
                .price(advert.getPrice())
                .build();
    }







}

