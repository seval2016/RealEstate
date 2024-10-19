package com.project.entity.concretes.business;

import antlr.build.ANTLR;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.entity.concretes.user.User;
import com.project.entity.enums.AdvertStatus;

import com.project.entity.image.Images;
import com.project.payload.request.business.UserResponseForTourRequest;
import com.project.payload.response.business.advert.AdvertResponse;
import com.project.payload.response.business.image.ImagesResponse;
import com.project.utils.SlugUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "adverts")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Advert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    @Size(min = 5, max = 150)
    private String title;

    @Column(length = 300)
    private String description;

    @Column(nullable = false, length = 200)
    @Size(min = 5, max = 200)
    private String slug;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @Column(nullable = false)
    private int status;

    @Column(nullable = false)
    @Builder.Default
    private Boolean builtIn = false;

    @Column(nullable = false)
    @Builder.Default
    private Boolean isActive = true;

    @Column(nullable = false)
    @Builder.Default
    private Integer viewCount = 0;

    @Column(nullable = false, length = 255)
    private String location; // Google embed kodu

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Turkey")
    @Column(nullable = false)
    private LocalDateTime createAt;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Turkey")
    private LocalDateTime updateAt;

    @PrePersist
    protected void onCreate() {
        createAt = LocalDateTime.now();
        updateAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updateAt = LocalDateTime.now();
    }

    @PostPersist
    @PostUpdate
    public void generateSlug() {
        if (this.slug == null || this.slug.isEmpty()) {
            this.slug = SlugUtils.toSlug(this.title) + "-" + this.id;
        }
    }

    // İlişkiler
    @ManyToOne
    @JoinColumn(name = "advert_type_id", nullable = false)
    private AdvertType advertType;

    @ManyToOne
    @JoinColumn(name = "country_id", nullable = false)
    private Country country;

    @ManyToOne
    @JoinColumn(name = "city_id", nullable = false)
    private City city;

    @ManyToOne
    @JoinColumn(name = "district_id", nullable = false)
    private District district;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "advert", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Images> images = new ArrayList<>();

    @OneToMany(mappedBy = "advert", cascade = CascadeType.ALL)
    private List<TourRequest> tourRequestList;

    @OneToMany(mappedBy = "advert", cascade = CascadeType.ALL)
    private List<CategoryPropertyValue> categoryPropertyValuesList;

    @OneToMany(mappedBy = "advert", cascade = CascadeType.ALL)
    private List<Favorite> favoritesList;

    @OneToMany(mappedBy = "advert", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Log> logList;

    // Yeni eklenen alanlar

    // Properties alanı
    @ElementCollection
    @CollectionTable(name = "advert_properties", joinColumns = @JoinColumn(name = "advert_id"))
    @MapKeyColumn(name = "property_key")
    @Column(name = "property_value")
    private Map<String, String> properties;

    // Featured Images alanı
    @ManyToOne
    @JoinColumn(name = "featured_image_id")
    private Images featuredImages;

}

