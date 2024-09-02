package com.project.entity.concretes.business;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
@Entity
@Getter
@Setter
@Table(name = "images")
@AllArgsConstructor
@NoArgsConstructor
public class Images {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    @JoinColumn(name = "advert_id")
    private Advert advert;

}
