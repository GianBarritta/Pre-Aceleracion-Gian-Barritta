package com.alkemy.disney.service;

import com.alkemy.disney.entity.CharacterEntity;
import com.alkemy.disney.entity.MovieEntity;
import com.alkemy.disney.exception.ParamNotFound;
import com.alkemy.disney.repository.CharacterRepository;
import com.alkemy.disney.repository.MovieRepository;
import com.alkemy.disney.service.impl.ICharacterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional
@Service
public class CharacterService implements ICharacterService {

    private final CharacterRepository characterRepository;

    private final MovieRepository movieRepository;

    @Override
    public Set<CharacterEntity> getAll() {
        return characterRepository.findAll();
    }

    @Override
    public CharacterEntity findById(Long characterId) {
        return characterRepository.findById(characterId).orElseThrow(() -> new ParamNotFound("No Character with ID : " + characterId));
    }

    @Override
    public Set<CharacterEntity> findByName(String name) {
        return characterRepository.findByName(name);
    }

    @Override
    public Set<CharacterEntity> findByAge(Integer age) {
        return characterRepository.findByAge(age);
    }

    @Override
    public void delete(Long id){
        characterRepository.delete(findById(id));
    }

    @Override
    public CharacterEntity save(CharacterEntity characterEntity) {
        return characterRepository.save(characterEntity);
    }

    @Override
    public Set<CharacterEntity> findByMovieId(Long idMovie) {
        return characterRepository.findByMoviesId(idMovie);
    }

    private boolean checkMoviesExistence(Set<Long> moviesIds) {
        return movieRepository.findAll().stream().map(MovieEntity::getId).collect(Collectors.toSet()).containsAll(moviesIds);
    }

    @Override
    public void addMovies(Long characterId, Set<Long> moviesIds) {
        CharacterEntity characterEntity = findById(characterId);
        if (checkMoviesExistence(moviesIds)) {movieRepository.findAllById(moviesIds).forEach(movie -> characterEntity.getMovies().add(movie));
        } else {throw new ParamNotFound("Asegúrese de que todas las películas que desea agregar al personaje ya existan en el servidor");}
        characterRepository.save(characterEntity);
    }

    @Override
    public void removeMovies(Long characterId, Set<Long> moviesIds) {
        CharacterEntity characterEntity = findById(characterId);
        characterEntity.getMovies().removeIf(movie -> moviesIds.contains(movie.getId()));
        characterRepository.save(characterEntity);
    }
}

