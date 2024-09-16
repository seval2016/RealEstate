package com.project.service.CategoryService;

import com.project.entity.Category.CategoryPropertyKey;
import com.project.entity.Category.CategoryPropertyValue;
import com.project.exception.ResourceNotFoundException;
import com.project.repository.CategoryRepository.CategoryPropertyKeyRepository;
import com.project.repository.CategoryRepository.CategoryPropertyValueRepository ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class CategoryPropertyValueService {

    @Autowired
    private CategoryPropertyValueRepository categoryPropertyValueRepository;

    public CategoryPropertyValue createCategoryPropertyValue(CategoryPropertyValue value) {
        return categoryPropertyValueRepository.save(value);
    }

    public CategoryPropertyValue updateCategoryPropertyValue(Long id, CategoryPropertyValue value) {
        if (categoryPropertyValueRepository.existsById(id)) {
            value.setId(id);
            return categoryPropertyValueRepository.save(value);
        }
        return null;
    }

    public void deleteCategoryPropertyValue(Long id) {
        categoryPropertyValueRepository.deleteById(id);
    }

    public Optional<CategoryPropertyValue> getCategoryPropertyValueById(Long id) {
        return categoryPropertyValueRepository.findById(id);
    }

    public List<CategoryPropertyValue> getValuesByAdvertId(Long advertId) {
        return categoryPropertyValueRepository.findByAdvertAdvertId(advertId);
    }

    public List<CategoryPropertyValue> getValuesByCategoryPropertyKeyId(Long categoryPropertyKeyId) {
        return categoryPropertyValueRepository.findByCategoryPropertyKey_Id(categoryPropertyKeyId);

    }


}