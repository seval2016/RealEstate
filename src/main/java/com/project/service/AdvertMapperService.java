package com.project.service;

import com.project.entity.concretes.business.Advert;
import com.project.entity.concretes.business.TourRequest;
import com.project.payload.response.business.advert.AdvertResponse;
import com.project.payload.response.business.tourRequest.TourRequestResponse;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

// AdvertMapper
@Component("AdvertMapperService")
@Service
public class AdvertMapperService implements MapperService{
    // MapperService interface'indeki metodları implemente eder
    public AdvertResponse mapAdvertToAdvertResponse(Advert advert) {
        // Advert nesnesini AdvertResponse nesnesine dönüştürme işlemleri
        AdvertResponse response = new AdvertResponse();
        // ...
        return response;
    }

    @Override
    public TourRequestResponse mapTourRequestToTourRequestResponse(TourRequest request) {
        return null;
    }
}
