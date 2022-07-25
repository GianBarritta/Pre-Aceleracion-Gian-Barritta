package com.alkemy.disney.mapper;

import com.alkemy.disney.dto.GenreDTO;
import com.alkemy.disney.entity.GenreEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GenreMapper {

    @Autowired
    private MovieMapper movieMapper;

    //conversión GenreEntity a GenreDTO
    public GenreDTO genreEntity2Dto(GenreEntity genreEntity) {
        GenreDTO genreDTO = new GenreDTO();
        genreDTO.setImage(genreEntity.getImage());
        genreDTO.setName(genreEntity.getName());
        return genreDTO;

    }

    //conversión GenreDTO a GenreEntity
    public GenreEntity genreDTO2Entity(GenreDTO genreDTO) {
        GenreEntity genreEntity = new GenreEntity();
        genreEntity.setImage(genreDTO.getImage());
        genreEntity.setName(genreDTO.getName());
        return genreEntity;
    }

    //conversión GenreEntityList a GenreDTOList
    public List<GenreDTO> genreEntityList2DTOList(List<GenreEntity> genreList) {
        List<GenreDTO> genreDTOList = new ArrayList<>();
        for (GenreEntity entity : genreList) {
            genreDTOList.add(genreEntity2Dto(entity));
        }
        return genreDTOList;
    }

    //conversión GenreDTOList a GenreEntityList
    public List<GenreEntity> genreDTOList2EntityList(List<GenreDTO> dtos)
    {
        List<GenreEntity>entities = new ArrayList<>();

        for(GenreDTO dto : dtos)
        {
            entities.add(genreDTO2Entity(dto));
        }
        return entities;
    }

}

