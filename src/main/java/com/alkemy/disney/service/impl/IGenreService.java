package com.alkemy.disney.service.impl;

import com.alkemy.disney.dto.GenreDTO;
import com.alkemy.disney.entity.Genre;
import com.alkemy.disney.exception.ParamNotFound;
import com.alkemy.disney.mapper.GenreMapper;
import com.alkemy.disney.repository.GenreRepository;
import com.alkemy.disney.service.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Transactional
@Service
public class IGenreService implements GenreService {

    private final GenreMapper genreMapper;

    private final GenreRepository genreRepository;

    public GenreDTO save(GenreDTO dto) {
        Genre entity = genreMapper.genreDTO2Entity(dto);
        Genre savedEntity = genreRepository.save(entity);
        return genreMapper.genreEntity2DTO(savedEntity);
    }

    public Genre getGenreEntityById(Long id) {
        Optional<Genre> genre = genreRepository.findById(id);
        if (genre.isEmpty()) {
            throw new ParamNotFound("Género con ID: " + id + " no encontrado");
        }
        return genre.get();
    }
}
