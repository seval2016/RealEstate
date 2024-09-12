package com.project.payload.mappers;

import com.project.entity.concretes.business.TourRequest;
import com.project.payload.request.business.TourRequestCreateAndUpdateRequest;
import com.project.payload.response.business.TourRequestResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class TourRequestMapper {

    private final UserMapper userMapper;
    private final AdvertMapper advertMapper;

    public TourRequestResponse tourRequestToTourRequestResponseForGuestUser(TourRequest request) {
        return TourRequestResponse.builder()
                .id(request.getId())
                .tourDate(request.getTourDate())
                .tourTime(request.getTourTime())
                .status(request.getStatusStatus())
                .updateAt(request.getUpdateAt())
                //.advert(advertMapper.advertToAdvertResponseForTourRequest(tourRequest.getAdvert()))
                //.ownerUser(userMapper.userToUserResponseForTourRequest(tourRequest.getOwnerUser()))

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
                .guestUserId(userMapper.mapUserToUserResponseForTourRequest(request.getGuestUser()))
                // Map the owner user correctly
                .ownerUserId(userMapper.mapUserToUserResponseForTourRequest(request.getOwnerUser()))
                //.advert(advertMapper.mapAdvertToAdvertResponseForTourRequest(request.getAdvert()))
                .build();
    }




    public TourRequest createTourRequestRequestToTourRequest(TourRequestCreateAndUpdateRequest request){
        return TourRequest.builder()
                .tourDate(request.getTourDate())
                .tourTime(request.getTourTime())
                .build();
    }

    public TourRequest TourRequestUpdateRequestToTourRequest(TourRequest tourRequest, TourRequestCreateAndUpdateRequest request) {
        return tourRequest.toBuilder()
                .tourDate(request.getTourDate())
                .tourTime(request.getTourTime())
                .updateAt(LocalDateTime.now())
                .build();
    }


}