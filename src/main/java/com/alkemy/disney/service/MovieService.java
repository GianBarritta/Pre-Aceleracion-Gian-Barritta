package com.alkemy.disney.service;

import com.alkemy.disney.dto.MovieDTO;
import com.alkemy.disney.entity.MovieEntity;

import java.util.Set;

public interface MovieService {

     MovieDTO save(MovieEntity dto);

     MovieEntity getMovieById(Long id);

     Set<MovieDTO> getByFilters(String name, Long genreId, String order);

     MovieDTO getMovieDTOById(Long id);

     MovieDTO updateMovie(Long id, MovieDTO movieDTO);

     void delete(Long id);

     MovieDTO addCharacter(Long movieId, Long characterId);

     MovieDTO removeCharacter(Long movieId, Long characterId);
}
