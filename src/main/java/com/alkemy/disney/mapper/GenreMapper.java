package com.alkemy.disney.mapper;

import com.alkemy.disney.dto.GenreDTO;
import com.alkemy.disney.entity.Genre;
import org.springframework.stereotype.Component;

@Component
public class GenreMapper {

    //conversión Genre a GenreDTO
    public GenreDTO genreEntity2DTO(Genre genre) {
        GenreDTO genreDTO = new GenreDTO();
        genreDTO.setId(genre.getId());
        genreDTO.setImage(genre.getImage());
        genreDTO.setName(genre.getName());
        return genreDTO;
    }

    //conversión GenreDTO a Genre
    public Genre genreDTO2Entity(GenreDTO genreDTO) {
        Genre genre = new Genre();
        genre.setImage(genreDTO.getImage());
        genre.setName(genreDTO.getName());
        return genre;
    }
}

