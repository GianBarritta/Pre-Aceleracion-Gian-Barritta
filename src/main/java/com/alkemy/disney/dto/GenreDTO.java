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
public class GenreDTO {

    private Long id;

    private String image;

    private String name;

    private List<MovieEntity> movies;
}
