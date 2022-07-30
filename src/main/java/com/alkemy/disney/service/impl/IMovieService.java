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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Transactional
@Service
public class IMovieService implements MovieService {

    @Autowired
    private final MovieMapper movieMapper;

    @Autowired
    private final MovieRepository movieRepository;

    @Autowired
    private GenreService genreService;

    @Autowired
    private final MovieSpecification movieSpecification;

    @Autowired
    private final CharacterRepository characterRepository;

    //busca película por su id
    public MovieEntity getMovieEntityById(Long id) {
        Optional<MovieEntity> movie = movieRepository.findById(id);
        if(movie.isEmpty()){
            throw new ParamNotFound("Película con ID: " + id + " no encontrado");
        }
        return movie.get();
    }

    //busca personaje por su id
    public CharacterEntity getCharacterEntityById(Long id) {
        Optional<CharacterEntity> character = characterRepository.findById(id);
        if(character.isEmpty()){
            throw new ParamNotFound("Personaje con ID: " + id + " no encontrado");
        }
        return character.get();
    }

    //busca una identificación en el repositorio de peliculas
    public MovieEntity getMovieById(Long id) {
        Optional<MovieEntity> entity = movieRepository.findById(id);
        if(!entity.isPresent()){
            throw new ParamNotFound("ID de película no encontrado");
        }
        return entity.get();
    }

    //busca una identificación en el repositorio de películas y convierte la entidad en un DTO
    public MovieDTO getMovieDTOById(Long id){
        Optional<MovieEntity> entity = movieRepository.findById(id);
        if(!entity.isPresent()) {
            throw new ParamNotFound("ID de película no encontrado");
        }
        MovieDTO movieDTO = movieMapper.movieEntity2DTO(entity.get(), true);
        return movieDTO;
    }

    //búsqueda de películas por filtro (nombre y género (tiene orden ASC/DESC))
    public List<MovieBasicDTO> getByFilters(String name, Long genreId, String order) {
        MovieFiltersDTO filtersDTO = new MovieFiltersDTO(name, genreId, order);
        List<MovieEntity> entities = movieRepository.findAll(movieSpecification.getByFilters(filtersDTO));
        List<MovieBasicDTO> dtos = movieMapper.movieEntityCollection2BasicDTOList(entities);
        return dtos;
    }

    //guarda la película en el repositorio
    public MovieDTO save(MovieDTO dto) {
        MovieEntity entity = movieMapper.movieDTO2Entity(dto);
        MovieEntity savedEntity = movieRepository.save(entity);
        MovieDTO result = movieMapper.movieEntity2DTO(savedEntity, true);
        return result;
    }

    //invoca un método de mapeador y modifica los valores de película
    public MovieDTO updateMovie(Long id, MovieDTO movieDTO) {
        Optional<MovieEntity> entity = movieRepository.findById(id);

        if(!entity.isPresent()) {
            throw new ParamNotFound("ID de título para modificar no encontrado");
        }
        MovieEntity movie = getMovieEntityById(id);
        movie.setTitle(movieDTO.getTitle());
        movie.setImage(movieDTO.getImage());
        movie.setCreationDate(movieDTO.getCreationDate());
        movie.setScore(movieDTO.getScore());
        movie.setGenreId(movieDTO.getGenreId());
        movie.setGenre(genreService.getGenreEntityById(movie.getGenreId()));
        movieRepository.save(movie);
        return movieMapper.movieEntity2DTO(movie, false);
    }

    //agrega un personaje a una película
    public MovieDTO addCharacter(Long movieId, Long characterId) {
        MovieEntity movie = getMovieEntityById(movieId);
        CharacterEntity character = getCharacterEntityById(characterId);
        movie.addCharacter(character);
        movieRepository.save(movie);
        return movieMapper.movieEntity2DTO(movie, true);
    }

    //remueve un personaje de una película
    public MovieDTO removeCharacter(Long movieId, Long characterId) {
        MovieEntity movie = getMovieEntityById(movieId);
        CharacterEntity character = getCharacterEntityById(characterId);
        movie.removeCharacter(character);
        movieRepository.save(movie);
        return movieMapper.movieEntity2DTO(movie, true);
    }

    //Soft Delete de una película guardada en el repositorio
    public void delete(Long id) {
        Optional<MovieEntity>entity = movieRepository.findById(id);
        if(!entity.isPresent())
        {
            throw new ParamNotFound("ID de título no encontrado, no se pudo eliminar");
        }
        movieRepository.deleteById(id);
    }
}
