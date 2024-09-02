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
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Advert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = false, length = 150 )
    private String title;

    @Column(length = 300)
    private String description;

    @Column(nullable = false, length = 200)
    private String slug;

    @Column(nullable = false)
    private Float price;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status = Status.PENDING;

    @Column(nullable = false)
    private Boolean builtIn = false;

    @Column(nullable = false)
    private Boolean isActive = true;

    @Column(nullable = false)
    private int viewCount = 0;

    @Column(nullable = false)
    private String location;

    @Column(name = "create_at", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate createdAt;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate updatedAt;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "advert_type_id", nullable = false)
    private AdvertType advertType;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // *******************Buradan sonraki kisimlar diger class'larin olusturulmasina bagli oldugu icin simdilik hata veriyor   *******************

   /* @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @OneToMany(mappedBy = "advert" , cascade = CascadeType.ALL)
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
