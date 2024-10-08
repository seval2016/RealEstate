package com.project.controller.CategoryController;

import com.project.entity.Category.CategoryPropertyValue;
import com.project.payload.request.CategoryRequest.CategoryPropertyKeyRequest;
import com.project.entity.Category.Category;
import com.project.entity.Category.CategoryPropertyKey;
import com.project.payload.response.business.category.CategoryPropertyKeyResponse;
import com.project.service.CategoryService.CategoryPropertyKeyService;
import com.project.service.CategoryService.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.project.service.CategoryService.CategoryPropertyValueService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categories")
public class CategoryPropertyKeyController {

    @Autowired
    private CategoryPropertyKeyService categoryPropertyKeyService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/{id}/properties")   // id : yerine categoryId bilgisi girilecek  *C07*
    public ResponseEntity<List<CategoryPropertyKeyResponse>> getKeysByCategoryId(@PathVariable Long id) {
        List<CategoryPropertyKey> keys = categoryPropertyKeyService.getKeysByCategoryId(id);
        List<CategoryPropertyKeyResponse> response = keys.stream().map(CategoryPropertyKeyResponse::new).toList();
        return ResponseEntity.ok(response);
    }


    //C07 alternatif
    /*
    @GetMapping("/{id}/properties")   // id : yerine categoryId bilgisi girilecek  *C07*
public ResponseEntity<List<CategoryPropertyKeyResponse>> getKeysByCategoryId(@PathVariable Long id) {
    List<CategoryPropertyKey> keys = categoryPropertyKeyService.getKeysByCategoryId(id);
    List<CategoryPropertyKeyResponse> response = keys.stream()
        .map(CategoryPropertyKeyResponse::new)
        .toList();
    return ResponseEntity.ok(response);
}
     */

    @PostMapping("/{id}/properties")  // *C08*
    public ResponseEntity<CategoryPropertyKeyResponse> createCategoryPropertyKey(
            @PathVariable Long id,  // PathVariable ile categoryId alınıyor
            @RequestBody CategoryPropertyKeyRequest request) {

        // Yeni CategoryPropertyKey nesnesi oluşturuluyor
        CategoryPropertyKey key = new CategoryPropertyKey();
        key.setName(request.getName());
        key.setType(request.getType());
        key.setBuiltIn(request.getBuiltIn());

        // categoryId ile categoryService'den kategori alınıyor
        Optional<Category> category = categoryService.getCategoryById(id);
        if (category.isPresent()) {
            key.setCategory(category.get());  // Category set ediliyor
        } else {
            return ResponseEntity.badRequest().build();  // Kategori bulunamazsa hata
        }

        // Yeni CategoryPropertyKey kaydediliyor
        CategoryPropertyKey createdKey = categoryPropertyKeyService.createCategoryPropertyKey(key);

        // Response oluşturuluyor
        CategoryPropertyKeyResponse response = new CategoryPropertyKeyResponse(createdKey);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/properties/{id}")  // *C09*
    public ResponseEntity<CategoryPropertyKeyResponse> updateCategoryPropertyKey(
            @PathVariable Long id,
            @RequestBody CategoryPropertyKeyRequest request) {

        // 1. Mevcut CategoryPropertyKey nesnesi getirilir
        Optional<CategoryPropertyKey> existingKey = categoryPropertyKeyService.getCategoryPropertyKeyById(id);
        if (existingKey.isEmpty()) {
            // Eğer key bulunamazsa 404 Not Found döndür
            return ResponseEntity.notFound().build();
        }

        // 2. Mevcut key nesnesini güncelle
        CategoryPropertyKey key = existingKey.get();
        key.setName(request.getName());
        key.setType(request.getType());
        key.setBuiltIn(request.getBuiltIn());

        // 3. Kategori güncelleniyor (Eğer request'teki categoryId ile kategori bulunamazsa bad request döndürülür)
        Optional<Category> category = categoryService.getCategoryById(request.getCategoryId());
        if (category.isPresent()) {
            key.setCategory(category.get());
        } else {
            return ResponseEntity.badRequest().build();
        }

        // 4. Güncellenen CategoryPropertyKey nesnesini kaydet
        CategoryPropertyKey updatedKey = categoryPropertyKeyService.updateCategoryPropertyKey(id, key);

        // 5. Güncellenen key'den CategoryPropertyKeyResponse oluştur ve döndür
        CategoryPropertyKeyResponse response = new CategoryPropertyKeyResponse(updatedKey);
        return ResponseEntity.ok(response);
    }




    @DeleteMapping("/properties/{id}")   // *C10*  id değeri girilmeli //!!categoryId değeri hata fırlatır
    public ResponseEntity<String> deleteCategoryPropertyKey(@PathVariable Long id) {

        // 1. Silinecek olan CategoryPropertyKey var mı diye kontrol et
        Optional<CategoryPropertyKey> propertyKey = categoryPropertyKeyService.getCategoryPropertyKeyById(id);
        if (propertyKey.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        // 2. İlişkili CategoryPropertyValue kayıtlarını sil
        List<CategoryPropertyValue> propertyValues = CategoryPropertyValueService.getValuesByPropertyKeyId(id) ;
        if (!propertyValues.isEmpty()) {
            CategoryPropertyValueService.deleteAll(propertyValues);
        }



        // 3. CategoryPropertyKey'i sil
        categoryPropertyKeyService.deleteCategoryPropertyKey(id);

        String message = "Category property with ID " + id + " and its related values have been successfully deleted.";
        return ResponseEntity.ok(message);
    }


}