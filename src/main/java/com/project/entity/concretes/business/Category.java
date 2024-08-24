package com.project.entity.concretes.business;

import javax.persistence.*;

@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@Column(name = "category_id")
    private Long categoryId;
}
