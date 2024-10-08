package com.project.entity.concretes.business;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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

    @JsonIgnore // sonsuz döngüye girilmesin diye @JsonIgnore eklendi
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_property_key_id", nullable = false)
    private CategoryPropertyKey categoryPropertyKey; // Kendi varlık adını yaz

    @ManyToOne
    @JoinColumn(name = "advert_id", nullable = false) // İlişkinin zorunlu olduğunu belirtmek için nullable = false ekledik
    private Advert advert; // ManyToOne ilişki tanımı, Advert sınıfına referans verir


    @Column(name = "property_key_id")
    private Long propertyKeyId;

}


