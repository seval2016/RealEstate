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

    @Column(unique = true)
    private Blob data;


    @Column(nullable = false)
    private String name;


    @Column(nullable = true)
    private String type;

    @Column(nullable = false)
    private Boolean featured = false;

    @Column(nullable = false, name = "advert_id")
    private Integer advertId;

}
