package com.project.controller.business;

import com.project.payload.request.business.AdvertRequest;
import com.project.payload.response.business.AdvertResponse;
import com.project.payload.response.business.ResponseMessage;
import com.project.service.business.AdvertService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

import javax.validation.Valid;
import java.util.List;

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

    @GetMapping("/id") // http://localhost:8080/adverts/1 + GET
    @PreAuthorize("hasAnyAuthority('ADMIN','CUSTOMER','MANAGER')")
    public AdvertResponse getAdvertById(@PathVariable Long id){
    return advertService.getAdvertById(id);
    }

    @GetMapping("/getAll") // http://localhost:8080/adverts/getAll + GET
    @PreAuthorize("hasAnyAuthority('ADMIN','CUSTOMER','MANAGER')")
    public List<AdvertResponse> getAllAdverts(){
        return advertService.getAllAdverts();
    }

}
