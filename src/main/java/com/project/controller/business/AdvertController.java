package com.project.controller.business;

import com.project.payload.response.business.AdvertResponse;
import com.project.payload.response.business.ResponseMessage;
import com.project.service.business.AdvertService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
// import org.springframework.security.access.prepost.PreAuthorize;    // security'yi ekleyince bu class'i tekrar gozden gecirmek gerekiyor

@RestController
@RequestMapping("/adverts")
@RequiredArgsConstructor
public class AdvertController {

    private final AdvertService advertService;

  // Save methodunu yazabilmek icin spring security'i maven'a eklemek gerekiyor, simdilik burada birakiyorum
    // Yorumlu satirlari security katmani yazildiktan sonra aktif edecegim
    @PostMapping("/save")  // http://localhost:8080/adverts/save + POST + JSON
    //@PreAuthorize("hasAnyAuthority('ADMIN','CUSTOMER','MANAGER')")
    public ResponseMessage<AdvertResponse> saveAdvert(){

    }


}
