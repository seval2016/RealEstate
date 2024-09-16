package com.project.controller.business;

import com.project.payload.request.business.AdvertTypeRequest;
import com.project.payload.response.business.AdvertTypeResponse;
import com.project.payload.response.business.ResponseMessage;
import com.project.service.business.AdvertTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/advertTypes")
@RequiredArgsConstructor
public class AdvertTypeController {

    private final AdvertTypeService advertTypeService;

    @PostMapping("/save") //  http://localhost:8080/advertTypes/save + JSON + POST
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER')")
    public ResponseMessage<AdvertTypeResponse> saveAdvertType(@RequestBody @Valid AdvertTypeRequest advertTypeRequest){
        return advertTypeService.saveAdvertType(advertTypeRequest);
    }

    @GetMapping("/{id}") //  http://localhost:8080/advertTypes/1 + GET
    @PreAuthorize("hasAnyAuthority('CUSTOMER','ADMIN','MANAGER')")
    public AdvertTypeResponse getAdvertTypeById(@PathVariable Long id){
        return advertTypeService.getAdvertTypeById(id);
    }


}