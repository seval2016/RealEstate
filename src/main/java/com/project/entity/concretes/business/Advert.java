package com.project.entity.concretes.business;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

import java.text.Normalizer;
// import java.util.regex.Pattern;

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
    @Size(min = 5, max = 150)
    private String title;

    @NotNull
    @Size(max = 300)
    private String desc;

    @NotNull
    @Size(min = 5, max = 200)
    private String slug;

    @NotNull
    private Float price;

    @NotNull
    private int status = 0;

    @NotNull
    private Boolean builtIn =false;  // Bu satiri gozden gecir

    @NotNull
    private Boolean isActive = true;

    @NotNull
    private int viewCount = 0;

    private String location;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDateTime create_at;

    @Column(nullable = true)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDateTime update_at;

    // *******************Buradan sonraki kisimlar diger class'larin olusturulmasina bagli oldugu icin simdilik hata veriyor   *******************

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "advert_type_id", nullable = false)
    private AdvertType advertType;

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

}
