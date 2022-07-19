package com.alkemy.disney.controller;

import com.alkemy.disney.dto.MovieDTO;
import com.alkemy.disney.service.IMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    private IMovieService IMovieService;

    @PostMapping
    public ResponseEntity<MovieDTO> save(@RequestBody MovieDTO movie) {
        MovieDTO movieCreated = IMovieService.save(movie);
        return ResponseEntity.status(HttpStatus.CREATED).body(movieCreated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        IMovieService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/{id}/characters/{idCharacter}")
    public ResponseEntity<Void> removeCharacter(@PathVariable Long id, @PathVariable Long idCharacter) {
        IMovieService.removeCharacter(id, idCharacter);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
