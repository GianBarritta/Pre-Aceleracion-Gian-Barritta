package com.alkemy.disney.service;

import com.alkemy.disney.dto.GenreDTO;
import com.alkemy.disney.entity.Genre;

public interface GenreService {

    GenreDTO save(GenreDTO dto);

    Genre getGenreEntityById(Long id);
}
