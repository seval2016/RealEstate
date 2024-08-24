package com.project.controller.business;

import com.project.payload.request.business.AdvertRequest;
import com.project.payload.response.business.AdvertResponse;
import com.project.payload.response.business.ResponseMessage;
import com.project.service.business.AdvertService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.access.prepost.PreAuthorize;

import javax.validation.Valid;

@RestController
@RequestMapping("/adverts")
@RequiredArgsConstructor
public class AdvertController {

    private final AdvertService advertService;


    @PostMapping("/save")  // http://localhost:8080/adverts/save + POST + JSON
    @PreAuthorize("hasAnyAuthority('ADMIN','CUSTOMER','MANAGER')")
    public ResponseMessage<AdvertResponse> saveAdvert(@RequestBody @Valid AdvertRequest advertRequest){

    return advertService.saveAdvert(advertRequest);

    }


}
