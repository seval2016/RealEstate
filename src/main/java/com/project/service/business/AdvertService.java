package com.project.service.business;
import com.project.service.helper.PageableHelper;
import com.project.payload.mappers.AdvertMapper;
import com.project.payload.request.business.AdvertRequest;
import com.project.payload.response.business.AdvertResponse;
import com.project.payload.response.business.ResponseMessage;
import com.project.repository.business.AdvertRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
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