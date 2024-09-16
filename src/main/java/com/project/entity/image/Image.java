package com.project.entity.image;

import lombok.*;

import javax.persistence.*;
import java.sql.Blob;


@Entity
@Table(name="images")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Boolean featured = false;

     @Column(nullable = true)
    private String type;

    @Column(unique = true)
    private  byte[] data;


    @Column(nullable = false)
    private String name;

   @ManyToOne
    @JoinColumn(name = "advert_id")
    private Advert advert;

    @Column(nullable = true)
    private String path; // Resim yolunu saklamak için eklenen alan

}
