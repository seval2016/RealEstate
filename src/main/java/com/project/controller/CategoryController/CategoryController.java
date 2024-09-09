package com.project.controller.CategoryController;

import com.project.payload.request.CategoryRequest.CategoryRequest;
import com.project.payload.response.CategoryResponse.CategoryResponse;
import com.project.entity.Category.Category;
import com.project.service.CategoryService.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/add")  // kategori ekler
    public ResponseEntity<CategoryResponse> createCategory(@RequestBody  @Valid CategoryRequest request) {
        Category category = new Category();
        category.setTitle(request.getTitle());
        category.setIcon(request.getIcon());
        category.setBuiltIn(request.getBuiltIn());
        category.setSeq(request.getSeq());
        category.setSlug(request.getSlug());
        category.setIsActive(request.getIsActive());
        category.setCreateAt(request.getCreateAt());
        category.setUpdateAt(request.getUpdateAt());
        Category createdCategory = categoryService.createCategory(category);
        CategoryResponse response = new CategoryResponse(createdCategory);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/categories/{id}")  // id ye opsiyonel değer gelir örn  :2  //Günceller
    public ResponseEntity<CategoryResponse> updateCategory(@PathVariable Long id, @RequestBody CategoryRequest request) {
        Optional<Category> categoryOpt = categoryService.getCategoryById(id);
        if (categoryOpt.isPresent()) {
            Category category = categoryOpt.get();
            if (category.getBuiltIn()) {
                return ResponseEntity.badRequest().body(null); // Built-in kategoriler güncellenemez.
            }

            category.setTitle(request.getTitle());
            category.setIcon(request.getIcon());
            category.setSeq(request.getSeq());
            category.setSlug(request.getSlug());
            category.setIsActive(request.getIsActive());
            category.setUpdateAt(request.getUpdateAt());

            Category updatedCategory = categoryService.updateCategory(id, category);
            CategoryResponse response = new CategoryResponse(updatedCategory);
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    /*
      eski PutMapping
    @PutMapping("/{id}")   //kategori günceller
    public ResponseEntity<CategoryResponse> updateCategory(@PathVariable Long id, @RequestBody CategoryRequest request) {
        Category category = new Category();
        category.setTitle(request.getTitle());
        category.setIcon(request.getIcon());
        category.setBuiltIn(request.getBuiltIn());
        category.setSeq(request.getSeq());
        category.setSlug(request.getSlug());
        category.setIsActive(request.getIsActive());
        category.setCreateAt(request.getCreateAt());
        category.setUpdateAt(request.getUpdateAt());

        Category updatedCategory = categoryService.updateCategory(id, category);
        if (updatedCategory != null) {
            CategoryResponse response = new CategoryResponse(updatedCategory);
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }





       */
     
    @DeleteMapping("/{id}")
    public ResponseEntity<CategoryResponse> deleteCategory(@PathVariable Long id) {
        Optional<Category> categoryOpt = categoryService.getCategoryById(id);
        if (categoryOpt.isPresent()) {
            Category category = categoryOpt.get();

            if (category.getBuiltIn()) {
                return ResponseEntity.badRequest().body(null); // Built-in kategoriler silinemez.
            }

            if (categoryService.hasRelatedAdverts(id)) {
                return ResponseEntity.badRequest().body(null); // İlgili advert kayıtları varsa silme işlemi yapılamaz.
            }

            categoryService.deleteCategory(id);
            CategoryResponse response = new CategoryResponse(category);
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }






    /*     eski deletemapping

     @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    } */




    @GetMapping("/{id}")  // id ye göre getirir
    public ResponseEntity<CategoryResponse> getCategoryById(@PathVariable Long id) {
        Optional<Category> category = categoryService.getCategoryById(id);
        if (category.isPresent()) {
            CategoryResponse response = new CategoryResponse(category.get());
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping ("/sort") //kategorileri sıralı getirir
    public ResponseEntity<List<CategoryResponse>> getAllCategories(@RequestParam(defaultValue = "id,asc") String[] sort) {
        Sort sortOrder = Sort.by(Sort.Order.asc(sort[0]));
        List<Category> categories = categoryService.getAllCategories(sortOrder);
        List<CategoryResponse> response = categories.stream().map(CategoryResponse::new).toList();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/active")     //rastgele getirir
    public ResponseEntity<List<CategoryResponse>> getActiveCategories() {
        List<Category> categories = categoryService.getActiveCategories();
        List<CategoryResponse> response = categories.stream().map(CategoryResponse::new).toList();
        return ResponseEntity.ok(response);
    }
}


