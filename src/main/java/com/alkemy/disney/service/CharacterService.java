package com.alkemy.disney.service;

import com.alkemy.disney.dto.CharacterBasicDTO;
import com.alkemy.disney.dto.CharacterDTO;
import com.alkemy.disney.dto.CharacterFiltersDTO;
import com.alkemy.disney.entity.CharacterEntity;
import com.alkemy.disney.exception.ParamNotFound;
import com.alkemy.disney.mapper.CharacterMapper;
import com.alkemy.disney.repository.CharacterRepository;
import com.alkemy.disney.repository.specifications.CharacterSpecification;
import com.alkemy.disney.service.impl.ICharacterService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;

@RequiredArgsConstructor
@Transactional
@Service
public class CharacterService implements ICharacterService {

    @Autowired
    private CharacterRepository characterRepository;
    private CharacterMapper characterMapper;
    private MovieService movieService;
    private CharacterSpecification characterSpecification;

    //guardar al personaje en el repositorio
    public CharacterDTO save(CharacterDTO dto, Long titleId)
    {
        CharacterEntity entity = characterMapper.characterDTO2Entity(dto);
        CharacterEntity savedEntity = characterRepository.save(entity);
        movieService.addCharacter(titleId, savedEntity.getId());
        CharacterDTO result = characterMapper.characterEntity2DTO(savedEntity,true);

        return result;
    }

    //busca una identificación en el repositorio de personajes
    public CharacterEntity getCharacterById(Long id)
    {
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
        CharacterDTO characterDTO = this.characterMapper.characterEntity2DTO(entity.get(),true);
        return characterDTO;
    }

    //búsqueda de personajes por filtros (nombre, edad, peso y peliculas (tiene orden ASC/DESC))
    public Set<CharacterDTO> getByFilters(String name, Integer age, double weight, Set<Long> movies)
    {
        CharacterFiltersDTO filtersDTO = new CharacterFiltersDTO(name, age, weight, movies);
        Set<CharacterEntity> entities = characterRepository.findAll(characterSpecification.getByFilters(filtersDTO));
        Set<CharacterDTO> dtos = characterMapper.characterEntitySet2DTOSet(entities,true);
        return dtos;
    }

    public Set<CharacterBasicDTO> getAllCharactersBasic() {
        Set<CharacterEntity> characterEntitiesBasicSet = (Set<CharacterEntity>) characterRepository.findAll();
        return characterMapper.characterEntitySet2BasicDTOSet(characterEntitiesBasicSet);
    }

    //invoca un método de mapeador y modifica solo los valores del personaje
    public CharacterDTO updateCharacter(Long id, CharacterDTO characterDTO)
    {
        Optional<CharacterEntity>entity = characterRepository.findById(id);
        if(!entity.isPresent())
        {
            throw new ParamNotFound("ID de personaje para modificar no encontrado");
        }
        this.characterMapper.modifyCharacterRefreshValues(entity.get(),characterDTO);
        CharacterEntity savedEntity = characterRepository.save(entity.get());
        CharacterDTO result = characterMapper.characterEntity2DTO(savedEntity, true);

        return result;
    }

    //devuelve todos los personajes guardados en el repositorio con sus peliculas asociadas
    public Set<CharacterDTO> getCharacters()
    {
        Set<CharacterEntity> entities = (Set<CharacterEntity>) characterRepository.findAll();
        Set<CharacterDTO>result = characterMapper.characterEntitySet2DTOSet(entities,true);
        return result;
    }

    //Soft Delete de un personaje guardado en el repositorio
    public void delete(Long id)
    {
        Optional<CharacterEntity>entity = characterRepository.findById(id);
        if(!entity.isPresent())
        {
            throw new ParamNotFound("ID de personaje no encontrado, no se pudo eliminar");
        }
        characterRepository.deleteById(id);
    }
}

