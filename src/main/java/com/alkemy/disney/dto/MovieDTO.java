package com.alkemy.disney.dto;

import com.alkemy.disney.entity.Genre;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    private Integer score;

    private Genre genre;

    private Long genreId;

    private Set<CharacterDTO> characters = new HashSet<>();
}
