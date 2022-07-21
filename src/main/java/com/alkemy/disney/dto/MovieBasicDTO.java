package com.alkemy.disney.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MovieBasicDTO {

    private Long id;

    private String image;

    @NotNull
    private String title;

    private LocalDateTime creationDate;

    @Min(1)
    @Max(5)
    private Integer score;
}
