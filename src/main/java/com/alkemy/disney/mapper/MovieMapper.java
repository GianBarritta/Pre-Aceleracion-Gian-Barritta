package com.alkemy.disney.mapper;

import com.alkemy.disney.dto.GenreBasicDTO;
import com.alkemy.disney.dto.MovieBasicDTO;
import com.alkemy.disney.dto.MovieDTO;
import com.alkemy.disney.entity.GenreEntity;
import com.alkemy.disney.entity.MovieEntity;

import java.util.List;
import org.mapstruct.*;


@Mapper(componentModel = "spring")
public interface MovieMapper  {

    MovieBasicDTO movieToMovieBasicDTO(MovieEntity movieEntity);

    MovieDTO movieToMovieDTO(MovieEntity movieEntity);

    MovieEntity movieDTOToMovie(MovieDTO movie);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
            nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    MovieEntity updateMovieFromDTO(MovieDTO movieDTO, @MappingTarget MovieEntity movieEntity);

    List<MovieBasicDTO> moviesToMovieBasicDTOS(List<MovieEntity> movies);

    List<MovieDTO> moviesToMovieDTOS(List<MovieEntity> movies);

    List<GenreBasicDTO> genresToGenreBasicDTOS(List<GenreEntity> genres);
}
