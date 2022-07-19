package com.alkemy.disney.service.impl;

import com.alkemy.disney.dto.MovieDTO;
import com.alkemy.disney.entity.GenreEntity;
import com.alkemy.disney.entity.MovieEntity;
import com.alkemy.disney.exception.ParamNotFound;
import com.alkemy.disney.repository.GenreRepository;
import com.alkemy.disney.repository.MovieRepository;
import com.alkemy.disney.service.IMovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional
@Service
public class MovieService implements IMovieService {

    private final MovieRepository movieRepository;
    private final GenreRepository genreRepository;

    @Override
    public List<MovieEntity> getAll() {
        return movieRepository.findAll();
    }

    @Override
    public List<MovieEntity> findAllOrderByCreationDate(String order) {
        if(order.equalsIgnoreCase("ASC")) {return movieRepository.findAllByOrderByCreationDateAsc();
        } else if (order.equalsIgnoreCase("DESC")) {
            return movieRepository.findAllByOrderByCreationDateDesc();
        }
        return null;
    }

    @Override
    public MovieEntity findById(Long movieId) {
        return movieRepository.findById(movieId).orElseThrow(() -> new ParamNotFound("No Movie with ID : " + movieId));
    }

    @Override
    public List<MovieEntity> findByTitle(String title) {
        return movieRepository.findByTitle(title);
    }

    @Override
    public void delete(Long id){
        movieRepository.delete(findById(id));
    }

    @Override
    public MovieEntity save(MovieEntity movieEntity) {
        return movieRepository.save(movieEntity);
    }

    @Override
    public List<MovieEntity> findByGenreId(Long idGenre) {
        return movieRepository.findByGenresId(idGenre);
    }

    @Override
    public List<GenreEntity> getGenre(Long id) {
        return null;
    }

    @Override
    public void addGenre(Long movieId, List<Long> genreIds) {

    }

    private boolean checkGenresExistence(List<Long> genresIds) {
        return genreRepository.findAll().stream().map(Genre::getId).collect(Collectors.toList()).containsAll(genresIds);
    }

    @Override
    public List<GenreEntity> getGenre(Long id) {
        return findById(id).getGenre();
    }

    public void addGenres(Long movieId, List<Long> genresIds) {
        MovieEntity movieEntity = findById(movieId);
        if (checkGenresExistence(genresIds)) {
            genreRepository.findAllById(genresIds).forEach(genre -> movieEntity.getGenre().add(genre));
        } else {
            throw new ParamNotFound("Make sure all movies you want to add to the character already exist on the server");
        }
        movieRepository.save(movieEntity);
    }

    @Override
    public void removeGenre(Long movieId, List<Long> genresIds) {

    }

    @Override
    public void removeGenres(Long movieId, List<Long> genreId) {
        MovieEntity movieEntity = findById(movieId);
        movieEntity.getGenre().removeIf(genre -> genreId.contains(genre.getId()));
        movieRepository.save(movieEntity);
    }

    @Override
    public List<MovieDTO> returnEmptyMovieDto() {
        List<MovieDTO> emptyMovies = new ArrayList<MovieDTO>();
        return emptyMovies;
    }
}
