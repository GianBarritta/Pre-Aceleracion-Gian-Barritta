package com.alkemy.disney.service;

import com.alkemy.disney.dto.MovieDTO;
import com.alkemy.disney.entity.GenreEntity;
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

     List<GenreEntity> getGenre(Long id);

     void addGenre(Long movieId, List<Long> genreIds);

     void removeGenre(Long movieId, List<Long> genreIds);

     List<MovieDTO> returnEmptyMovieDto();

 }
