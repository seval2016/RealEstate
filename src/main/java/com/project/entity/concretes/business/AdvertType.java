package com.project.entity.concretes.business;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "advert_types")
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class AdvertType {

    @Id
    @Column(name = "advert_type_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Range(min = 5, max = 30)
    private String title;

    @NotNull
    private Boolean builtIn = false; // boolean ou boolean

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "advertType")
    private List<Advert> adverts;

}
