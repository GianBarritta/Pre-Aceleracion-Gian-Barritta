package com.alkemy.disney.service;

import com.alkemy.disney.repository.CharacterRepository;
import com.alkemy.disney.repository.GenreRepository;
import com.alkemy.disney.repository.MovieRepository;
import com.alkemy.disney.service.impl.IGenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class GenreService implements IGenreService {

    private final CharacterRepository characterRepository;

    private final MovieRepository movieRepository;

    private final GenreRepository genreRepository;



}
