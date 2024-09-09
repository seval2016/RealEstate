package com.project.repository.CategoryRepository;

import com.project.entity.Category.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    // Burada gerekli özel sorgular tanımlana bilir


// Kategori adını kullanarak kategori bul
    List<Category> findByTitle(String title);


    // Sadece aktif olan kategorileri getir
    List<Category> findByIsActiveTrue();


    // Güncellenme tarihine göre sıralı kategoriler
    List<Category> findAllByOrderByUpdateAtDesc();





}