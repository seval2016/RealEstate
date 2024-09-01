package com.project.entity.concretes.business;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
//@Table(name = "logs")
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class Logs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "advert_id")
    private Advert advert;

}