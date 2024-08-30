package com.project.entity.concretes.business;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.entity.concretes.user.User;
import com.project.entity.enums.Status;
import lombok.*;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "adverts")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)

public class Advert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long advertId;

    @NotNull
    @Range(min = 5, max = 150)  // @Range anotasyonu ( min ve max degerleri belirlememizi saglar, valeur olarak
    private String title;

    @NotNull
    @Range(max = 300)
    private String description;

    @NotNull
    @Range(min = 5, max = 200)
    private String slug;

    @NotNull
    private Float price;

    @NotNull // (message = "Status cannot be null")
    @Enumerated(EnumType.STRING)
    @Range(min = 0, max =2)
    //@Min(0)
    //@Max(2)
    private Status status = Status.PENDING;

    @NotNull
    private Boolean builtIn = false;

    @NotNull
    private Boolean isActive = true;

    @NotNull
    private int viewCount = 0;

    private String location;

    @NotNull(message = " Create date must not be empty")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate createdAt;

    @Column(nullable = true)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate updatedAt;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "advert_type_id", nullable = false)
    private AdvertType advertType;

    // *******************Buradan sonraki kisimlar diger class'larin olusturulmasina bagli oldugu icin simdilik hata veriyor   *******************

/*

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "country_id", nullable = false)
    private Country country;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "city_id", nullable = false)
    private City city;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "district_id", nullable = false)
    private District district;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

     @OneToMany(mappedBy = "advert" , cascade = CascadeType.ALL)
   // @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Images> images;

     @OneToMany
    // @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<CategoryPropertyValues> categoryPropertyValues;

     @OneToMany(mappedBy = "advert" , cascade = CascadeType.ALL)
    // @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Set<Favorites> favorites;

     @OneToMany(mappedBy = "advert" , cascade = CascadeType.ALL)
    // @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Logs> logs;

     @OneToMany(mappedBy = "advert" , cascade = CascadeType.ALL)
    // @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<TourRequests> tourRequests;

    */


}
