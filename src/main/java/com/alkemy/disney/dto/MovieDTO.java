package com.alkemy.disney.dto;

import com.alkemy.disney.entity.CharacterEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MovieDTO {

    private Long id;

    private String image;

    @NotNull
    private String title;

    private LocalDateTime creationDate;

    @Min(1)
    @Max(5)
    private Integer score;

    private Set<CharacterEntity> characters;
}
