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

    @Column(nullable = false)
    private boolean featured;

    @Column(nullable = true)
    private String type;

    @Lob
    private byte[] data;

    @Column(nullable = false)
    private String name;


    @ManyToOne
    @JoinColumn(name = "advert_id")
    private Advert advert;


    @Column(nullable = true)
    private String path; // Resim yolunu saklamak için eklenen alan
}
