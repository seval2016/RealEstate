package com.project.service.business;

import com.project.entity.concretes.business.Advert;
import com.project.exception.BadRequestException;
import com.project.payload.mappers.AdvertMapper;
import com.project.payload.messages.ErrorMessages;
import com.project.payload.messages.SuccessMessages;
import com.project.payload.request.business.AdvertRequest;
import com.project.payload.response.business.AdvertResponse;
import com.project.payload.response.business.ResponseMessage;
import com.project.repository.business.AdvertRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdvertService {

    private final AdvertRepository advertRepository;
    private final AdvertMapper advertMapper;

    public ResponseMessage<AdvertResponse> saveAdvert(AdvertRequest advertRequest) {

        Advert savedAdvert = advertRepository.save(advertMapper.mapAdvertRequestToAdvert(advertRequest));

        return ResponseMessage.<AdvertResponse>builder()
                .message((SuccessMessages.ADVERT_SAVED))
                .object()



    }

/*    private void validateAdvertDatesForRequest(AdvertRequest advertRequest){
        // Bu methodda request'ten gelen create date ve update date arasindaki uyumluluk kontrol ediliyor

        if(advertRequest.getUpdate_at().isBefore(advertRequest.getCreate_at())){

            throw new BadRequestException(ErrorMessages.ADVERT_UPDATE_DATE_IS_EARLIER_THAN_CREATE_DATE_ERROR);

        }*/


    }

}
