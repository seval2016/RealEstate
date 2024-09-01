package com.project.entity.concretes.business;

import javax.persistence.*;

@Entity
//@Table(name = "country")
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
