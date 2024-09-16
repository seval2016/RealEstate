package com.project.repository.CategoryRepository;

import com.project.entity.Category.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    // Burada gerekli özel sorgular tanımlana bilir


// Kategori adını kullanarak kategori bul
    List<Category> findByTitle(String title);


    // Sadece aktif olan kategorileri getir
    List<Category> findByIsActiveTrue();


    // Güncellenme tarihine göre sıralı kategoriler
    List<Category> findAllByOrderByUpdateAtDesc();
    // Kategori başlığına göre arama yapan sorgu
    Page<Category> findByTitleContainingIgnoreCase(String title, Pageable pageable);  // 10,09,24 eklendi

        Optional<Category> findBySlug(String slug);       //10,09,24 eklendi





}