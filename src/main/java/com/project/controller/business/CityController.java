package com.project.controller.business;

import com.project.payload.response.business.ResponseMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping
@RequiredArgsConstructor
public class CityController {

    private final CityService cityService;

    @GetMapping("/getAll") //http://localhost:8080/city/getAll
    public ResponseMessage<List<City>> getAllCity() {
        List<City> cityList = cityService.getAllCity();
        return ResponseMessage.<List<City>>builder()
                .httpStatus(HttpStatus.OK)
                .object(cityList)
                .build();
    }

    @GetMapping("/getByCity/{countryId}") //http://localhost:8080/city/getByCity/1
    public ResponseMessage<List<City>> getByCity(@PathVariable Long countryId) {
        List<City> cityList = cityService.getByCity(countryId);
        return ResponseMessage.<List<City>>builder()
                .httpStatus(HttpStatus.OK)
                .object(cityList)
                .build();
    }
}
