package com.project.payload.response.business.advert;

import com.project.entity.concretes.business.Advert;
import com.project.entity.image.Images;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;



@NoArgsConstructor
@AllArgsConstructor
public class AdvertResponseForTourRequest extends Advert {

    private Long id; // İlan ID'si
    private String title; // İlan başlığı
    private String description; // İlan açıklaması
    private BigDecimal price; // İlan fiyatı
    private String location; // İlanın lokasyonu
    private List<String> images; // Resim URL'leri
    private List<String> tourRequests; // Tur talepleri
    private LocalDateTime createAt; // Oluşturulma tarihi
    private LocalDateTime updateAt; // Güncellenme tarihi






}
