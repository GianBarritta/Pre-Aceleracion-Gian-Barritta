package com.alkemy.disney.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MovieBasicDTO {

    private Long id;

    private String image;

    private String title;

    private LocalDateTime creationDate;

    private Integer score;
}
