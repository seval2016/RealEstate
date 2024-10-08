package com.project.service;

import com.project.entity.concretes.business.Advert;
import com.project.entity.concretes.business.TourRequest;
import com.project.payload.response.business.advert.AdvertResponse;
import com.project.payload.response.business.tourRequest.TourRequestResponse;
import org.springframework.stereotype.Component;

// TourRequestMapper
@Component
// Eğer gerekliyse @Lazy anotasyonu eklenebilir
public class TourRequestMapperService  implements MapperService{
    @Override
    public AdvertResponse mapAdvertToAdvertResponse(Advert advert) {
        return null;
    }

    // MapperService interface'indeki metodları implemente eder
    public TourRequestResponse mapTourRequestToTourRequestResponse(TourRequest request) {
        // TourRequest nesnesini TourRequestResponse nesnesine dönüştürme işlemleri
        TourRequestResponse response = new TourRequestResponse();
        // ...
        return response;
    }
}
