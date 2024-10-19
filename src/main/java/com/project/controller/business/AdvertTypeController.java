package com.project.controller.business;

import com.project.payload.request.business.AdvertTypesRequest;
import com.project.payload.response.business.ResponseMessage;
import com.project.payload.response.business.advert.AdvertTypesResponse;
import com.project.service.business.AdvertTypesService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/advertTypes")
@RequiredArgsConstructor
public class AdvertTypeController {

    private final AdvertTypesService advertTypesService;

    @PostMapping("/save") //  http://localhost:8080/advertTypes/save + JSON + POST
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER')")
    public ResponseMessage<AdvertTypesResponse> saveAdvertType(@RequestBody @Valid AdvertTypesRequest advertTypesRequest){
        return advertTypesService.saveAdvertType(advertTypesRequest);
    }

    @GetMapping("/{id}") //  http://localhost:8080/advertTypes/1 + GET
    @PreAuthorize("hasAnyAuthority('CUSTOMER','ADMIN','MANAGER')")
    public AdvertTypesResponse getAdvertTypeById(@PathVariable Long id){
        return advertTypesService.getAdvertTypeById(id);
    }


}
