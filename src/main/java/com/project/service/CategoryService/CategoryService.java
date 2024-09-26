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
        // C05 için kontrol eksik: 'builtIn' true olan kategori güncellenmemeli.
        // Bu kontrol eklenmeli.
        if (categoryRepository.existsById(id)) {
            category.setId(id);
            return categoryRepository.save(category);
        }
        return null;
    }

    public void deleteCategory(Long id) {
        // C06 için eksik: İlanlarla ilişkili kayıtlar kontrol edilip, varsa silme işlemi yapılmalı.
        // Ayrıca 'builtIn' true olan kategoriler silinmemeli.
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
        // C06'da ilanlarla ilişkili olup olmadığı kontrol edilmeli, şu an false dönüyor.
        // İlanlarla ilişkili kontrol mekanizması eklenmeli.
        return false;
    }

    // Kategori başlığına göre arama yapan metot
    public Page<Category> searchCategoriesByTitle(String title, Pageable pageable) {
        // C01 ve C02 için doğru; başlığa göre arama yapılabiliyor.
        return categoryRepository.findByTitleContainingIgnoreCase(title, pageable);
    }

    public Optional<Category> getCategoryBySlug(String slug) {
        return categoryRepository.findBySlug(slug);
    }
}

