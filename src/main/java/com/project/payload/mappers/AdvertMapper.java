package com.project.payload.mappers;

import com.project.entity.concretes.business.Advert;
import com.project.payload.request.business.AdvertRequest;
import com.project.payload.response.business.AdvertResponse;
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
                .desc(advertRequest.getDesc())
                .price(advertRequest.getPrice())
                .status(advertRequest.getStatus())
                .viewCount(advertRequest.getViewCount())
                .location(advertRequest.getLocation())
                .create_at(advertRequest.getCreate_at())
                .update_at(advertRequest.getUpdate_at())
                .build();
    }

    // POJO -> DTO

    public AdvertResponse mapAdvertToAdvertResponse(Advert advert){

        return AdvertResponse.builder()
                .advertId(advert.getAdvertId())
                .title(advert.getTitle())
                .desc(advert.getDesc())
               // .slug(advert.getSlug())
                .price(advert.getPrice())
                .status(advert.getStatus())
                .isActive(advert.getIsActive())
                .location(advert.getLocation())
                .create_at(advert.getCreate_at())
                .update_at(advert.getUpdate_at())
                .viewCount(advert.getViewCount())
                .advertType(advert.getAdvertType())
                .country(advert.getCountry())
                .city(advert.getCity())
                .district(advert.getDistrict())
                .category(advert.getCategory())
                .build();

    }

    // Update icin kullaniyoruz
    public Advert mapAdvertRequestToUpdatedAdvert(Long id, AdvertRequest advertRequest){
        return mapAdvertRequestToAdvert(advertRequest)
                .toBuilder()
                .advertId(id)
                .build();

    }




}
