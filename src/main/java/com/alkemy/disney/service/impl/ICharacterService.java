package com.alkemy.disney.service.impl;

import com.alkemy.disney.entity.CharacterEntity;

import java.util.Set;

public interface ICharacterService {
    Set<CharacterEntity> getAll();

    CharacterEntity findById(Long characterId);

    Set<CharacterEntity> findByName(String name);

    Set<CharacterEntity> findByAge(Integer age);

    void delete(Long id);

    CharacterEntity save(CharacterEntity characterEntity);

    Set<CharacterEntity> findByMovieId(Long idMovie);

    void addMovies(Long characterId, Set<Long> moviesIds);

    void removeMovies(Long characterId, Set<Long> moviesIds);
}
