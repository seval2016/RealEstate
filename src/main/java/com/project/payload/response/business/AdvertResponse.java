package com.project.payload.response.business;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.project.entity.concretes.business.*;
//import com.project.entity.enums.Status;
import com.project.entity.concretes.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AdvertResponse {

    private Long id;
    private String title;
    private String description;
    //private String slug;
    private Float price;
    private int status; // primitive ou non-primitive ?
    private Boolean builtIn = false; // bunu dusunelim
    private Boolean isActive = true;
    private String location;
    private LocalDate createdAt;
    private LocalDate updatedAt;
    private int viewCount; // primitive ou non-primitive ?
    private AdvertType advertType;
//    private Country country;
//    private City city;
//    private District district;
//    private Category category;
   // private User username;

}
