package com.project.entity.concretes.business;

import lombok.*;

import javax.persistence.*;
import java.util.Base64;


@Entity
@Getter
@Setter
@Table(name = "images")
@AllArgsConstructor
@NoArgsConstructor
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    private byte[] data;

    @Column(nullable = false)
    private String name;

    @Column(nullable = true)
    private String type;

    @Column(nullable = false)
    private Boolean featured=false;

    @ManyToOne
    @JoinColumn(name = "advert_id")
    private Advert advert;


    @Column(nullable = true)
    private String path;

    public String getUrl() {
        // Burada URL oluşturma mantığını ekleyin
        // Örneğin, bir Base64 string ile dönebilir veya bir dosya yolunu verebilirsiniz
        return "data:" + type + ";base64," + Base64.getEncoder().encodeToString(data);
    }
}
