package com.project.entity.concretes.business;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.entity.enums.ContactStatus;
import lombok.*;


import javax.persistence.*;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Entity
@Table(name = "contacts")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,length = 30)
    private String firstName;

    @Column(nullable = false,length = 30)
    private String lastName;

    @Column(nullable = false,length = 60)
    private String email;

    @Column(nullable = false,length = 300)
    private String message;

    @Column(name = "status", nullable = false)
    private int status = ContactStatus.NOT_OPENED.getStatusValue(); // Default value

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime createAt;

    @PrePersist
    protected void onCreate() {
        createAt = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
    }

}