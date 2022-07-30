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
import java.util.*;

@Component
public class MovieMapper {

    @Autowired
    private CharacterMapper characterMapper;

    //conversión MovieDTO a MovieEntity
    public MovieEntity movieDTO2Entity(MovieDTO movieDTO){
        MovieEntity entity = new MovieEntity();
        entity.setImage(movieDTO.getImage());
        entity.setTitle(movieDTO.getTitle());
        entity.setScore(movieDTO.getScore());
        entity.setCreationDate(string2LocalDate(movieDTO.getCreationDate().toString()));
        entity.setGenreId(movieDTO.getGenreId());
        for (CharacterDTO characterDTO : movieDTO.getCharacters()) {
            CharacterEntity character = characterMapper.characterDTO2Entity(characterDTO);
            entity.getCharacters().add(character);
        }
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
            Set<CharacterDTO> characterDTOS = characterMapper.characterEntityCollection2DTOSet(entity.getCharacters(),false);
            movieDTO.setCharacters(characterDTOS);
        }
        return movieDTO;
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

    //conversión de MovieEntityCollection a MovieDTOSet
    public Set<MovieDTO> movieEntityCollection2DTOSet(Collection<MovieEntity> entities, boolean loadCharacters){
        Set<MovieDTO> DTOs = new HashSet<>();
        for (MovieEntity entity : entities){
            DTOs.add(movieEntity2DTO(entity, loadCharacters));
        }
        return DTOs;
    }

    //conversión de MovieEntityCollection a MovieDTOList
    public List<MovieDTO> movieEntityCollection2DTOList(Collection<MovieEntity> entities, boolean loadCharacters){
        List<MovieDTO> DTOs = new ArrayList<>();
        for (MovieEntity entity : entities){
            DTOs.add(movieEntity2DTO(entity, loadCharacters));
        }
        return DTOs;
    }

    //conversión de MovieEntityCollection a MovieBasicDTOList
    public List<MovieBasicDTO> movieEntityCollection2BasicDTOList(Collection<MovieEntity> entities) {
        List<MovieBasicDTO> DTOs = new ArrayList<>();
        for (MovieEntity entity : entities){
            DTOs.add(movieEntity2BasicDTO(entity));
        }
        return DTOs;
    }
}