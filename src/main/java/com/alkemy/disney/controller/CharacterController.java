package com.alkemy.disney.controller;

import com.alkemy.disney.dto.CharacterBasicDTO;
import com.alkemy.disney.dto.CharacterDTO;
import com.alkemy.disney.dto.MovieBasicDTO;
import com.alkemy.disney.mapper.CharacterMapper;
import com.alkemy.disney.mapper.MovieMapper;
import com.alkemy.disney.service.ICharacterService;
import org.hibernate.annotations.Parameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/characters")
public class CharacterController {

    private final CharacterMapper characterMapper;

    private final MovieMapper movieMapper;

    private final ICharacterService characterService;

    public CharacterController(CharacterMapper characterMapper, MovieMapper movieMapper, ICharacterService characterService) {
        this.characterMapper = characterMapper;
        this.movieMapper = movieMapper;
        this.characterService = characterService;
    }


    @GetMapping()
    public ResponseEntity<List<CharacterBasicDTO>> getAllCharacters() {
        return new ResponseEntity<>(characterMapper.charactersToCharacterBasicDTO(characterService.getAll()), HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<CharacterDTO> getCharacterById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(characterMapper.characterToCharacterDTO(characterService.findById(id)), HttpStatus.OK);
    }

    @GetMapping(params = "name")
    public ResponseEntity<List<CharacterDTO>> findCharacterByName(@Parameter(description = "Filter by name") @RequestParam(value = "name", required = false) String name) {
        return new ResponseEntity<>(characterMapper.charactersToCharacterDTOS(characterService.findByName(name)), HttpStatus.OK);
    }

    @GetMapping(params="age")
    public ResponseEntity<List<CharacterDTO>> findCharacterByAge(@Parameter(description = "Filter by age") @RequestParam(value = "age", required = false) Integer age) {
        return new ResponseEntity<>(characterMapper.charactersToCharacterDTOS(characterService.findByAge(age)), HttpStatus.OK);
    }

    @GetMapping(params="movie")
    public ResponseEntity<List<CharacterDTO>> findCharacterByMovieId(@Parameter(description = "Filter by MovieID") @RequestParam(value = "movie", required = false) Long movieId) {
        return new ResponseEntity<>(characterMapper.charactersToCharacterDTOS(characterService.findByMovieId(movieId)), HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteCharacterById(@PathVariable("id") Long id) {
        characterService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @PostMapping()
    public ResponseEntity<CharacterDTO> saveCharacter(@Valid @RequestBody CharacterDTO character) {
        Character characterCreated = characterService.save(characterMapper.characterDTOToCharacter(character));
        return new ResponseEntity<>(characterMapper.characterToCharacterDTO(characterCreated), HttpStatus.CREATED);
    }


    @PatchMapping("/{id}")
    public ResponseEntity<CharacterDTO> updateCharacter(@Valid @RequestBody CharacterDTO character, @PathVariable("id") Long id) {
        Character characterUpdated = characterService.save(characterMapper.updateCharacterFromDTO(character, characterService.findById(id)));
        return new ResponseEntity<>(characterMapper.characterToCharacterDTO(characterUpdated), HttpStatus.OK);
    }


    @GetMapping("{id}/movies")
    public ResponseEntity<List<MovieBasicDTO>> getCharacterMovies(@PathVariable("id") Long characterId) {
        return new ResponseEntity<>(movieMapper.moviesToMovieBasicDTOS(new ArrayList<>(characterService.findById(characterId).getMovies())), HttpStatus.OK);
    }


    @PutMapping("{id}/movies")
    public ResponseEntity<?> addMoviesToCharacter(@Valid @RequestBody ListOfLongDTO moviesIds, @PathVariable("id") Long characterId) {
        characterService.addMovies(characterId, moviesIds.getList());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @DeleteMapping("{id}/movies")
    public ResponseEntity<?> removeMoviesFromCharacter(@Valid @RequestBody ListOfLongDTO moviesIds, @PathVariable("id") Long characterId) {
        characterService.removeMovies(characterId, moviesIds.getList());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
