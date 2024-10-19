package com.project.payload.request.business;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.File;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class ImagesRequest {


    private List<File> Images;

    @NotNull(message = "Advert id cannot be null")
    private Long Advert_id;


    @NotNull(message = "Featured cannot be null")
    private Boolean featured;

    @NotBlank(message = "Type cannot be blank")
    private String type;

    @NotNull(message = "Data cannot be null")
    private byte[] data;

    @NotBlank(message = "Name cannot be blank")
    private String name;

    @NotNull(message = "Advert ID cannot be null")
    private Long advertId; // Advert ID'yi almak için

    private String path; // Resim yolunu saklamak için

    // Eğer URL oluşturmak için bir mantık eklemek isterseniz
    // @NotBlank(message = "URL cannot be blank") gibi bir doğrulama ekleyebilirsiniz







}
