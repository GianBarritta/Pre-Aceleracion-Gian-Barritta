package com.alkemy.disney.mapper;

import com.alkemy.disney.dto.GenreDTO;
import com.alkemy.disney.entity.GenreEntity;
import org.springframework.stereotype.Component;

@Component
public class GenreMapper {

    //conversión GenreEntity a GenreDTO
    public GenreDTO genreEntity2Dto(GenreEntity genreEntity) {
        GenreDTO genreDTO = new GenreDTO();
        genreDTO.setId(genreEntity.getId());
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
}

