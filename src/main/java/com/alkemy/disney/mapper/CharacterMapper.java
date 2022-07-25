package com.alkemy.disney.mapper;

import com.alkemy.disney.dto.CharacterBasicDTO;
import com.alkemy.disney.dto.CharacterDTO;
import com.alkemy.disney.dto.MovieDTO;
import com.alkemy.disney.entity.CharacterEntity;
import com.alkemy.disney.entity.MovieEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class CharacterMapper {

    @Autowired
    private MovieMapper movieMapper;

    //conversión CharacterDTO a CharacterEntity
    public CharacterEntity characterDTO2Entity(CharacterDTO characterDTO){
        CharacterEntity entity = new CharacterEntity();
        entity.setImage(characterDTO.getImage());
        entity.setName(characterDTO.getName());
        entity.setAge(characterDTO.getAge());
        entity.setWeight(characterDTO.getWeight());
        entity.setHistory(characterDTO.getHistory());
        return entity;
    }

    //conversión CharacterEntity a CharacterDTO
    public CharacterDTO characterEntity2DTO(CharacterEntity entity, boolean b){
        CharacterDTO characterDTO = new CharacterDTO();
        characterDTO.setImage(entity.getImage());
        characterDTO.setName(entity.getName());
        characterDTO.setAge(entity.getAge());
        characterDTO.setWeight(entity.getWeight());
        characterDTO.setHistory(entity.getHistory());
        if (b){
            Set<MovieEntity> movieEntities = MovieMapper.MovieEntity2DTOSet(entity.getMovies(), false);
            characterDTO.setMovies(movieEntities);
        }
        return characterDTO;
    }

    //conversión CharacterEntitySet a CharacterDTOSet
    public Set<CharacterDTO> characterEntitySet2DTOSet(Set<CharacterEntity> CharacterEntitiesSet, boolean b){
        Set<CharacterDTO> CharactersDTOSet = new HashSet<>();
        for (CharacterEntity entity: CharacterEntitiesSet){
            CharactersDTOSet.add(characterEntity2DTO(entity,b));
        }
        return CharactersDTOSet;
    }

    //conversión CharacterEntity a CharacterBasicDTO
    public CharacterBasicDTO characterEntity2BasicDTO(CharacterEntity entity){
        CharacterBasicDTO characterBasicDTO = new CharacterBasicDTO();
        characterBasicDTO.setImage(entity.getImage());
        characterBasicDTO.setName(entity.getName());
        return characterBasicDTO;
    }

    //conversión CharacterEntitySet a CharacterBasicDTOSet
    public Set<CharacterBasicDTO> characterEntitySet2BasicDTOSet(Set<CharacterEntity> entities){
        Set<CharacterBasicDTO> charactersBasicDTOSet = new HashSet<>();
        for (CharacterEntity entity: entities){
            charactersBasicDTOSet.add(characterEntity2BasicDTO(entity));
        }
        return charactersBasicDTOSet;
    }

    // Converts a Character DTO List to an Entity List
    public Set<CharacterEntity> characterDTO2EntitySet(Set<CharacterDTO> dtos)
    {
        Set<CharacterEntity> entities = new HashSet<>();

        for(CharacterDTO dto : dtos)
        {
            entities.add(characterDTO2Entity(dto));
        }

        return entities;
    }

    //se modifica la información de la Entidad con la del DTO
    public void modifyCharacterValues(CharacterEntity entity, CharacterDTO characterDTO)
    {
        entity.setImage(characterDTO.getImage());
        entity.setName(characterDTO.getName());
        entity.setAge(characterDTO.getAge());
        entity.setWeight(characterDTO.getWeight());
        entity.setHistory(characterDTO.getHistory());
        Set<MovieEntity> movieEntities = movieMapper.movieDTO2EntitySet(characterDTO.getMovies());

        for(MovieEntity movieEntity : movieEntities){
            entity.getMovies().add(movieEntity);
        }
    }
    }
}
}
