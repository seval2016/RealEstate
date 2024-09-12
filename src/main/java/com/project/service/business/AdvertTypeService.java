package com.project.service.business;

import com.project.entity.concretes.business.AdvertType;
import com.project.exception.ResourceNotFoundException;
import com.project.payload.mappers.AdvertTypeMapper;
import com.project.payload.messages.ErrorMessages;
import com.project.payload.messages.SuccessMessages;
import com.project.payload.request.business.AdvertTypeRequest;
import com.project.payload.response.business.AdvertTypeResponse;
import com.project.payload.response.business.ResponseMessage;
import com.project.repository.business.AdvertTypeRepository;
import com.project.service.helper.PageableHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdvertTypeService {
    
    private final AdvertTypeRepository advertTypeRepository;
    private final AdvertTypeMapper advertTypeMapper;
    private final PageableHelper pageableHelper;

    public ResponseMessage<AdvertTypeResponse> saveAdvertType(AdvertTypeRequest advertTypeRequest) {
        AdvertType savedAdvertType = advertTypeRepository.save(advertTypeMapper.mapAdvertTypeRequestToAdvertType(advertTypeRequest));
        return ResponseMessage.<AdvertTypeResponse>builder()
                .message((SuccessMessages.ADVERT_TYPE_SAVED))
                .object(advertTypeMapper.mapAdvertTypeToAdvertTypeResponse(savedAdvertType))
                .httpStatus(HttpStatus.CREATED)
                .build();
    }

    public AdvertTypeResponse getAdvertTypeById(Long id) {
        AdvertType advertType = isAdvertTypeExist(id);
        return advertTypeMapper.mapAdvertTypeToAdvertTypeResponse(advertType);
    }

    private AdvertType isAdvertTypeExist(Long id) {
        return advertTypeRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException(String.format(ErrorMessages.ADVERT_TYPE_NOT_FOUND)));
    }
}
