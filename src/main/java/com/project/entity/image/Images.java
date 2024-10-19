package com.project.entity.image;

import com.project.entity.concretes.business.Advert;
import com.project.payload.response.business.image.ImagesResponse;
import lombok.*;

import javax.persistence.*;
import java.util.Base64;


@Entity
@Table(name="images")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Images extends ImagesResponse {


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




    private String url;





    public String getUrl() {
        // Burada URL oluşturma mantığını ekleyin
        // Örneğin, bir Base64 string ile dönebilir veya bir dosya yolunu verebilirsiniz
        return "data:" + type + ";base64," + Base64.getEncoder().encodeToString(data);
    }







}
