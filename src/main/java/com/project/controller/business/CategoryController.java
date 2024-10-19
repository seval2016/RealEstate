package com.project.controller.business;

import com.project.entity.concretes.business.Category;
import com.project.payload.response.business.category.CategoryResponse;
import com.project.repository.business.CategoryPropertyKeyRepository;
import com.project.service.business.CategoryService;
import com.project.payload.request.business.CategoryRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CategoryPropertyKeyRepository categoryPropertyKeyRepository;


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

        // Category nesneleri manuel olarak CategoryResponse'a dönüştürülüyor
        Page<CategoryResponse> response = categories.map(category -> CategoryResponse.builder()
                .id(category.getId())
                .title(category.getTitle())
                .icon(category.getIcon())
                .builtIn(category.getBuiltIn())
                .seq(category.getSeq())
                .slug(category.getSlug())
                .isActive(category.getIsActive())
                .createAt(category.getCreateAt())
                .updateAt(category.getUpdateAt())

                .build());

        return ResponseEntity.ok(response);
    }






    @GetMapping("/admin")  // *C02* // Kategorileri başlıklarına, sayfa numarasına ve sayfadaki kayıt sırasına göre getirir.
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
            categories = (Page<Category>) categoryService.getAllCategories(pageable);
        }

        // Category nesnesini CategoryResponse nesnesine dönüştür
        Page<CategoryResponse> response = categories.map(category -> CategoryResponse.builder()
                .id(category.getId())
                .title(category.getTitle())
                .icon(category.getIcon())
                .builtIn(category.getBuiltIn())
                .seq(category.getSeq())
                .slug(category.getSlug())
                .isActive(category.getIsActive())
                .createAt(category.getCreateAt())
                .updateAt(category.getUpdateAt())

                .build());

        return ResponseEntity.ok(response);
    }



    @GetMapping("/{id}")  // id'ye göre getirir  *C03*
    public ResponseEntity<CategoryResponse> getCategoryById(@PathVariable Long id) {
        Optional<Category> category = categoryService.getCategoryById(id);
        if (category.isPresent()) {
            CategoryResponse response = CategoryResponse.builder()
                    .id(category.get().getId())
                    .title(category.get().getTitle())
                    .icon(category.get().getIcon())
                    .builtIn(category.get().getBuiltIn())
                    .seq(category.get().getSeq())
                    .slug(category.get().getSlug())
                    .isActive(category.get().getIsActive())
                    .createAt(category.get().getCreateAt())
                    .updateAt(category.get().getUpdateAt())

                    .build();
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }



    @PostMapping()  // kategori ekler  *C04*

    public ResponseEntity<CategoryResponse> createCategory(@RequestBody @Valid CategoryRequest request) {
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

        // CategoryResponse nesnesini manuel oluşturuyoruz
        CategoryResponse response = CategoryResponse.builder()
                .id(createdCategory.getId())
                .title(createdCategory.getTitle())
                .icon(createdCategory.getIcon())
                .builtIn(createdCategory.getBuiltIn())
                .seq(createdCategory.getSeq())
                .slug(createdCategory.getSlug())
                .isActive(createdCategory.getIsActive())
                .createAt(createdCategory.getCreateAt())
                .updateAt(createdCategory.getUpdateAt())

                .build();

        return ResponseEntity.ok(response);
    }



    @PutMapping("/{id}")  // id'ye göre günceller *C05*
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

            // Manuel CategoryResponse dönüşümü
            CategoryResponse response = CategoryResponse.builder()
                    .id(updatedCategory.getId())
                    .title(updatedCategory.getTitle())
                    .icon(updatedCategory.getIcon())
                    .builtIn(updatedCategory.getBuiltIn())
                    .seq(updatedCategory.getSeq())
                    .slug(updatedCategory.getSlug())
                    .isActive(updatedCategory.getIsActive())
                    .createAt(updatedCategory.getCreateAt())
                    .updateAt(updatedCategory.getUpdateAt())

                    .build();

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

            if (category.getBuiltIn()) {
                return ResponseEntity.badRequest().body(null); // Built-in kategoriler silinemez.
            }

            if (categoryService.hasRelatedAdverts(id)) {
                return ResponseEntity.badRequest().body(null); // İlgili advert kayıtları varsa silme işlemi yapılamaz.
            }

            categoryService.deleteCategory(id);

            // Manuel CategoryResponse dönüşümü
            CategoryResponse response = CategoryResponse.builder()
                    .id(category.getId())
                    .title(category.getTitle())
                    .icon(category.getIcon())
                    .builtIn(category.getBuiltIn())
                    .seq(category.getSeq())
                    .slug(category.getSlug())
                    .isActive(category.getIsActive())
                    .createAt(category.getCreateAt())
                    .updateAt(category.getUpdateAt())
                    .build();

            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping("/slug/{slug}")  // *C11* Slug ile kategori getirir  // 10,09,24 eklendi
    public ResponseEntity<CategoryResponse> getCategoryBySlug(@PathVariable String slug) {
        Optional<Category> categoryOpt = categoryService.getCategoryBySlug(slug);
        if (categoryOpt.isPresent()) {
            Category category = categoryOpt.get();

            // Manuel dönüşüm
            CategoryResponse response = CategoryResponse.builder()
                    .id(category.getId())
                    .title(category.getTitle())
                    .icon(category.getIcon())
                    .builtIn(category.getBuiltIn())
                    .seq(category.getSeq())
                    .slug(category.getSlug())
                    .isActive(category.getIsActive())
                    .createAt(category.getCreateAt())
                    .updateAt(category.getUpdateAt())

                    .build();

            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}




