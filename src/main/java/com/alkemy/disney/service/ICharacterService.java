package com.alkemy.disney.service;

import com.alkemy.disney.entity.CharacterEntity;

import java.util.List;

public interface ICharacterService {
    List<CharacterEntity> getAll();

    CharacterEntity findById(Long characterId);

    List<CharacterEntity> findByName(String name);

    List<CharacterEntity> findByAge(Integer age);

    void delete(Long id);

    CharacterEntity save(CharacterEntity characterEntity);

    List<CharacterEntity> findByMovieId(Long idMovie);

    void addMovies(Long characterId, List<Long> moviesIds);

    void removeMovies(Long characterId, List<Long> moviesIds);
}
