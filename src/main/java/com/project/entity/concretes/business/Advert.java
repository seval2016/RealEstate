package com.project.entity.concretes.business;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.entity.concretes.user.User;
//import com.project.entity.enums.Status;
import lombok.*;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "adverts")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Advert {

    @Id
    //@Column(name = "advert_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @NotNull
    @Size(min = 5, max = 150)
    private String title;

    @Column(length = 300)
    private String description;

//    @Column(nullable = false, unique = true)
//    @Size(min = 5, max = 200)
//    private String slug;

    @Column(nullable = false)
    private Float price;

    @NotNull
  //  @Enumerated(EnumType.STRING)
    @Min(0)
    @Max(2)
    //private Status status = Status.values()[0];
    private int status = 0;

    @NotNull
    private Boolean builtIn = false;

    @NotNull
    private Boolean isActive = true;

    @NotNull
    private int viewCount = 0;

    @NotNull
    private String location;

    //@Column(nullable = false)
    @NotNull(message = " Create date must not be empty")
    @PastOrPresent
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate createdAt;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate updatedAt;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "advert_type_id", nullable = false)
    private AdvertType advertType;
//************************************************************************************************
//    @ManyToOne
//    @JsonIgnore
//    @JoinColumn(name = "user_id", nullable = false)
//    private User user;

    //************************************************************************************************

    // *******************Buradan sonraki kisimlar diger class'larin olusturulmasina bagli oldugu icin simdilik hata veriyor   *******************
/*

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @OneToMany(mappedBy = "advert", cascade = CascadeType.ALL)
    // @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Images> images;

    @OneToMany(mappedBy = "advert")
    // @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<CategoryPropertyValues> categoryPropertyValues;

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


    @OneToMany(mappedBy = "advert", cascade = CascadeType.ALL)
    // @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Set<Favorites> favorites;

    @OneToMany(mappedBy = "advert", cascade = CascadeType.ALL)
    // @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Logs> logs;

    @OneToMany(mappedBy = "advert", cascade = CascadeType.ALL)
    // @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<TourRequests> tourRequests;
*/

}