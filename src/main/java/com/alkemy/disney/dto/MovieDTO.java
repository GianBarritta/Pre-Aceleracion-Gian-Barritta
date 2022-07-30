package com.alkemy.disney.dto;

import com.alkemy.disney.entity.GenreEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MovieDTO {

    private long id;

    private String image;

    @NotNull
    private String title;

    private LocalDate creationDate;

    @Min(value = 1, message = "El minimo es 1")
    @Max(value = 5, message = "El maximo es 5")
    private Integer score;

    private GenreEntity genre;

    private Long genreId;

    private Set<CharacterDTO> characters = new HashSet<>();
}
