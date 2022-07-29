package com.alkemy.disney.mapper;

import com.alkemy.disney.dto.CharacterDTO;
import com.alkemy.disney.dto.MovieBasicDTO;
import com.alkemy.disney.dto.MovieDTO;
import com.alkemy.disney.entity.CharacterEntity;
import com.alkemy.disney.entity.MovieEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Component
public class MovieMapper {

    @Autowired
    private final CharacterMapper characterMapper;

    public MovieMapper(CharacterMapper characterMapper) {
        this.characterMapper = characterMapper;
    }

    //conversión MovieDTO a MovieEntity
    public MovieEntity movieDTO2Entity(MovieDTO movieDTO){
        MovieEntity entity = new MovieEntity();
        entity.setImage(movieDTO.getImage());
        entity.setTitle(movieDTO.getTitle());
        entity.setScore(movieDTO.getScore());
        entity.setCreationDate(string2LocalDate(movieDTO.getCreationDate().toString()));
        entity.setGenreId(movieDTO.getGenreId());
        Set<CharacterEntity> characterEntities = characterMapper.characterDTOSet2EntitySet(movieDTO.getCharacters());
        entity.setCharacters(characterEntities);

        return entity;
    }

    //conversión MovieEntity a MovieDTO
    public MovieDTO movieEntity2DTO(MovieEntity entity, boolean b){
        MovieDTO movieDTO = new MovieDTO();
        movieDTO.setId(entity.getId());
        movieDTO.setImage(entity.getImage());
        movieDTO.setTitle(entity.getTitle());
        movieDTO.setCreationDate(string2LocalDate(entity.getCreationDate().toString()));
        movieDTO.setScore(entity.getScore());
        movieDTO.setGenreId(entity.getGenreId());

        if(b){
            Set<CharacterDTO> characterDTOS = characterMapper.characterEntitySet2DTOSet(entity.getCharacters(),false);
            movieDTO.setCharacters(characterDTOS);
        }
        return movieDTO;
    }

    //conversión MovieEntitySet a MovieDTOSet
    public Set<MovieDTO> movieEntity2DTOSet(Set<MovieEntity> movieSet, boolean b){
        Set<MovieDTO> movieDTOSet = new HashSet<>();
        for (MovieEntity entity: movieSet){
            movieDTOSet.add(movieEntity2DTO(entity,b));
        }
        return movieDTOSet;
    }

    //conversión MovieEntity a MovieBasicDTO
    public MovieBasicDTO movieEntity2BasicDTO(MovieEntity entity) {
        MovieBasicDTO dto = new MovieBasicDTO();
        dto.setImage(entity.getImage());
        dto.setTitle(entity.getTitle());
        dto.setCreationDate(string2LocalDate(entity.getCreationDate().toString()));
        return dto;
    }

    //conversión String a LocalDate
    private LocalDate string2LocalDate(String stringDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(stringDate, formatter);
    }

    //conversión de MovieEntityCollection a MovieBasicDTOSet
    public Set<MovieBasicDTO> movieEntityCollection2BasicDTOSet(Collection<MovieEntity> entities) {
        Set<MovieBasicDTO> DTOS = new HashSet<>();
        for (MovieEntity entity : entities){
            DTOS.add(movieEntity2BasicDTO(entity));
        }
        return DTOS;
    }

    //se modifica la información de la Entidad con la del DTO
    public void modifyMovieRefreshValues(MovieEntity entity, MovieDTO movieDTO) {
        entity.setImage(movieDTO.getImage());
        entity.setTitle(movieDTO.getTitle());
        entity.setCreationDate(string2LocalDate(movieDTO.getCreationDate().toString()));
        entity.setScore(movieDTO.getScore());
        entity.setGenreId(movieDTO.getGenreId());
        Set<CharacterEntity> characterEntities = characterMapper.characterDTOSet2EntitySet(movieDTO.getCharacters());
    }
}