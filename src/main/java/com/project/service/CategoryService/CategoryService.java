package com.project.service.CategoryService;

import com.project.entity.Category.Category;
import com.project.repository.CategoryRepository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public Category createCategory(Category category) {

        if (category.getCreateAt() == null) {
            category.setCreateAt(LocalDateTime.now());
        }

        return categoryRepository.save(category);

    }

    public Category updateCategory(Long id, Category category) {
        if (categoryRepository.existsById(id)) {
             category.setId(id);
            return categoryRepository.save(category);
        }
        return null;
    }

    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }

    public Optional<Category> getCategoryById(Long id) {
        return categoryRepository.findById(id);
    }

    public List<Category> getAllCategories(Sort sort) {
        return categoryRepository.findAll(sort);
    }

    public List<Category> getActiveCategories() {
        return categoryRepository.findByIsActiveTrue();
    }

    public boolean hasRelatedAdverts(Long id) {
        return false;   // 4.09.24 ' de eklendi
    }

    // Kategori başlığına göre arama yapan metot
    public Page<Category> searchCategoriesByTitle(String title, Pageable pageable) {  //10,09,24 eklendi
        return categoryRepository.findByTitleContainingIgnoreCase(title, pageable);
    }
    public Optional<Category> getCategoryBySlug(String slug) {
        return categoryRepository.findBySlug(slug);               //10,09 , 24 eklendi
    }


}

