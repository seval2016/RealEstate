package com.project.payload.response.business.image;

import com.project.entity.image.Images;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ImagesResponse {

    private Long id;
    private String name;
    private String type;
    private boolean featured;
    private Long advertId;
    private String url;
    private byte[] data;

    public ImagesResponse(Long id,String name,String type,Boolean featured,Long advertId,
                         byte[] data){
        this.id=id;
        this.name=name;
        this.type=type;
        this.featured=featured;
        this.advertId=advertId;
        this.data=data;

    }

    public ImagesResponse(Long id,String name,String type,Boolean featured){
        this.id=id;
        this.name=name;
        this.type=type;
        this.featured=featured;

    }
    public ImagesResponse(Long id,byte[] data){
        this.id=id;
        this.data=data;

    }

    public ImagesResponse(byte[] data) {

       this.data=data;

    }
}
