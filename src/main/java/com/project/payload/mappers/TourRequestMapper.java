package com.project.payload.mappers;

import com.project.entity.concretes.business.Advert;

import com.project.entity.concretes.business.TourRequest;
import com.project.entity.image.Images;
import com.project.payload.request.business.TourRequestRequest;

import com.project.payload.response.business.advert.AdvertResponse;
import com.project.payload.response.business.image.ImagesResponse;
import com.project.payload.response.business.tourRequest.TourRequestResponse;
import com.project.service.MapperService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;



@Component
@RequiredArgsConstructor
@Lazy
public  class TourRequestMapper  {

    private final UserMapper userMapper;
    private final AdvertMapper advertMapper;
    private final ImageMapper imageMapper;

    public TourRequestResponse tourRequestToTourRequestResponseForGuestUser(TourRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("TourRequest cannot be null");
        }

        AdvertResponse advertResponse = null;
        if (request.getAdvert() != null) {
            advertResponse = advertMapper.mapAdvertToAdvertResponseForAll(request.getAdvert());
        }

        return TourRequestResponse.builder()
                .id(request.getId())
                .tourDate(request.getTourDate())
                .tourTime(request.getTourTime())
                .status(request.getStatus().getTourStatusValue())
                .updateAt(request.getUpdateAt())
                .advert(request.getAdvert()) // AdvertResponse null olabilir
                .ownerUser(userMapper.mapUserToUserResponseForTourRequest(request.getOwnerUser()))
                .build();
    }


    public TourRequestResponse tourRequestToTourRequestResponse(TourRequest request) {
        // İmaj mapper'ı başlat
        ImageMapper imagesMapper = new ImageMapper(); // Uygun bir yapılandırma ile başlatın

        // Advert nesnesini al
        Advert advert = request.getAdvert();
        if (advert == null) {
            throw new IllegalArgumentException("Advert cannot be null");
        }

        // Resimleri dönüştür
        List<ImagesResponse> imagesResponseList = new ArrayList<>();
        if (advert.getImages() != null) {
            for (Images image : advert.getImages()) {
                imagesResponseList.add(imagesMapper.mapToImagesResponse(image));
            }
        }

        // TourRequestResponse döndür

        return TourRequestResponse.builder()
                .id(request.getId())
                .tourDate(request.getTourDate())
                .tourTime(request.getTourTime())
                .status(request.getStatus().getTourStatusValue())
                .updateAt(request.getUpdateAt())
                // Misafir kullanıcıyı doğru bir şekilde haritalıyoruz
                .guestUser(userMapper.mapUserToUserResponseForTourRequest(request.getGuestUser()))
                // Sahip kullanıcıyı doğru bir şekilde haritalıyoruz
                .ownerUser(userMapper.mapUserToUserResponseForTourRequest(request.getOwnerUser()))
                // AdvertResponse olarak dönen mapper metodunu kullanıyoruz
               .advert(advertMapper.mapAdvertToAdvertResponseForTourRequest(request.getAdvert()) )
                // Resim listesini ekliyoruz
             //  .images(imagesResponseList) // Düzeltme burada yapıldı
                .build();
    }





    public TourRequest createTourRequestRequestToTourRequest(TourRequestRequest request){
        return TourRequest.builder()
                .tourDate(request.getTourDate())
                .tourTime(request.getTourTime())
                .build();
    }

    public TourRequest updateTourRequestRequestToTourRequest(TourRequest tourRequest, TourRequestRequest request) {
        return tourRequest.toBuilder()
                .tourDate(request.getTourDate())
                .tourTime(request.getTourTime())
                .updateAt(LocalDateTime.now())
                .build();
    }


    public TourRequestResponse mapTourRequestToTourRequestResponse(TourRequest tourRequest) {
        return TourRequestResponse.builder()
                .id(tourRequest.getId())
                .tourDate(tourRequest.getTourDate())
                .advertTitle(tourRequest.getAdvert().getTitle())
                .build();
    }
}