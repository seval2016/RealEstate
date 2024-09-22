package com.project.controller.CategoryController;

import com.project.entity.concretes.business.Advert;
import com.project.payload.request.CategoryRequest.CategoryPropertyValueRequest;
import com.project.payload.response.business.category.CategoryPropertyValueResponse;
import com.project.entity.Category.CategoryPropertyKey;
import com.project.entity.Category.CategoryPropertyValue;
import com.project.service.CategoryService.CategoryPropertyKeyService;
import com.project.service.CategoryService.CategoryPropertyValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.project.service.business.AdvertService;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/category-property-values")
public class CategoryPropertyValueController {
    @Autowired
    private AdvertService advertService;
    @Autowired
    private CategoryPropertyValueService categoryPropertyValueService;

    @Autowired
    private CategoryPropertyKeyService categoryPropertyKeyService;

    @Autowired
    private AdvertService AdvertService;

    @PostMapping("/add/property/value")
    public ResponseEntity<CategoryPropertyValueResponse> createCategoryPropertyValue(@RequestBody @Valid CategoryPropertyValueRequest request) {
        CategoryPropertyValue value = new CategoryPropertyValue();
        value.setValue(request.getValue());

        // Set Advert entity
        AdvertResponse advertResponse = advertService.getAdvertById(request.getAdvertId());
        if (advertResponse != null) {
            Advert advert = null; // AdvertResponse'u Advert'a dönüştüren mapper

            value.setAdvert(null); // Dönüştürülen Advert entity'sini ayarlıyoruz
        } else {
            return ResponseEntity.badRequest().build();
        }

        //Set CategoryPropertyKey entity
        Optional<CategoryPropertyKey> key = categoryPropertyKeyService.getCategoryPropertyKeyById(request.getCategoryPropertyKeyId());
        if (key.isPresent()) {
            value.setCategoryPropertyKey(key.get());
        } else {
            return ResponseEntity.badRequest().build();
        }

        CategoryPropertyValue createdValue = categoryPropertyValueService.createCategoryPropertyValue(value);
        CategoryPropertyValueResponse response = new CategoryPropertyValueResponse(createdValue);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryPropertyValueResponse> updateCategoryPropertyValue(@PathVariable Long id, @RequestBody CategoryPropertyValueRequest request) {
        CategoryPropertyValue value = new CategoryPropertyValue();
        value.setValue(request.getValue());

        // Set Advert entity
      /*  AdvertResponse advertResponse = advertService.getAdvertById(request.getAdvertId());
        if (advertResponse != null) {
            Object advertMapper;
            advertMapper = null;
            Advert advert = null; // AdvertResponse'u Advert'a dönüştüren mapper
            advertMapper.notifyAll();
            value.setAdvert(advert); // Dönüştürülen Advert entity'sini ayarlıyoruz
        } else {
            return ResponseEntity.badRequest().build();
        }*/



        // Set CategoryPropertyKey entity
        Optional<CategoryPropertyKey> key = categoryPropertyKeyService.getCategoryPropertyKeyById(request.getCategoryPropertyKeyId());
        if (key.isPresent()) {
            value.setCategoryPropertyKey(key.get());
        } else {
            return ResponseEntity.badRequest().build();
        }

        CategoryPropertyValue updatedValue = categoryPropertyValueService.updateCategoryPropertyValue(id, value);
        if (updatedValue != null) {
            CategoryPropertyValueResponse response = new CategoryPropertyValueResponse(updatedValue);
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategoryPropertyValue(@PathVariable Long id) {
        categoryPropertyValueService.deleteCategoryPropertyValue(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryPropertyValueResponse> getCategoryPropertyValueById(@PathVariable Long id) {
        Optional<CategoryPropertyValue> value = categoryPropertyValueService.getCategoryPropertyValueById(id);
        if (value.isPresent()) {
            CategoryPropertyValueResponse response = new CategoryPropertyValueResponse(value.get());
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/category-property-key/{categoryPropertyKeyId}")
    public ResponseEntity<List<CategoryPropertyValueResponse>> getValuesByCategoryPropertyKeyId(@PathVariable Long categoryPropertyKeyId) {
        List<CategoryPropertyValue> values = categoryPropertyValueService.getValuesByCategoryPropertyKeyId(categoryPropertyKeyId);
        List<CategoryPropertyValueResponse> response = values.stream().map(CategoryPropertyValueResponse::new).toList();
        return ResponseEntity.ok(response);
    }




}


