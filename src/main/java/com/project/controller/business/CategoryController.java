package com.project.controller.CategoryController;

import com.project.payload.request.CategoryRequest.CategoryRequest;
import com.project.entity.concretes.business.Category;
import com.project.payload.response.business.category.CategoryResponse;
import com.project.service.CategoryService.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping() //*C01*
    public ResponseEntity<Page<CategoryResponse>> getActiveCategories(
            @RequestParam(required = false) String q,    // Arama parametresi (opsiyonel)
            @RequestParam(defaultValue = "0") int page,  // Sayfa numarası, varsayılan 0
            @RequestParam(defaultValue = "10") int size, // Sayfadaki öğe sayısı, varsayılan 10
            @RequestParam(defaultValue = "id") String sort, // Sıralama kriteri, varsayılan "id"
            @RequestParam(defaultValue = "asc") String type // Sıralama tipi, varsayılan "asc"
    ) {
        // Sıralama türü ayarlanıyor
        Sort.Direction direction = type.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sort));

        // Kategoriler servis tarafından getirilir, arama varsa ona göre filtreleme yapılır

        Page<Category> categories = categoryService.getActiveCategories(q, pageable);

        // Category nesneleri CategoryResponse'a dönüştürülüyor
        Page<CategoryResponse> response = categories.map(CategoryResponse::new);

        return ResponseEntity.ok(response);
    }







    @GetMapping("/admin")  // *C02* // Kategorileri başlıklarına ve sayfa numarasına ve sayfadaki kayıt sırasına göre getirir.
    public ResponseEntity<Page<CategoryResponse>> getAllCategories(
            @RequestParam(defaultValue = "id,asc") String[] sort,
            @RequestParam(required = false) String q,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {

        // Sıralama işlemi
        Sort sortOrder = Sort.by(Sort.Order.by(sort[0]));
        if (sort.length > 1 && sort[1].equalsIgnoreCase("desc")) {
            sortOrder = sortOrder.descending();
        }

        // Sayfalama işlemi
        Pageable pageable = PageRequest.of(page, size, sortOrder);

        // Kategorileri alırken arama sorgusu varsa kullan
        Page<Category> categories;
        if (q != null && !q.isBlank()) {
            categories = categoryService.searchCategoriesByTitle(q, pageable); // Arama işlemi
        } else {
            categories = (Page<Category>) categoryService.getAllCategories(pageable); // Casting gerekmez
        }

        // CategoryResponse nesnesine dönüştür
        Page<CategoryResponse> response = categories.map(CategoryResponse::new);
        return ResponseEntity.ok(response);
    }



    @GetMapping("/{id}")  // id ye göre getirir  *C03*
    public ResponseEntity<CategoryResponse> getCategoryById(@PathVariable Long id) {
        Optional<Category> category = categoryService.getCategoryById(id);
        if (category.isPresent()) {
            CategoryResponse response = new CategoryResponse(category.get());
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }



    @PostMapping()  // kategori ekler  *C04*
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
    }//

    @PutMapping("/{id}")  // id ye opsiyonel değer gelir örn  :2  //Günceller *C05*
    public ResponseEntity<CategoryResponse> updateCategory(@PathVariable Long id, @RequestBody CategoryRequest request) {
        Optional<Category> categoryOpt = categoryService.getCategoryById(id);
        if (categoryOpt.isPresent()) {
            Category category = categoryOpt.get();
            if (category.getbuiltIn()) {
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


    @DeleteMapping("/{id}")  // *C06*
    public ResponseEntity<CategoryResponse> deleteCategory(@PathVariable Long id) {
        Optional<Category> categoryOpt = categoryService.getCategoryById(id);
        if (categoryOpt.isPresent()) {
            Category category = categoryOpt.get();

            if (category.getbuiltIn()) {
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

    @GetMapping("/slug/{slug}")  // *C11* Slug ile kategori getirir  // 10,09,24 eklendi
    public ResponseEntity<CategoryResponse> getCategoryBySlug(@PathVariable String slug) {
        Optional<Category> categoryOpt = categoryService.getCategoryBySlug(slug);
        if (categoryOpt.isPresent()) {
            CategoryResponse response = new CategoryResponse(categoryOpt.get());
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}




