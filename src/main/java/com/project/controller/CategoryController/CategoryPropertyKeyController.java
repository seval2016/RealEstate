package com.project.controller.CategoryController;

import com.project.payload.request.CategoryRequest.CategoryPropertyKeyRequest;
import com.project.payload.response.CategoryResponse.CategoryPropertyKeyResponse ;
import com.project.entity.Category.Category;
import com.project.entity.Category.CategoryPropertyKey;
import com.project.service.CategoryService.CategoryPropertyKeyService;
import com.project.service.CategoryService.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/category-property-keys")
public class CategoryPropertyKeyController {

    @Autowired
    private CategoryPropertyKeyService categoryPropertyKeyService;

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/add/property/key")
    public ResponseEntity<CategoryPropertyKeyResponse> createCategoryPropertyKey(@RequestBody CategoryPropertyKeyRequest request) {
        CategoryPropertyKey key = new CategoryPropertyKey();
        key.setName(request.getName());
        key.setType(request.getType());
        key.setBuiltIn(request.getBuiltIn());

        // Set Category entity
        Optional<Category> category = categoryService.getCategoryById(request.getCategoryId());
        if (category.isPresent()) {
            key.setCategory(category.get());
        } else {
            return ResponseEntity.badRequest().build();
        }

        CategoryPropertyKey createdKey = categoryPropertyKeyService.createCategoryPropertyKey(key);
        CategoryPropertyKeyResponse response = new CategoryPropertyKeyResponse(createdKey);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryPropertyKeyResponse> updateCategoryPropertyKey(@PathVariable Long id, @RequestBody CategoryPropertyKeyRequest request) {
        CategoryPropertyKey key = new CategoryPropertyKey();
        key.setName(request.getName());
        key.setType(request.getType());
        key.setBuiltIn(request.getBuiltIn());

        // Set Category entity
        Optional<Category> category = categoryService.getCategoryById(request.getCategoryId());
        if (category.isPresent()) {
            key.setCategory(category.get());
        } else {
            return ResponseEntity.badRequest().build();
        }

        CategoryPropertyKey updatedKey = categoryPropertyKeyService.updateCategoryPropertyKey(id, key);
        if (updatedKey != null) {
            CategoryPropertyKeyResponse response = new CategoryPropertyKeyResponse(updatedKey);
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategoryPropertyKey(@PathVariable Long id) {
        categoryPropertyKeyService.deleteCategoryPropertyKey(id);
        return ResponseEntity.noContent().build();
    }


    /*      eski getMapping
     @GetMapping("/{id}")
    public ResponseEntity<CategoryPropertyKeyResponse> getCategoryPropertyKeyById(@PathVariable Long id) {
        Optional<CategoryPropertyKey> key = categoryPropertyKeyService.getCategoryPropertyKeyById(id);
        if (key.isPresent()) {
            CategoryPropertyKeyResponse response = new CategoryPropertyKeyResponse(key.get());
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    */
    @GetMapping("/{id}/properties")   // id : yerine categoryId bilgisi girilecek
    public ResponseEntity<List<CategoryPropertyKeyResponse>> getKeysByCategoryId(@PathVariable Long id) {
        List<CategoryPropertyKey> keys = categoryPropertyKeyService.getKeysByCategoryId(id);
        List<CategoryPropertyKeyResponse> response = keys.stream().map(CategoryPropertyKeyResponse::new).toList();
        return ResponseEntity.ok(response);
    }







    /*  @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<CategoryPropertyKeyResponse>> getKeysByCategoryId(@PathVariable Long categoryId) {
        List<CategoryPropertyKey> keys = categoryPropertyKeyService.getKeysByCategoryId(categoryId);
        List<CategoryPropertyKeyResponse> response = keys.stream().map(CategoryPropertyKeyResponse::new).toList();
        return ResponseEntity.ok(response);
    } */
}