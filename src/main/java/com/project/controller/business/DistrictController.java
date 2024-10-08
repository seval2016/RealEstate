package com.project.controller.business;

import com.project.entity.concretes.business.District;
import com.project.payload.response.business.DistrictResponse;
import com.project.payload.response.business.ResponseMessage;
import com.project.service.business.DistrictService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/district")
@RequiredArgsConstructor
public class DistrictController {

   private final DistrictService districtService;

    @GetMapping("/getAll")
    public List<DistrictResponse> getAllDistricts(){
        return districtService.getAllDistricts();
    }

    @GetMapping("/getByDistrict/{cityId}") //http://localhost:8080/district/getByDistrict/1
    public ResponseMessage<List<District>> getByDistrict(@PathVariable Long cityId){
        List<District> districtList =districtService.getByDistrict(cityId);
        return ResponseMessage.<List<District>>builder()
                .httpStatus(HttpStatus.OK)
                .object(districtList)
                .build();
    }
}
