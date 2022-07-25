package com.alkemy.disney.mapper;

import com.alkemy.disney.dto.CharacterDTO;
import com.alkemy.disney.dto.MovieBasicDTO;
import com.alkemy.disney.dto.MovieDTO;
import com.alkemy.disney.entity.CharacterEntity;
import com.alkemy.disney.entity.MovieEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class MovieMapper {

    @Autowired
    private CharacterMapper characterMapper;
    private GenreMapper genreMapper;

    //conversión MovieDTO a MovieEntity
    public MovieEntity movieDTO2Entity(MovieDTO movieDTO){
        MovieEntity entity = new MovieEntity();
        entity.setImage(movieDTO.getImage());
        entity.setTitle(movieDTO.getTitle());
        entity.setScore(movieDTO.getScore());
        entity.setCreationDate(movieDTO.getCreationDate());
        entity.setGenreId(movieDTO.getGenreId());
        Set<CharacterEntity> characterEntities = characterMapper.characterDTO2EntitySet(movieDTO.getCharacters());
        entity.setCharacters(characterEntities);

        return entity;
    }

    //conversión MovieEntity a MovieDTO
    public MovieDTO movieEntity2DTO(MovieEntity entity, boolean b){
        MovieDTO movieDTO = new MovieDTO();
        movieDTO.setImage(entity.getImage());
        movieDTO.setTitle(entity.getTitle());
        movieDTO.setCreationDate(entity.getCreationDate());
        movieDTO.setScore(entity.getScore());
        movieDTO.setGenreId(entity.getGenreId());

        if(b){
            Set<CharacterEntity> characterEntities = characterMapper.characterEntity2DTOSet(entity.getCharacters(),false);
            movieDTO.setCharacters(characterEntities);
        }
        return movieDTO;
    }

    //conversión MovieEntitySet a MovieDTOSet
    public Set<MovieDTO> entitySet2DTOSet(Set<MovieEntity> movieSet, boolean b){
        Set<MovieDTO> movieDTOSet = new HashSet<>();
        for (MovieEntity entity: movieSet){
            movieDTOSet.add(movieEntity2DTO(entity,b));
        }
        return movieDTOSet;
    }

    //conversión MovieDTOSet a MovieEntitySet
    public Set<MovieEntity> MovieDTO2EntitySet(Set<MovieDTO> dtos)
    {
        Set<MovieEntity>entities = new HashSet<>();

        for(MovieDTO dto : dtos)
        {
            entities.add(MovieDTO2Entity(dto));
        }
        return entities;
    }

    //conversión MovieEntitySet MovieBasicDTOSet
    public Set<MovieBasicDTO> movieEntitySet2BasicDTOSet(Set<MovieEntity> entities){
        Set<MovieBasicDTO> movieBasicDTOSSet = new HashSet<>();
        for (MovieEntity ent: entities){
            movieBasicDTOSSet.add(movieEntity2BasicDTO(ent));
        }
        return movieBasicDTOSSet;
    }

    //se modifica la información de la Entidad con la del DTO
    public void modifyMovieValues(MovieEntity entity, MovieDTO movieDTO)
    {
        entity.setImage(movieDTO.getImage());
        entity.setTitle(movieDTO.getTitle());
        entity.setCreationDate(movieDTO.getCreationDate());
        entity.setScore(movieDTO.getScore());
        entity.setGenreId(movieDTO.getGenreId());
        Set<CharacterEntity> characterEntities = characterMapper.characterDTO2EntitySet(movieDTO.getCharacters());

        for(CharacterEntity characterEntity : characterEntities){
            entity.getCharacters().add(characterEntity);
        }
    }

}