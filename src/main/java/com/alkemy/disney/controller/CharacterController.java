package com.alkemy.disney.controller;

import com.alkemy.disney.dto.CharacterBasicDTO;
import com.alkemy.disney.dto.CharacterDTO;
import com.alkemy.disney.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/characters")
public class CharacterController {

    @Autowired
    private CharacterService characterService;

    //obtener el personaje por su id
    @GetMapping("/{id}")
    public ResponseEntity<CharacterDTO> getCharacterById(@PathVariable Long id) {
        CharacterDTO character = characterService.getCharacterDTOById(id);
        return ResponseEntity.ok().body(character);
    }

    //buscar personajes con filtros (name, age, weight y movies)
    @GetMapping
    public ResponseEntity<Set<CharacterBasicDTO>> search(@RequestParam(required = false) String name,
                                                         @RequestParam(required = false) Integer age,
                                                         @RequestParam(required = false) double weight,
                                                         @RequestParam(required = false) Set<Long> movies) {
        Set<CharacterBasicDTO> characters = characterService.getByFilters(name, age, weight, movies);
        return ResponseEntity.ok().body(characters);
    }

    //crea el personaje
    @PostMapping
    public ResponseEntity<CharacterDTO> save(@RequestBody CharacterDTO characterDTO, @RequestParam Long movieId) {
        CharacterDTO savedCharacter = characterService.save(characterDTO, movieId);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCharacter);
    }

    //modifica el personaje
    @PutMapping("/{id}")
    public ResponseEntity<CharacterDTO> update(@PathVariable Long id, @RequestBody CharacterDTO character){
        CharacterDTO characterUpdated = characterService.updateCharacter(id, character);
        return ResponseEntity.ok().body(characterUpdated);
    }

    //borra el personaje
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        characterService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
