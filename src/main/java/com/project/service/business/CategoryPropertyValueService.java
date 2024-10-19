package com.project.service.business;

import com.project.entity.concretes.business.CategoryPropertyValue;
import com.project.repository.business.CategoryPropertyValueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryPropertyValueService {

    @Autowired
    private static CategoryPropertyValueRepository categoryPropertyValueRepository;

    public CategoryPropertyValue createCategoryPropertyValue(CategoryPropertyValue value) {
        // C07 için eksik: 'builtIn' true olan category property value yaratılmamalı.
        return categoryPropertyValueRepository.save(value);
    }

    public CategoryPropertyValue updateCategoryPropertyValue(Long id, CategoryPropertyValue value) {
        // C09 için eksik: 'builtIn' true olan property value güncellenmemeli.
        if (categoryPropertyValueRepository.existsById(id)) {
            value.setId(id);
            return categoryPropertyValueRepository.save(value);
        }
        return null;
    }

    public void deleteCategoryPropertyValue(Long id) {
        // C10 için eksik: 'builtIn' true olan property value silinmemeli.
        // Ayrıca bu property value ile ilişkili ilanlar ve anahtarlar (keys) varsa, silme işlemi yapılmalı.
        categoryPropertyValueRepository.deleteById(id);
    }

    public Optional<CategoryPropertyValue> getCategoryPropertyValueById(Long id) {
        return categoryPropertyValueRepository.findById(id);
    }

    public List<CategoryPropertyValue> getValuesByAdvertId(Long advertId) {
        return categoryPropertyValueRepository.findByAdvert_Id(advertId);
    }

    public List<CategoryPropertyValue> getValuesByCategoryPropertyKeyId(Long categoryPropertyKeyId) {
        return categoryPropertyValueRepository.findByCategoryPropertyKey_Id(categoryPropertyKeyId);
    }
    // Verilen keyId'ye göre ilişkili CategoryPropertyValue kayıtlarını getir
    public static List<CategoryPropertyValue> getValuesByPropertyKeyId(Long keyId) {
        return categoryPropertyValueRepository.findByCategoryPropertyKeyId(keyId);
    }

    // İlişkili CategoryPropertyValue kayıtlarını topluca sil
    public static void deleteAll(List<CategoryPropertyValue> values) {
        categoryPropertyValueRepository.deleteAll(values);
    }
    public CategoryPropertyValue saveCategoryPropertyValue(CategoryPropertyValue categoryPropertyValue) {
        return categoryPropertyValueRepository.save(categoryPropertyValue);
    }


    public List<CategoryPropertyValue> categoryFindAllByValue(String value) {
        return categoryPropertyValueRepository.findAllByValue(value);
    }

}
