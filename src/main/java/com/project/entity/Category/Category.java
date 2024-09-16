package com.project.entity.Category;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "categories")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Category  implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
  //  @Setter(AccessLevel.NONE)  //id yi erişilmez kılar
    private Long id;

    @NotNull(message = "title can not be null")
    @Column(nullable = false, length = 150)
    private String title;

    @Column(nullable = false, length = 50)
    private String icon;

    @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT false")
    private Boolean builtIn = false;

    @Column(nullable = false, columnDefinition = "INT DEFAULT 0")
    private int seq = 0;

    @Column(nullable = false, length = 200)
    private String slug;

    @NotNull(message = "is_active can not be null")
    @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT true")
    private Boolean isActive = true;

    @Column(nullable = false)
    private LocalDateTime createAt;

    @Column(nullable = true)
    private LocalDateTime updateAt;
}