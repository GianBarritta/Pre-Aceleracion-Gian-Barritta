package com.alkemy.disney.service.impl;

import com.alkemy.disney.dto.CharacterBasicDTO;
import com.alkemy.disney.dto.CharacterDTO;
import com.alkemy.disney.dto.CharacterFiltersDTO;
import com.alkemy.disney.entity.CharacterEntity;
import com.alkemy.disney.exception.ParamNotFound;
import com.alkemy.disney.mapper.CharacterMapper;
import com.alkemy.disney.repository.CharacterRepository;
import com.alkemy.disney.repository.specifications.CharacterSpecification;
import com.alkemy.disney.service.CharacterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RequiredArgsConstructor
@Transactional
@Service
public class ICharacterService implements CharacterService {

    private final CharacterRepository characterRepository;

    private final CharacterMapper characterMapper;

    private final CharacterSpecification characterSpecification;

    public CharacterDTO save(CharacterDTO dto) {
        CharacterEntity entity = characterMapper.characterDTO2Entity(dto);
        CharacterEntity savedEntity = characterRepository.save(entity);
        return characterMapper.characterEntity2DTO(savedEntity, true);
    }

    public CharacterDTO getCharacterDTOById(Long id){
        CharacterEntity entity = getCharacterById(id);
        return characterMapper.characterEntity2DTO(entity,true);
    }

    public List<CharacterBasicDTO> getByFilters(String name, Integer age, double weight, Set<Long> movies) {
        CharacterFiltersDTO filtersDTO = new CharacterFiltersDTO(name, age, weight, movies);
        List<CharacterEntity> entities = characterRepository.findAll(characterSpecification.getByFilters(filtersDTO));
        return characterMapper.characterEntityCollection2BasicDTOList(entities);
    }

    public CharacterDTO updateCharacter(Long id, CharacterDTO characterDTO) {
        CharacterEntity character = getCharacterById(id);
        character.setName(characterDTO.getName());
        character.setImage(characterDTO.getImage());
        character.setAge(characterDTO.getAge());
        character.setWeight(characterDTO.getWeight());
        character.setHistory(characterDTO.getHistory());
        characterRepository.save(character);
        return characterMapper.characterEntity2DTO(character, false);
    }

    public void delete(Long id) {
        CharacterEntity character = getCharacterById(id);
        characterRepository.delete(character);
    }

    private CharacterEntity getCharacterById(Long id) {
        Optional<CharacterEntity> entity = characterRepository.findById(id);
        if(entity.isEmpty()) {
            throw new ParamNotFound("Personaje con ID: " + id + " no encontrado");
        }
        return entity.get();
    }
}