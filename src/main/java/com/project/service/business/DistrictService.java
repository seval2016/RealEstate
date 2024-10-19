package com.project.service.business;

import com.project.entity.concretes.business.District;
import com.project.exception.ResourceNotFoundException;
import com.project.payload.mappers.DistrictMapper;
import com.project.payload.messages.ErrorMessages;
import com.project.payload.response.business.DistrictResponse;
import com.project.repository.business.DistrictRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DistrictService {

    private final DistrictRepository districtRepository;
    private final DistrictMapper districtMapper;
    private final CityService cityService;

    public List<DistrictResponse> getAllDistricts() {
        return districtRepository.findAll()
                .stream()
                .map(districtMapper::mapDistrictToDistrictResponse)
                .collect(Collectors.toList());
    }

    public District getDistrictByIdForAdvert(Long districtId) {
        return districtRepository.findById(districtId).orElseThrow(()->new ResourceNotFoundException(ErrorMessages.DISTRICT_NOT_FOUND));
    }

    public void resetDistrictTables() {
        districtRepository.deleteAll();
    }

    public int countAllDistricts() {
        return districtRepository.countAllDistricts();
    }

    public void setBuiltInForDistrict() {
        // Türkiye'nin ID'si 1 olduğundan emin olun
        Long districtId = 1L;

        District district = districtRepository.findById(districtId).orElseThrow(() -> new RuntimeException(ErrorMessages.DISTRICT_NOT_FOUND));
        district.setBuilt_in(Boolean.TRUE);
        districtRepository.save(district);
    }

    public List<District> getByDistrict(Long cityId) {
        return districtRepository.getByDistrict(cityId);

    }}
