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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RequiredArgsConstructor
@Transactional
@Service
public class ICharacterService implements CharacterService {

    @Autowired
    private final CharacterRepository characterRepository;

    @Autowired
    private final CharacterMapper characterMapper;

    @Autowired
    private final IMovieService iMovieService;

    @Autowired
    private final CharacterSpecification characterSpecification;

    //busca una identificación en el repositorio de personajes
    public CharacterEntity getCharacterById(Long id) {
        Optional<CharacterEntity> entity = characterRepository.findById(id);
        if(!entity.isPresent()) {
            throw new ParamNotFound("ID de personaje no encontrado");
        }
        return entity.get();
    }

    //busca una identificación en el repositorio de personajes y convierte la entidad en un DTO
    public CharacterDTO getCharacterDTOById(Long id){
        Optional<CharacterEntity> entity = characterRepository.findById(id);
        if(!entity.isPresent()) {
            throw new ParamNotFound("ID de personaje no encontrado");
        }
        CharacterDTO characterDTO = characterMapper.characterEntity2DTO(entity.get(),true);
        return characterDTO;
    }

    //búsqueda de personajes por filtros (nombre, edad, peso y películas (tiene orden ASC/DESC))
    public List<CharacterBasicDTO> getByFilters(String name, Integer age, double weight, Set<Long> movies) {
        CharacterFiltersDTO filtersDTO = new CharacterFiltersDTO(name, age, weight, movies);
        List<CharacterEntity> entities = characterRepository.findAll(characterSpecification.getByFilters(filtersDTO));
        List<CharacterBasicDTO> dtos = characterMapper.characterEntityCollection2BasicDTOList(entities);
        return dtos;
    }

    //devuelve todos los personajes guardados en el repositorio con sus peliculas asociadas
    public Set<CharacterDTO> getCharacters() {
        Set<CharacterEntity> entities = (Set<CharacterEntity>) characterRepository.findAll();
        Set<CharacterDTO> result = characterMapper.characterEntityCollection2DTOSet(entities,true);
        return result;
    }

    //guardar al personaje en el repositorio
    public CharacterDTO save(CharacterDTO dto) {
        CharacterEntity entity = characterMapper.characterDTO2Entity(dto);
        CharacterEntity savedEntity = characterRepository.save(entity);
        return characterMapper.characterEntity2DTO(savedEntity, true);
    }

    //invoca un método de mapeador y modifica solo los valores del personaje
    public CharacterDTO updateCharacter(Long id, CharacterDTO characterDTO) {
        Optional<CharacterEntity> entity = characterRepository.findById(id);
        if(!entity.isPresent())
        {
            throw new ParamNotFound("ID de personaje para modificar no encontrado");
        }
        CharacterEntity character = getCharacterById(id);
        character.setName(characterDTO.getName());
        character.setImage(characterDTO.getImage());
        character.setAge(characterDTO.getAge());
        character.setWeight(characterDTO.getWeight());
        character.setHistory(characterDTO.getHistory());
        characterRepository.save(character);
        return characterMapper.characterEntity2DTO(character, false);
    }

    //Soft Delete de un personaje guardado en el repositorio
    public void delete(Long id) {
        Optional<CharacterEntity>entity = characterRepository.findById(id);
        if(!entity.isPresent())
        {
            throw new ParamNotFound("ID de personaje no encontrado, no se pudo eliminar");
        }
        characterRepository.deleteById(id);
    }
}