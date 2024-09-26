package com.project.payload.request.business;

import com.project.payload.request.abstracts.AbstractAdvertRequest;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;


@NoArgsConstructor
@SuperBuilder
@Getter
@Setter
public class AdvertRequest extends AbstractAdvertRequest {


}