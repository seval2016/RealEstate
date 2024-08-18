package com.project.service.business;

import com.project.repository.business.AdvertRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class Advertservice {

    private final AdvertRepository advertRepository;

}
