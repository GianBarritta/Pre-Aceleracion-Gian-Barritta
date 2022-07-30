package com.alkemy.disney.controller;

import com.alkemy.disney.dto.CharacterBasicDTO;
import com.alkemy.disney.dto.CharacterDTO;
import com.alkemy.disney.service.CharacterService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@RestController
@RequestMapping("/characters")
public class CharacterController {

    @Autowired
    private final CharacterService characterService;

    //obtener el personaje por su id
    @GetMapping("/{id}")
    public ResponseEntity<CharacterDTO> getCharacterById(@PathVariable Long id) {
        CharacterDTO character = characterService.getCharacterDTOById(id);
        return ResponseEntity.ok(character);
    }

    //buscar personajes con filtros (nombre, edad, peso y películas)
    @GetMapping
    public ResponseEntity<List<CharacterBasicDTO>> getByFilters(@RequestParam(required = false) String name,
                                                               @RequestParam(required = false) Integer age,
                                                               @RequestParam(required = false) double weight,
                                                               @RequestParam(required = false) Set<Long> movies) {
        List<CharacterBasicDTO> characters = characterService.getByFilters(name, age, weight, movies);
        return ResponseEntity.ok(characters);
    }

    //crea el personaje
    @PostMapping
    public ResponseEntity<CharacterDTO> save(@RequestBody CharacterDTO characterDTO) {
        CharacterDTO savedCharacter = characterService.save(characterDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCharacter);
    }

    //modifica el personaje
    @PutMapping("/{id}")
    public ResponseEntity<CharacterDTO> update(@PathVariable Long id, @RequestBody CharacterDTO character){
        CharacterDTO characterUpdated = characterService.updateCharacter(id, character);
        return ResponseEntity.status(HttpStatus.CREATED).body(characterUpdated);
    }

    //borra el personaje
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        characterService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
