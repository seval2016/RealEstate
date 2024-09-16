package com.project.service.business;


import com.project.repository.business.CityRepository;
import com.project.service.helper.MethodHelper;
import com.project.entity.concretes.business.City;
import com.project.exception.ResourceNotFoundException;
import com.project.payload.mappers.CityMapper;
import com.project.payload.messages.ErrorMessages;
import com.project.payload.response.business.CityForAdvertsResponse;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CityService {

    private final CityRepository cityRepository;
    private final CountryService countryService;
    private final CityMapper cityMapper;
    private final MethodHelper methodHelper;

    public List<CityForAdvertsResponse> getAllCityForAdverts() {

        List<City> cityList = cityRepository.findAll();

        return cityList.stream().map(cityMapper::mapperCityToCityForAdvertsResponse).collect(Collectors.toList());
    }


    public List<City> getAllCity() {
        return cityRepository.findAll();
    }

    @Transactional
    public City getCityById(Long id) {
        return cityRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(ErrorMessages.CITY_NOT_FOUND));

    }

    public void resetCityTables() {
        cityRepository.deleteAll();
    }


    public int countAllCities() {

        return cityRepository.countAllCities();
    }

    public void setBuiltInForCity() {

        Long cityId = 1L;

        City city = cityRepository.findById(cityId).orElseThrow(() -> new RuntimeException(ErrorMessages.CITY_NOT_FOUND));
        city.setBuilt_in(Boolean.TRUE);
        cityRepository.save(city);
    }

    public List<City> getByCity(Long countryId) {
        return cityRepository.getByCity(countryId);
    }
}