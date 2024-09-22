package com.project.payload.mappers;

import com.project.entity.concretes.business.TourRequest;
import com.project.payload.request.business.TourRequestRequest;

import com.project.payload.response.business.tourRequest.TourRequestResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class TourRequestMapper {

    private final UserMapper userMapper;
    private final AdvertMapper advertMapper;
    private final ImageMapper imageMapper;

    public TourRequestResponse tourRequestToTourRequestResponseForGuestUser(TourRequest request) {
        return TourRequestResponse.builder()
                .id(request.getId())
                .tourDate(request.getTourDate())
                .tourTime(request.getTourTime())
                .status(request)
                .updateAt(request.getUpdateAt())
                .advert(advertMapper.mapAdvertToAdvertResponse(request.getAdvert()))
                .ownerUser(userMapper.mapUserToUserResponseForTourRequest(request.getOwnerUser()))

                .build();
    }

    public TourRequestResponse tourRequestToTourRequestResponse(TourRequest request) {
        return TourRequestResponse.builder()
                .id(request.getId())
                .tourDate(request.getTourDate())
                .tourTime(request.getTourTime())
                .status(request.getStatusStatus())
                .updateAt(request.getUpdateAt())
                // Map the guest user correctly
                .guestUser(userMapper.mapUserToUserResponseForTourRequest(request.getGuestUser()))
                // Map the owner user correctly
                .ownerUser(userMapper.mapUserToUserResponseForTourRequest(request.getOwnerUser()))
                //---Advert dahil ettim
                .advert(advertMapper.mapAdvertToAdvertResponse(request.getAdvert()))
                //---Image dahil ettim
                .image(imageMapper.mapToImageResponse(request.getAdvert().getFeatured()))
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


}