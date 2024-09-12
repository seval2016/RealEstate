package com.project.repository.CategoryRepository;

import com.project.entity.Category.Category;
import com.project.entity.Category.CategoryPropertyValue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryPropertyValueRepository extends JpaRepository<CategoryPropertyValue, Long> {


    // Belirli bir advert'e ait tüm değerleri getir
    List<CategoryPropertyValue> findByAdvertAdvertId(Long advertId);

    // Belirli bir kategori özelliği anahtarına ait tüm değerleri getir
    List<CategoryPropertyValue> findByCategoryPropertyKey_Id(Long categoryPropertyKeyId);

    // Değerin içeriğine göre değerleri getir
    List<CategoryPropertyValue> findByValueContaining(String value);
    //  List<CategoryPropertyValue> findById () ;
}
