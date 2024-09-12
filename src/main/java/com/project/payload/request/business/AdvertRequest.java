package com.project.payload.request.business;

import com.fasterxml.jackson.annotation.JsonFormat;
//import com.project.entity.enums.Status;
import com.project.entity.concretes.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Set;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class AdvertRequest {

 //   private User user; // sonradan ekledim , dusunelim

    @NotNull
    @Size(min = 5, max = 150)
    private String title;

    @Column(length = 300)
    private String description;

//    @NotNull(message = " Slug must not be empty")
//    //@Range(min = 5, max = 200)
//    @Column(nullable = false, length = 200)
//    private String slug;

    @NotNull(message = " Price  must not be empty")
    private Float price;

    @NotNull(message = " Status must not be empty")
    //@Enumerated(EnumType.STRING)
    @Min(0)
    @Max(2)
    private int status = 0; //= Status.values()[0];

    @NotNull
    private Boolean builtIn = false;


    @NotNull(message = " View Count must not be empty")
    private int viewCount = 0;


    @NotNull
    private Boolean isActive = true;


    @NotNull
    private String location;

    @NotNull(message = " Create date must not be empty")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate createdAt;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate updatedAt;

    //************************************************************************************************
//    @NotNull(message = "Please enter username")
//    private Long userId;

    //************************************************************************************************
}
