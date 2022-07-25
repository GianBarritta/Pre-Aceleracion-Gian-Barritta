package com.alkemy.disney.controller;

import com.alkemy.disney.dto.MovieBasicDTO;
import com.alkemy.disney.dto.MovieDTO;
import com.alkemy.disney.service.impl.IMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    private final IMovieService movieService;

    //obtiene una película por id
    @GetMapping("/{id}")
    public ResponseEntity<MovieDTO> getById(@PathVariable Long id) {
        MovieDTO movie = movieService.getById(id);
        return ResponseEntity.ok(movie);
    }

    //busca películas con filtros (nombre, genero y orden)
    @GetMapping
    public ResponseEntity<Set<MovieBasicDTO>> getDetailByFilters(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Long genre,
            @RequestParam(required = false, defaultValue = "ASC") String order
    ) {
        Set<MovieBasicDTO> movies = movieService.getByFilters(name, genre, order);
        return ResponseEntity.ok(movies);
    }

    //guarda una película creada
    @PostMapping
    ResponseEntity<MovieDTO> save(@Valid @RequestBody MovieDTO movie) {
        MovieDTO savedMovie = movieService.save(movie);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedMovie);
    }

    @PostMapping("/{idMovie}/characters/{idCharacter}")
    public ResponseEntity<MovieDTO> addCharacter(@PathVariable Long movieId, @PathVariable Long characterId) {
        MovieDTO movie = movieService.addCharacter(movieId, characterId);
        return ResponseEntity.ok(movie);
    }

    //actualiza una película
    @PutMapping("/{id}")
    public ResponseEntity<MovieDTO> update(@Valid @RequestBody MovieDTO movieDTO, @PathVariable Long id) {
        MovieDTO savedMovie = movieService.update(movieDTO, id);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedMovie);
    }

    //borra la película
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        movieService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/{idMovie}/characters/{idCharacter}")
    public ResponseEntity<MovieDTO> removeCharacter(@PathVariable Long movieId, @PathVariable Long characterId) {
        MovieDTO movie = movieService.removeCharacter(movieId, characterId);
        return ResponseEntity.ok(movie);
    }
}
