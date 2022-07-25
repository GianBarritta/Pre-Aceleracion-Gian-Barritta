package com.alkemy.disney.service;

import com.alkemy.disney.dto.MovieBasicDTO;
import com.alkemy.disney.dto.MovieDTO;
import com.alkemy.disney.entity.MovieEntity;
import com.alkemy.disney.exception.ParamNotFound;
import com.alkemy.disney.repository.CharacterRepository;
import com.alkemy.disney.repository.GenreRepository;
import com.alkemy.disney.repository.MovieRepository;
import com.alkemy.disney.service.impl.IMovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional
@Service
public class MovieService implements IMovieService {

    private final MovieRepository movieRepository;

    private final GenreRepository genreRepository;

    private final CharacterRepository characterRepository;

    @Override
    public Set<MovieEntity> getAll() {
        return movieRepository.findAll();
    }

    @Override
    public Set<MovieEntity> findAllOrderByCreationDate(String order) {
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
    public Set<MovieEntity> findByTitle(String title) {
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
    public Set<MovieEntity> findByGenreId(Long idGenre) {
        return movieRepository.findByGenresId(idGenre);
    }

    private boolean checkGenreExistence(List<Long> genreId) {
        return genreRepository.findAll().stream().map(Genre::getId).collect(Collectors.toList()).containsAll(genreIds);
    }

    public void addGenreLong (movieId Set<Long> genreId) {
        MovieEntity movieEntity = findById(movieId);
        if (checkGenreExistence(genreId)) {
            genreRepository.findAllById(genreId).forEach(genre -> movieEntity.getGenre().add(genre));
        } else {
            throw new ParamNotFound("Make sure all movies you want to add to the character already exist on the server");
        }
        movieRepository.save(movieEntity);
    }

    @Override
    public Set<MovieDTO> returnEmptyMovieDTO() {
        Set<MovieDTO> emptyMovies = new HashSet<>();
        return emptyMovies;
    }

    @Override
    public Set<MovieBasicDTO> getByFilters(String name, String genre, String order, Set<MovieEntity> moviesAssociated) {
        return null;
    }

    @Override
    public MovieDTO removeCharacter(Long id, Long characterId){

    }
}
