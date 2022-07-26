package com.alkemy.disney.service.impl;

import com.alkemy.disney.dto.CharacterDTO;
import com.alkemy.disney.entity.CharacterEntity;

import java.util.Set;

public interface ICharacterService {
    CharacterDTO save(CharacterDTO dto, Long titleId);

    CharacterEntity getCharacterById(Long id);

    CharacterDTO getCharacterDTOById(Long id);

    Set<CharacterDTO> getCharacters();

    CharacterDTO updateCharacter(Long id, CharacterDTO characterDTO);

    Set<CharacterDTO> getByFilters(String name, Integer age, double weight, Set<Long> movies);

    void delete(Long id);
}
