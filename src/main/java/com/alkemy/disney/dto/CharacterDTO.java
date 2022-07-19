package com.alkemy.disney.dto;

import com.alkemy.disney.entity.MovieEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CharacterDTO {

    private Long id;

    private String image;

    private String name;

    private Integer age;

    private double weight;

    private String history;

    private List<MovieEntity> movies;
}

