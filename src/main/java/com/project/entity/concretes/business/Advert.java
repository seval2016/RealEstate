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



    // ******************************SLUGYFY START*************Bu islemi frontend tarafinda yapabiliyoruz aslinda, gerekli olup olmadigini bir dusunelim***********************//


    public class Slugify {

        public static String slugify(String input) {
            // Convert to lowercase
            String slug = input.toLowerCase();

            // Normalize the string and remove accents
            slug = Normalizer.normalize(slug, Normalizer.Form.NFD);
            slug = slug.replaceAll("\\p{InCombiningDiacriticalMarks}+", "");

            // Replace all non-alphanumeric characters with hyphens
            slug = slug.replaceAll("[^a-z0-9\\s]", "-");

            // Replace spaces with hyphens
            slug = slug.replaceAll("\\s+", "-");

            // Remove any leading or trailing hyphens
            slug = slug.replaceAll("^-|-$", "");

            return slug;
        }

        public static void main(String[] args) {
            String title = "Java Programming: An Introductory Guide!";
            String slug = slugify(title);
            System.out.println("Original: " + title);
            System.out.println("Slugified: " + slug);
        }
    }

    // *****************************SLUGYFY END*******************************************//

}
