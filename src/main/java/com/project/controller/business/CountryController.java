package com.project.controller.business;

import com.project.entity.concretes.business.Country;
import com.project.payload.response.business.ResponseMessage;
import com.project.service.business.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/country")
@RequiredArgsConstructor
public class CountryController {

   private final CountryService countryService;

    @GetMapping("/getAll") // http://localhost:8080/countries/getAll + GET
    public ResponseMessage<List<Country>> getAllCountry() {
        List<Country> countryList = countryService.getAllCountry();
        return ResponseMessage.<List<Country>>builder()
                .httpStatus(HttpStatus.OK)
                .object(countryList)
                .build();
    }

}

