package com.project.payload.mappers;

import com.project.entity.concretes.business.Advert;
import com.project.entity.concretes.business.AdvertType;
import com.project.payload.request.business.AdvertRequest;
import com.project.payload.request.business.AdvertTypeRequest;
import com.project.payload.response.business.AdvertTypeResponse;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class AdvertTypeMapper {

    public AdvertType mapAdvertTypeRequestToAdvertType(AdvertTypeRequest advertTypeRequest) {
        return AdvertType.builder()
                .title(advertTypeRequest.getTitle())
                .builtIn(advertTypeRequest.getBuiltIn())
                .build();

    }

    public AdvertTypeResponse mapAdvertTypeToAdvertTypeResponse(AdvertType advertType){
        return AdvertTypeResponse.builder()
                .id(advertType.getId())
                .title(advertType.getTitle())
                .build();
    }

    public AdvertType mapAdvertTypeRequestToUpdatedAdvertType(Long id, AdvertTypeRequest advertRequest){
        return mapAdvertTypeRequestToAdvertType(advertRequest)
                .toBuilder()
                .id(id)
                .build();

    }


}
