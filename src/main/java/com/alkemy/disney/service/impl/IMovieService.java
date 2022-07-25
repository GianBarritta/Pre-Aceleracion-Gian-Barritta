package com.alkemy.disney.service.impl;

import com.alkemy.disney.dto.MovieBasicDTO;
import com.alkemy.disney.dto.MovieDTO;
import com.alkemy.disney.entity.MovieEntity;

import java.util.Set;

public interface IMovieService {

     Set<MovieEntity> getAll();

     Set<MovieEntity> findAllOrderByCreationDate(String order);

     MovieEntity findById(Long movieId);

     Set<MovieEntity> findByTitle(String title);

     void delete(Long id);

     MovieEntity save(MovieEntity movieEntity);

     Set<MovieEntity> findByGenreId(Long idGenre);

     Set<MovieDTO> returnEmptyMovieDTO();

     Set<MovieBasicDTO> getByFilters(String name, String genre, String order, Set<MovieEntity> moviesAssociated);
}
