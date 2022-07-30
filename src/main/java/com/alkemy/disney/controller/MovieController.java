package com.alkemy.disney.controller;

import com.alkemy.disney.dto.MovieBasicDTO;
import com.alkemy.disney.dto.MovieDTO;
import com.alkemy.disney.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    private final MovieService movieService;

    //obtiene una película por id
    @GetMapping("/{id}")
    public ResponseEntity<MovieDTO> getMovieById(@PathVariable Long id) {
        MovieDTO movie = movieService.getMovieDTOById(id);
        return ResponseEntity.ok(movie);
    }

    //busca películas con filtros (nombre, genero y orden)
    @GetMapping
    public ResponseEntity<List<MovieBasicDTO>> getDetailByFilters(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Long genre,
            @RequestParam(required = false, defaultValue = "ASC") String order
    ) {
        List<MovieBasicDTO> movies = movieService.getByFilters(name, genre, order);
        return ResponseEntity.ok(movies);
    }

    //guarda la película creada
    @PostMapping
    ResponseEntity<MovieDTO> save(@Valid @RequestBody MovieDTO movie) {
        MovieDTO savedMovie = movieService.save(movie);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedMovie);
    }

    //agrega el personaje a la película
    @PostMapping("/{movieId}/characters/{characterId}")
    public ResponseEntity<MovieDTO> addCharacter(@PathVariable Long movieId, @PathVariable Long characterId) {
        MovieDTO movie = movieService.addCharacter(movieId, characterId);
        return ResponseEntity.ok(movie);
    }

    //actualiza la película
    @PutMapping("/{id}")
    public ResponseEntity<MovieDTO> update(@Valid @RequestBody MovieDTO movieDTO, @PathVariable Long id) {
        MovieDTO savedMovie = movieService.updateMovie(id, movieDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedMovie);
    }

    //borra la película
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        movieService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    //remueve el personaje de la película
    @DeleteMapping("/{movieId}/characters/{characterId}")
    public ResponseEntity<MovieDTO> removeCharacter(@PathVariable Long movieId, @PathVariable Long characterId) {
        MovieDTO movie = movieService.removeCharacter(movieId, characterId);
        return ResponseEntity.ok(movie);
    }
}
