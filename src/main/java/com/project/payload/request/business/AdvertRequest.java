package com.project.payload.request.business;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class AdvertRequest {


    @NotNull(message = " Title must not be empty")
    @Size(min = 5, max = 150)
    private String title;

    @NotNull(message = " Description must not be empty")
    @Size(max = 300)
    private String desc;

    /*
    @NotNull ( message = " Slug must not be empty")
    @Size(min = 5, max = 200)
    private String slug;
    */

    @NotNull(message = " Price  must not be empty")
    private Float price;

/*    @NotNull(message = " Status must not be empty")
    private int status = 0;*/

    /*

    @NotNull
    private Boolean builtIn =false;

    @NotNull
    private Boolean isActive = true;

    @NotNull(message = " View Count must not be empty")
    private int viewCount = 0;

    */

    private String location;

    @NotNull(message = " Create date must not be empty")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDateTime create_at;

    @Column(nullable = true)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDateTime update_at;
}
