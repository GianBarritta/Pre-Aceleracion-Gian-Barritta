package com.alkemy.disney.service.impl;

import com.alkemy.disney.dto.MovieBasicDTO;
import com.alkemy.disney.dto.MovieDTO;
import com.alkemy.disney.dto.MovieFiltersDTO;
import com.alkemy.disney.entity.Character;
import com.alkemy.disney.entity.Movie;
import com.alkemy.disney.exception.ParamNotFound;
import com.alkemy.disney.mapper.MovieMapper;
import com.alkemy.disney.repository.CharacterRepository;
import com.alkemy.disney.repository.MovieRepository;
import com.alkemy.disney.repository.specifications.MovieSpecification;
import com.alkemy.disney.service.GenreService;
import com.alkemy.disney.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Transactional
@Service
public class IMovieService implements MovieService {

    private final MovieMapper movieMapper;

    private final MovieRepository movieRepository;

    private final GenreService genreService;

    private final MovieSpecification movieSpecification;

    private final CharacterRepository characterRepository;

    public MovieDTO save(MovieDTO dto) {
        Movie entity = movieMapper.movieDTO2Entity(dto);
        Movie savedEntity = movieRepository.save(entity);
        savedEntity.setGenre(genreService.getGenreEntityById(savedEntity.getGenreId()));
        return movieMapper.movieEntity2DTO(savedEntity, true);
    }

    public MovieDTO getMovieDTOById(Long id){
        Movie entity = getMovieById(id);
        return movieMapper.movieEntity2DTO(entity, true);
    }

    public List<MovieBasicDTO> getByFilters(String name, Long genreId, String order) {
        MovieFiltersDTO filtersDTO = new MovieFiltersDTO(name, genreId, order);
        List<Movie> entities = movieRepository.findAll(movieSpecification.getByFilters(filtersDTO));
        return movieMapper.movieEntityCollection2BasicDTOList(entities);
    }

    public MovieDTO updateMovie(Long id, MovieDTO movieDTO) {
        Movie movie = getMovieById(id);
        movie.setTitle(movieDTO.getTitle());
        movie.setImage(movieDTO.getImage());
        movie.setCreationDate(movieDTO.getCreationDate());
        movie.setScore(movieDTO.getScore());
        movie.setGenreId(movieDTO.getGenreId());
        movie.setGenre(genreService.getGenreEntityById(movie.getGenreId()));
        movieRepository.save(movie);
        return movieMapper.movieEntity2DTO(movie, false);
    }

    public MovieDTO addCharacter(Long movieId, Long characterId) {
        Movie movie = getMovieById(movieId);
        Character character = getCharacterEntityById(characterId);
        movie.addCharacter(character);
        movieRepository.save(movie);
        return movieMapper.movieEntity2DTO(movie, true);
    }

    public MovieDTO removeCharacter(Long movieId, Long characterId) {
        Movie movie = getMovieById(movieId);
        Character character = getCharacterEntityById(characterId);
        movie.removeCharacter(character);
        movieRepository.save(movie);
        return movieMapper.movieEntity2DTO(movie, true);
    }

    public void delete(Long id) {
        Movie movie = getMovieById(id);
        movieRepository.delete(movie);
    }

    private Movie getMovieById(Long id) {
        Optional<Movie> entity = movieRepository.findById(id);
        if(entity.isEmpty()){
            throw new ParamNotFound("Película con ID: " + id + " no encontrado");
        }
        return entity.get();
    }

    private Character getCharacterEntityById(Long id) {
        Optional<Character> character = characterRepository.findById(id);
        if(character.isEmpty()){
            throw new ParamNotFound("Personaje con ID: " + id + " no encontrado");
        }
        return character.get();
    }
}
