package com.project.payload.mappers;

import com.project.entity.concretes.business.TourRequest;
import com.project.payload.request.business.tourRequestRequests.TourRequestCreateAndUpdateRequest;
import com.project.payload.response.business.TourRequestResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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
                .status(request.getStatus().id)
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
                .status(request.getStatus().id)
                .updateAt(request.getUpdateAt())
                //.advert(advertMapper.mapAdvertToAdvertResponseForTourRequest(tourRequest.getAdvert()))
                .guestUser(userMapper.mapUserToUserResponseForTourRequest(request.getGuestUser()))
                //.ownerUser(userMapper.toUserResponse(tourRequest.getOwnerUser()))
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
