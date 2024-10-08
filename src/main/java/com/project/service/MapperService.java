package com.project.service;

import com.project.entity.concretes.business.Advert;
import com.project.entity.concretes.business.TourRequest;
import com.project.payload.response.business.advert.AdvertResponse;
import com.project.payload.response.business.tourRequest.TourRequestResponse;
import org.hibernate.validator.internal.util.stereotypes.Lazy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


// MapperService interface
public interface MapperService {
    AdvertResponse mapAdvertToAdvertResponse(Advert advert);
    TourRequestResponse mapTourRequestToTourRequestResponse(TourRequest request);
}




