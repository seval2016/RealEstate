package com.project.service.business;

import com.project.entity.concretes.business.Advert;
import com.project.exception.BadRequestException;
import com.project.exception.ResourceNotFoundException;
import com.project.payload.mappers.AdvertMapper;
import com.project.payload.messages.ErrorMessages;
import com.project.payload.messages.SuccessMessages;
import com.project.payload.request.business.AdvertRequest;
import com.project.payload.response.business.AdvertResponse;
import com.project.payload.response.business.ResponseMessage;
import com.project.repository.business.AdvertRepository;
import com.project.service.helper.PageableHelper;
import com.project.service.helper.Slugify;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.project.service.helper.Slugify.slugify;

@Service
@RequiredArgsConstructor
public class AdvertService {

    private final AdvertRepository advertRepository;
    private final AdvertMapper advertMapper;
    private final PageableHelper pageableHelper;
   // private final Slugify slugify;

    public ResponseMessage<AdvertResponse> saveAdvert(AdvertRequest advertRequest) {
        return null;
    }


    //************************************************************************************************
//    public AdvertResponse createAdvertSlug(AdvertRequest advertRequest) {
//        String slug = slugify(advert.getTitle());
//        advert.setSlug(slug);
//        return advertRepository.save(advert);
//    }



//************************************************************************************************

    public AdvertResponse getAdvertById(Long id) {
        return null;
    }

    public List<AdvertResponse> getAllAdverts() {
        return null;
    }

    public Page<AdvertResponse> getAllAdvertsByPage(int page, int size, String sort, String type) {
        return null;
    }

    public ResponseMessage<?> deleteAdvertById(Long id) {
        return null;
    }

    public ResponseMessage<AdvertResponse> updateAdvertById(Long id, AdvertRequest advertRequest) {
        return null;
    }



}