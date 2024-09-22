package com.project.controller.business;

import com.project.payload.request.business.AdvertTypesRequest;
import com.project.payload.response.business.advert.AdvertTypesResponse;
import com.project.payload.response.business.ResponseMessage;
import com.project.service.business.AdvertTypesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/advert-types")
@RequiredArgsConstructor
public class AdvertTypesController {

    private final AdvertTypesService advertTypesService;

    /**
     * T01 - /advert-types
     * Tüm ilan türlerini getirir.
     * http://localhost:8080/advert-types
     *
     * @return List of AdvertTypesResponse
     */
    @GetMapping
    public List<AdvertTypesResponse> getAllAdvertTypes(){
        return advertTypesService.getAllAdvertTypes();
    }

    /**
     * T02 - /advert-types/{id}
     * Belirtilen ID'ye göre ilan türünü getirir.
     * http://localhost:8080/advert-types/{id}
     *
     * @param id İlan türünün ID'si
     * @return AdvertTypesResponse
     */
    @GetMapping("/advert-types/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER')")
    public AdvertTypesResponse getAdvertTypeById(@PathVariable Long id){
        return advertTypesService.getAdvertTypeById(id);
    }

    /**
     * T03 - /advert-types
     * Yeni bir ilan türü oluşturur.
     * http://localhost:8080/advert-types
     *
     * @param advertTypesRequest Oluşturulacak ilan türünün bilgileri
     * @return ResponseMessage<AdvertTypesResponse>
     */
    @PostMapping("/advert-types")
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER')")
    public ResponseMessage<AdvertTypesResponse> createAdvertType(@Valid @RequestBody AdvertTypesRequest advertTypesRequest){
        return advertTypesService.saveAdvertType(advertTypesRequest);
    }

    /**
     * T04 - /advert-types/{id}
     * Belirtilen ID'ye göre ilan türünü günceller.
     * http://localhost:8080/advert-types/{id}
     *
     * @param id İlan türünün ID'si
     * @param advertTypesRequest Güncellenecek ilan türü bilgileri
     * @return ResponseEntity<AdvertTypesResponse>
     */
    @PutMapping("/advert-types/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER')")
    public ResponseEntity<AdvertTypesResponse> updateAdvertTypeById(@PathVariable Long id, @RequestBody AdvertTypesRequest advertTypesRequest){
        return ResponseEntity.ok(advertTypesService.updateAdvertTypeById(id, advertTypesRequest));
    }

    /**
     * T05 - /advert-types/{id}
     * Belirtilen ID'ye göre bir ilan türünü siler.
     * http://localhost:8080/advert-types/{id}
     *
     * @param id İlan türünün ID'si
     * @return Silinen ilan türüyle ilgili bir mesaj
     */
    @DeleteMapping("/advert-types/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER')")
    public ResponseMessage deleteAdvertType(@PathVariable Long id){
        return advertTypesService.deleteAdvertTypeById(id);
    }
}
