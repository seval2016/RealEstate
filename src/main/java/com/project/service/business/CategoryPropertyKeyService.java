package com.project.service.CategoryService;

import com.project.entity.concretes.business.CategoryPropertyKey;
import com.project.repository.CategoryRepository.CategoryPropertyKeyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryPropertyKeyService {

    @Autowired
    private CategoryPropertyKeyRepository categoryPropertyKeyRepository;

    public CategoryPropertyKey createCategoryPropertyKey(CategoryPropertyKey key) {
        return categoryPropertyKeyRepository.save(key);
    }

    public CategoryPropertyKey updateCategoryPropertyKey(Long id, CategoryPropertyKey key) {
        // C09 için eksik: 'builtIn' true olan property key güncellenmemeli.
        if (categoryPropertyKeyRepository.existsById(id)) {
            key.setId(id);
            return categoryPropertyKeyRepository.save(key);
        }
        return null;
    }

    public void deleteCategoryPropertyKey(Long id) {
        // C10 için eksik: 'builtIn' true olan property key silinmemeli.
        // Ayrıca category_property_values tablosundaki ilişkili kayıtlar da silinmeli.
        categoryPropertyKeyRepository.deleteById(id);
    }

    public Optional<CategoryPropertyKey> getCategoryPropertyKeyById(Long id) {
        return categoryPropertyKeyRepository.findById(id);
    }

    public List<CategoryPropertyKey> getKeysByCategoryId(Long categoryId) {
        return categoryPropertyKeyRepository.findByCategoryId(categoryId);
    }

    public List<CategoryPropertyKey> getKeysByManagerId(Long managerId) {
        return categoryPropertyKeyRepository.findByManagerId(managerId);
    }

    // Yeni bir CategoryPropertyKey oluşturma metodu



}