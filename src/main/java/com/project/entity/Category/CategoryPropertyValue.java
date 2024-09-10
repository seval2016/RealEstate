package com.project.entity.Category;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import com.project.entity.concretes.business.Advert;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
public class CategoryPropertyValue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Value cannot be null")
    @Size(max = 100, message = "Max length is 100 characters")
    private String value;

    @ManyToOne
    @JoinColumn(name = "advert_id")
    private Advert advert;  // ManyToOne ilişki tanımı, Advert sınıfına referans verir


    @JsonIgnore // sonsuz döngüye girilmesin diye @JsonIgnore eklendi
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_property_key_id", nullable = false)
    private CategoryPropertyKey categoryPropertyKey; // Kendi varlık adını yaz
}








/*package com.project.entity.Category;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import com.project.entity.concretes.business.Advert;


@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
public class CategoryPropertyValue  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    @NotNull(message = "Value cannot be null")
    @Size(max = 100, message = "Max length is 100 characters")
    private String value;


    @ManyToOne
    @JoinColumn(name = "advert_id")
    private Advert advert;  // ManyToOne ilişki tanımı, Advert sınıfına referans verir


    // Bir CategoryPropertyKey varlığına referans
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_property_key_id", nullable = false)
    private CategoryPropertyKey categoryPropertyKey; // Kendi varlık adını yaz
    public void setCategoryPropertyKey(CategoryPropertyKey categoryPropertyKey) {
    }
}
*/