package com.project.entity.concretes.business;
import lombok.*;
import javax.persistence.*;

@Entity
@Table(name = "countries")

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length =30)
    private String name;
}
