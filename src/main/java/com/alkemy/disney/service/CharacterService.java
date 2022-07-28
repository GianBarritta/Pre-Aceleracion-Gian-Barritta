package com.alkemy.disney.service;

import com.alkemy.disney.dto.CharacterBasicDTO;
import com.alkemy.disney.dto.CharacterDTO;
import com.alkemy.disney.entity.CharacterEntity;

import java.util.Set;

public interface CharacterService {
    CharacterDTO save(CharacterDTO dto, Long movieId);

    CharacterEntity getCharacterById(Long id);

    CharacterDTO getCharacterDTOById(Long id);

    Set<CharacterDTO> getCharacters();

    Set<CharacterBasicDTO> getAllCharactersBasic();

    CharacterDTO updateCharacter(Long id, CharacterDTO characterDTO);

    Set<CharacterDTO> getByFilters(String name, Integer age, double weight, Set<Long> movies);

    void delete(Long id);
}
