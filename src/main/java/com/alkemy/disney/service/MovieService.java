package com.alkemy.disney.service;

import com.alkemy.disney.dto.MovieDTO;
import com.alkemy.disney.dto.MovieFiltersDTO;
import com.alkemy.disney.entity.CharacterEntity;
import com.alkemy.disney.entity.MovieEntity;
import com.alkemy.disney.exception.ParamNotFound;
import com.alkemy.disney.mapper.MovieMapper;
import com.alkemy.disney.repository.CharacterRepository;
import com.alkemy.disney.repository.MovieRepository;
import com.alkemy.disney.repository.specifications.MovieSpecification;
import com.alkemy.disney.service.impl.IMovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Transactional
@Service
public class MovieService implements IMovieService {

    @Autowired
    private MovieMapper movieMapper;
    @Autowired
    private MovieService movieService;
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private CharacterService characterService;
    @Autowired
    private MovieSpecification movieSpecification;
    @Autowired
    private CharacterRepository characterRepository;

    //guarda la película en el repositorio
    public MovieDTO save(MovieDTO dto)
    {
        MovieEntity entity = movieMapper.movieDTO2Entity(dto);
        MovieEntity savedEntity = movieRepository.save(entity);
        MovieDTO result = movieMapper.movieEntity2DTO(savedEntity, true);
        return result;
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
    public List<MovieDTO> getByFilters(String name, Long genreId, String order)
    {
        MovieFiltersDTO filtersDTO = new MovieFiltersDTO(name, genreId, order);
        List<MovieEntity> entities = movieRepository.findAll(movieService.getByFilters(filtersDTO));
        List<MovieDTO> dtos = movieMapper.movieEntity2DTOSet(entities,true);

        return dtos;
    }

    //invoca un método de mapeador y modifica los valores de película
    public MovieDTO updateMovie(Long id, MovieDTO movieDTO){
        Optional<MovieEntity> entity = movieRepository.findById(id);

        if(!entity.isPresent())
        {
            throw new ParamNotFound("ID de título para modificar no encontrado");
        }
        this.movieMapper.modifyMovieRefreshValues(entity.get(),movieDTO);
        MovieEntity savedEntity = this.movieRepository.save(entity.get());
        MovieDTO result = this.movieMapper.movieEntity2DTO(savedEntity, true);

        return result;
    }

    public MovieDTO addCharacter(Long movieId, Long characterId) {
        MovieEntity movie = getMovieEntityById(movieId);
        CharacterEntity character = getCharacterEntityById(characterId);
        movie.addCharacter(character);
        return movieMapper.movieEntity2DTO(movie, true);
    }

    public MovieDTO removeCharacter(Long movieId, Long characterId) {
        MovieEntity movie = getMovieEntityById(movieId);
        CharacterEntity character = getCharacterEntityById(characterId);
        movie.removeCharacter(character);
        return movieMapper.movieEntity2DTO(movie, true);
    }

    //Soft Delete de una película guardada en el repositorio
    public void delete(Long id)
    {
        Optional<MovieEntity>entity = movieRepository.findById(id);
        if(!entity.isPresent())
        {
            throw new ParamNotFound("ID de título no encontrado, no se pudo eliminar");
        }
        movieRepository.deleteById(id);
    }

    private MovieEntity getMovieEntityById(Long id) {
        Optional<MovieEntity> movie = movieRepository.findById(id);
        if(movie.isEmpty()){
            throw new ParamNotFound("Película con ID: " + id + " no encontrado");
        }
        return movie.get();
    }

    private CharacterEntity getCharacterEntityById(Long id) {
        Optional<CharacterEntity> character = characterRepository.findById(id);
        if(character.isEmpty()){
            throw new ParamNotFound("Personaje con ID: " + id + " no encontrado");
        }
        return character.get();
    }
}
