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

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional
@Service
public class CharacterService implements ICharacterService {

    private final CharacterRepository characterRepository;

    private final MovieRepository movieRepository;

    @Override
    public List<CharacterEntity> getAll() {
        return characterRepository.findAll();
    }

    @Override
    public CharacterEntity findById(Long characterId) {
        return characterRepository.findById(characterId).orElseThrow(() -> new ParamNotFound("No Character with ID : " + characterId));
    }

    @Override
    public List<CharacterEntity> findByName(String name) {
        return characterRepository.findByName(name);
    }

    @Override
    public List<CharacterEntity> findByAge(Integer age) {
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
    public List<CharacterEntity> findByMovieId(Long idMovie) {
        return characterRepository.findByMoviesId(idMovie);
    }

    private boolean checkMoviesExistence(List<Long> moviesIds) {
        return movieRepository.findAll().stream().map(MovieEntity::getId).collect(Collectors.toList()).containsAll(moviesIds);
    }

    @Override
    public void addMovies(Long characterId, List<Long> moviesIds) {
        CharacterEntity characterEntity = findById(characterId);
        if (checkMoviesExistence(moviesIds)) {movieRepository.findAllById(moviesIds).forEach(movie -> characterEntity.getMovies().add(movie));
        } else {throw new ParamNotFound("Make sure all movies you want to add to the character already exist on the server");}
        characterRepository.save(characterEntity);
    }

    @Override
    public void removeMovies(Long characterId, List<Long> moviesIds) {
        CharacterEntity characterEntity = findById(characterId);
        characterEntity.getMovies().removeIf(movie -> moviesIds.contains(movie.getId()));
        characterRepository.save(characterEntity);
    }
}

