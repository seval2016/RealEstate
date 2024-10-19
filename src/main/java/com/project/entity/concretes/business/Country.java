package com.project.entity.concretes.business;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)

public class Country {

    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @Column(nullable = false, length = 30)
    private String name;

    private Boolean built_in;


    @OneToMany (mappedBy = "country",cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<City> city= new HashSet<>();


    @OneToMany (mappedBy = "country",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Advert> advertList = new ArrayList<>();


    public void setBuilt_in(Boolean aTrue) {

    }
}
