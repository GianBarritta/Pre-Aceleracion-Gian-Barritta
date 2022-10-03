package com.alkemy.disney.service.impl;

import com.alkemy.disney.dto.MovieBasicDTO;
import com.alkemy.disney.dto.MovieDTO;
import com.alkemy.disney.dto.MovieFiltersDTO;
import com.alkemy.disney.entity.CharacterEntity;
import com.alkemy.disney.entity.MovieEntity;
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
        MovieEntity entity = movieMapper.movieDTO2Entity(dto);
        MovieEntity savedEntity = movieRepository.save(entity);
        savedEntity.setGenre(genreService.getGenreEntityById(savedEntity.getGenreId()));
        return movieMapper.movieEntity2DTO(savedEntity, true);
    }

    public MovieDTO getMovieDTOById(Long id){
        MovieEntity entity = getMovieById(id);
        return movieMapper.movieEntity2DTO(entity, true);
    }

    public List<MovieBasicDTO> getByFilters(String name, Long genreId, String order) {
        MovieFiltersDTO filtersDTO = new MovieFiltersDTO(name, genreId, order);
        List<MovieEntity> entities = movieRepository.findAll(movieSpecification.getByFilters(filtersDTO));
        return movieMapper.movieEntityCollection2BasicDTOList(entities);
    }

    public MovieDTO updateMovie(Long id, MovieDTO movieDTO) {
        Optional<MovieEntity> entity = movieRepository.findById(id);

        if(entity.isEmpty()) {
            throw new ParamNotFound("ID de película para modificar no encontrado");
        }
        MovieEntity movie = getMovieById(id);
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
        MovieEntity movie = getMovieById(movieId);
        CharacterEntity character = getCharacterEntityById(characterId);
        movie.addCharacter(character);
        movieRepository.save(movie);
        return movieMapper.movieEntity2DTO(movie, true);
    }

    public MovieDTO removeCharacter(Long movieId, Long characterId) {
        MovieEntity movie = getMovieById(movieId);
        CharacterEntity character = getCharacterEntityById(characterId);
        movie.removeCharacter(character);
        movieRepository.save(movie);
        return movieMapper.movieEntity2DTO(movie, true);
    }

    public void delete(Long id) {
        MovieEntity movie = getMovieById(id);
        movieRepository.delete(movie);
    }

    private MovieEntity getMovieById(Long id) {
        Optional<MovieEntity> entity = movieRepository.findById(id);
        if(entity.isEmpty()){
            throw new ParamNotFound("Película con ID: " + id + " no encontrado");
        }
        return entity.get();
    }

    private CharacterEntity getCharacterEntityById(Long id) {
        Optional<CharacterEntity> character = characterRepository.findById(id);
        if(character.isEmpty()){
            throw new ParamNotFound("Personaje con ID: " + id + " no encontrado");
        }
        return character.get();
    }
}
