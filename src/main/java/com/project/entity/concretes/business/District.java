package com.project.entity.concretes.business;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class District {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @NotNull(message = "Please enter district")
    @Size(min = 2 ,max = 30 , message = "Min 2 chars and max 30 chars ")
    private String name;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "city_id")
    private City city;

    private Boolean built_in;

    @OneToMany(mappedBy = "district",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Advert> advertList = new ArrayList<>();

    public void setBuilt_in(Boolean aTrue) {

    }
}
