package com.project.entity.concretes.business;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class AdvertType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Length(min = 5, max = 30) // @Range olur muydu acaba
    private String title;

    @NotNull
    private Boolean builtIn = false; // boolean ou Boolean

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "advertType")
    private List<Advert> adverts;

}
