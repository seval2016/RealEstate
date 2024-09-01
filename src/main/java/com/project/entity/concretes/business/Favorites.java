package com.project.entity.concretes.business;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
//@Table(name = "favorites")
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class Favorites {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "advert_id")
    private Advert advert;

}