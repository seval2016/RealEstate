package com.project.repository.CategoryRepository;

import com.project.entity.Category.Category;
import com.project.entity.Category.CategoryPropertyKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryPropertyKeyRepository  extends JpaRepository<CategoryPropertyKey, Long> {
    // Belirli bir kategoriye ait tüm anahtarları getir
    List<CategoryPropertyKey> findByCategoryId(Long categoryId);


    // Belirli bir yöneticinin tüm anahtarlarını getir
    List<CategoryPropertyKey> findByManagerId(Long managerId);

    // Anahtar adını kullanarak anahtar bul
    List<CategoryPropertyKey> findByName(String name);


}
