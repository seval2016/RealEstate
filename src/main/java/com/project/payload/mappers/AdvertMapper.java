package com.project.payload.mappers;

import com.project.entity.concretes.business.Advert;
import com.project.payload.request.business.AdvertRequest;
import com.project.payload.response.business.AdvertResponse;
import com.project.payload.response.business.AdvertResponseForTourRequest;
import com.project.repository.business.AdvertRepository;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data  // Yazilmasa da olur
@Component
public class AdvertMapper {

    // DTO -> POJO
    public Advert mapAdvertRequestToAdvert(AdvertRequest advertRequest){
        return Advert.builder()
                .title(advertRequest.getTitle())
                .description(advertRequest.getDescription())
                .price(advertRequest.getPrice())
                .status(advertRequest.getStatus())
                .viewCount(advertRequest.getViewCount())
                .location(advertRequest.getLocation())
                .createdAt(advertRequest.getCreatedAt())
                .updatedAt(advertRequest.getUpdatedAt())
                .build();

    }

    // POJO -> DTO

    public AdvertResponse mapAdvertToAdvertResponse(Advert advert){

        return AdvertResponse.builder()
                .advertId(advert.getAdvertId())
                .title(advert.getTitle())
                .description(advert.getDescription())
                .slug(advert.getSlug())
                .price(advert.getPrice())
                .status(advert.getStatus())
                .isActive(advert.getIsActive())
                .location(advert.getLocation())
                .createdAt(advert.getCreatedAt())
                .updatedAt(advert.getUpdatedAt())
                .viewCount(advert.getViewCount())
/*                .advertType(advert.getAdvertType())
                .country(advert.getCountry())
                .city(advert.getCity())
                .district(advert.getDistrict())
                .category(advert.getCategory())*/
                .build();

    }

    // Update icin kullaniyoruz
    public Advert mapAdvertRequestToUpdatedAdvert(Long id, AdvertRequest advertRequest){
        return mapAdvertRequestToAdvert(advertRequest)
                .toBuilder()
                .advertId(id)
                .build();

    }


    //---------------------------------
    public AdvertResponseForTourRequest mapAdvertToAdvertResponseForTourRequest(Advert advert) {
        return AdvertResponseForTourRequest.builder()
                .id(advert.getAdvertId())
                .price(advert.getPrice())
                .title(advert.getTitle())
                .slug(advert.getSlug())
                .build();
    }
}