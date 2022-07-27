package com.alkemy.disney.dto;

import com.alkemy.disney.entity.CharacterEntity;
import com.alkemy.disney.entity.GenreEntity;
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

    private long id;

    private String image;

    @NotNull
    private String title;

    private LocalDateTime creationDate;

    @Min(1)
    @Max(5)
    private Integer score;

    private GenreEntity genre;

    private Long genreId;

    private Set<CharacterEntity> characters;
}
