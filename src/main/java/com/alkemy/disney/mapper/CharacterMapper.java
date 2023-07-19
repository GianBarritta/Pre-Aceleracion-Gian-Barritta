package com.alkemy.disney.mapper;

import com.alkemy.disney.dto.CharacterBasicDTO;
import com.alkemy.disney.dto.CharacterDTO;
import com.alkemy.disney.dto.MovieDTO;
import com.alkemy.disney.entity.Character;
import com.alkemy.disney.entity.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class CharacterMapper {

    @Autowired
    private MovieMapper movieMapper;

    //conversión CharacterDTO a Character
    public Character characterDTO2Entity(CharacterDTO characterDTO){
        Character entity = new Character();
        entity.setImage(characterDTO.getImage());
        entity.setName(characterDTO.getName());
        entity.setAge(characterDTO.getAge());
        entity.setWeight(characterDTO.getWeight());
        entity.setHistory(characterDTO.getHistory());
        for (MovieDTO movieDTO : characterDTO.getMovies()) {
            Movie movie = movieMapper.movieDTO2Entity(movieDTO);
            entity.getMovies().add(movie);
        }
        return entity;
    }

    //conversión Character a CharacterDTO
    public CharacterDTO characterEntity2DTO(Character entity, boolean b){
        CharacterDTO characterDTO = new CharacterDTO();
        characterDTO.setId(entity.getId());
        characterDTO.setImage(entity.getImage());
        characterDTO.setName(entity.getName());
        characterDTO.setAge(entity.getAge());
        characterDTO.setWeight(entity.getWeight());
        characterDTO.setHistory(entity.getHistory());
        if (b){
            Set<MovieDTO> movieDTOS = movieMapper.movieEntityCollection2DTOSet(entity.getMovies(), false);
            characterDTO.setMovies(movieDTOS);
        }
        return characterDTO;
    }

    //conversión Character a CharacterBasicDTO
    public CharacterBasicDTO characterEntity2BasicDTO (Character entity) {
        CharacterBasicDTO dto = new CharacterBasicDTO();
        dto.setName(entity.getName());
        dto.setImage(entity.getImage());
        return dto;
    }

    //conversión CharacterEntityCollection a CharacterDTOSet
    public Set<CharacterDTO> characterEntityCollection2DTOSet (Collection<Character> entities, boolean b) {
        Set<CharacterDTO> DTOS = new HashSet<>();
        for (Character entity : entities) {
            DTOS.add(characterEntity2DTO(entity, b));
        }
        return DTOS;
    }

    //conversión CharacterEntityCollection a CharacterBasicDTOList
    public List<CharacterBasicDTO> characterEntityCollection2BasicDTOList (Collection<Character> entities) {
        List<CharacterBasicDTO> DTOs = new ArrayList<>();
        for(Character entity : entities) {
            DTOs.add(characterEntity2BasicDTO(entity));
        }
        return DTOs;
    }
}

