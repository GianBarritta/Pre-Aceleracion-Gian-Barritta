package com.alkemy.disney.mapper;

import com.alkemy.disney.dto.CharacterBasicDTO;
import com.alkemy.disney.dto.CharacterDTO;
import com.alkemy.disney.entity.CharacterEntity;

import java.util.List;
import org.mapstruct.*;


@Mapper(componentModel = "spring")
public interface CharacterMapper {

    CharacterBasicDTO characterToCharacterBasicDTO(CharacterEntity characterEntity);

    CharacterDTO characterToCharacterDTO(CharacterEntity characterEntity);

    CharacterDTO characterDTOToCharacter(CharacterDTO character);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
            nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    Character updateCharacterFromDTO(CharacterDTO characterDTO, @MappingTarget CharacterEntity characterEntity);

    List<CharacterBasicDTO> charactersToCharacterBasicDTOS(List<CharacterEntity> characters);

    List<CharacterDTO> charactersToCharacterDTOS(List<CharacterEntity> characters);

}
