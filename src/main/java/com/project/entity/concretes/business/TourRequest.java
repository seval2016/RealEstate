package com.project.entity.concretes.business;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.entity.concretes.user.User;

import com.project.entity.enums.TourRequestStatus;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "tour_requests")

@Getter
@Setter

@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
//@FieldDefaults(level = AccessLevel.PRIVATE)  Bu anotasyonu kullanarak, sınıf içindeki tüm alanları private olarak ayarlayabilirsiniz,
// böylece her alan için tek tek private erişim belirlemenize gerek kalmaz. //ama kullanmicam
public class TourRequest {


    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;


    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "tour_date", nullable = false)
    private LocalDate tourDate;


    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm", timezone = "US")
    @Column(name = "tour_time", nullable = false)
    private LocalTime tourTime;


    @Column(nullable = false,name = "status")
    private int statusStatus = TourRequestStatus.PENDING.getTourStatusValue();

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "US")
    @Column(name = "create_at", nullable = false)
    private LocalDateTime createAt;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "US")
    @Column(name = "update_at")
    private LocalDateTime updateAt;

    //---------------------------------------------------------

    /*@ManyToOne
    @JoinColumn(name = "advert_id", nullable = false)
    @JsonIgnore
    private Advert advert;*/

    /*@ManyToOne
    @JoinColumn(name = "owner_user", nullable = false)
    @JsonIgnore
    private User ownerUser;*/

    @ManyToOne
    @JoinColumn(name = "guest_user", nullable = false)
    @JsonIgnore
    private User guestUser;





}