package com.alkemy.disney.service;

import com.alkemy.disney.dto.CharacterBasicDTO;
import com.alkemy.disney.dto.CharacterDTO;
import com.alkemy.disney.entity.CharacterEntity;

import java.util.List;
import java.util.Set;

public interface CharacterService {
    CharacterDTO save(CharacterDTO dto);

    CharacterDTO getCharacterDTOById(Long id);

    CharacterDTO updateCharacter(Long id, CharacterDTO characterDTO);

    List<CharacterBasicDTO> getByFilters(String name, Integer age, double weight, Set<Long> movies);

    void delete(Long id);
}
