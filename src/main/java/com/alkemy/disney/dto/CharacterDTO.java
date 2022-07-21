package com.alkemy.disney.dto;

import com.alkemy.disney.entity.MovieEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CharacterDTO {

    private Long id;

    private String image;

    @NotNull
    private String name;

    private Integer age;

    private double weight;

    private String history;

    private List<MovieEntity> movies;
}

