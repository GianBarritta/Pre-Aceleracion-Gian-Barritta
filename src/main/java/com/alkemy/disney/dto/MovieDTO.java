package com.alkemy.disney.dto;

import com.alkemy.disney.entity.CharacterEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MovieDTO {

    private Long id;

    private String image;

    private String title;

    private LocalDateTime creationDate;

    private Integer score;

    private Set<CharacterEntity> characters;
}
