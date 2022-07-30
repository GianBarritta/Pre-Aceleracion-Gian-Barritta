package com.alkemy.disney.service;

import com.alkemy.disney.dto.GenreDTO;
import com.alkemy.disney.entity.GenreEntity;

public interface GenreService {

    GenreDTO save(GenreDTO dto);

    GenreEntity getGenreEntityById(Long id);
}
