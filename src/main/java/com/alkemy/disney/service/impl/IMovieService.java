package com.alkemy.disney.service.impl;

import com.alkemy.disney.dto.MovieBasicDTO;
import com.alkemy.disney.dto.MovieDTO;
import com.alkemy.disney.entity.MovieEntity;

import java.util.List;

public interface IMovieService {

     List<MovieEntity> getAll();

     List<MovieEntity> findAllOrderByCreationDate(String order);

     MovieEntity findById(Long movieId);

     List<MovieEntity> findByTitle(String title);

     void delete(Long id);

     MovieEntity save(MovieEntity movieEntity);

     List<MovieEntity> findByGenreId(Long idGenre);

     List<MovieDTO> returnEmptyMovieDTO();

     List<MovieBasicDTO> getByFilters(String name, String genre, String order, List<MovieEntity> moviesAssociated);
}
