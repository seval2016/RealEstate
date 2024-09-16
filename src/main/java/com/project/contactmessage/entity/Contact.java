package com.project.contactmessage.entity;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
@Entity
@Table(name = "contacts")

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @Column(nullable = false)
    @Size(max = 30)
    private String firstName;

    @Size(max = 30)
    @Column(nullable = true)
    private String lastName;

    @Size(max = 60)
    @Column(nullable = false)
    private String email;

    @Size(max = 300)
    @Column(nullable = false)
    private String message;


    private int status=0;


    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    @Column(name = "create_at", nullable = false, updatable = false)
    private LocalDateTime createAt;

    @PrePersist
    protected void onCreate() {
        createAt = LocalDateTime.now();
    }

    public ContactStatus getContactStatus() {
        return ContactStatus.fromValue(this.status);
    }

    public void setContactStatus(ContactStatus status) {
        this.status = status.getValue();
    }


}