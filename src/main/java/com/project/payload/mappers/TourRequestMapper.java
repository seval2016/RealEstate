package com.project.payload.mappers;

import com.project.entity.concretes.business.TourRequest;
import com.project.payload.request.business.tourRequestRequests.TourRequestCreateAndUpdateRequest;
import com.project.payload.response.business.TourRequestResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TourRequestMapper {

    public TourRequestResponse mapTourRequestToResponse(TourRequest request){
        return TourRequestResponse.builder()
                .id(request.getId())
                .tour_date(request.getTourDate())
                .tour_time(request.getTourTime())
                .status(request.getStatus().id)
                .update_at(request.getUpdateAt())
                //.advert(request.getAdvert())
                .ownerUser(request.getOwnerUser())
                .guestUser(request.getGuestUser())
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
