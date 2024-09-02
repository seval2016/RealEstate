package com.project.entity.concretes.business;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
//@Table(name = "tour_requests")
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class TourRequests {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "advert_id")
    private Advert advert;

}