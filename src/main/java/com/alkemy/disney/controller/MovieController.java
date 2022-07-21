package com.alkemy.disney.controller;

import com.alkemy.disney.dto.MovieBasicDTO;
import com.alkemy.disney.dto.MovieDTO;
import com.alkemy.disney.entity.MovieEntity;
import com.alkemy.disney.mapper.MovieMapper;
import com.alkemy.disney.service.impl.IMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    private final MovieMapper movieMapper;

    @Autowired
    private final IMovieService movieService;

    public MovieController(MovieMapper movieMapper, IMovieService movieService) {
        this.movieMapper = movieMapper;
        this.movieService = movieService;
    }

    @GetMapping
    public ResponseEntity<List<MovieDTO>> getAllMovies() {
        return new ResponseEntity<>(movieMapper.moviesToMovieDTOS(movieService.getAll()), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieDTO> findMovieById(@PathVariable Long id) {
        return new ResponseEntity<>(movieMapper.movieToMovieDTO(movieService.findById(id)), HttpStatus.OK);
    }

    //Obtener todas las películas ordenadas por fecha de creación (ASC | DESC)
    @GetMapping(params="order")
    public ResponseEntity<List<MovieDTO>> getAllMoviesOrderByCreationDate(@Valid @RequestParam(value ="order", required = false) String order) {
        List<MovieEntity> movies = movieService.findAllOrderByCreationDate(order);
        if(movies == null) {return new ResponseEntity<>(movieService.returnEmptyMovieDTO(),HttpStatus.OK);
        } else {return new ResponseEntity<>(movieMapper.moviesToMovieDTOS(movies), HttpStatus.OK);}
    }

    //Filtrar películas por título
    @GetMapping(params="title")
    public ResponseEntity<List<MovieDTO>> findMovieByTitle(@RequestParam(value = "title", required = false) String title) {
        return new ResponseEntity<>(movieMapper.moviesToMovieDTOS(movieService.findByTitle(title)), HttpStatus.OK);
    }

    @GetMapping(params="genre")
    public ResponseEntity<List<MovieDTO>> findMovieByGenre(@RequestParam(value = "genre", required = false) Long genreId) {
        return new ResponseEntity<>(movieMapper.moviesToMovieDTOS(movieService.findByGenreId(genreId)), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<MovieBasicDTO>> getByFilters(
            @RequestParam(required = false) String name,
            @RequestParam(required = false, defaultValue = "0") String genre,
            @RequestParam(required = false, defaultValue = "ASC") String order,
            @RequestParam(required = false) List<MovieEntity> moviesAssociated){

        List<MovieBasicDTO> movies = movieService.getByFilters(name, genre, order, moviesAssociated);
        return ResponseEntity.ok(movies);
    }

    @PostMapping
    public ResponseEntity<MovieDTO> saveMovie(@Valid @RequestBody MovieDTO movie) {
        MovieEntity movieCreated = movieService.save(movieMapper.movieDTOToMovie(movie));
        return new ResponseEntity<>(movieMapper.movieToMovieDTO(movieCreated), HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    public ResponseEntity<MovieDTO> updateMovie(@Valid @RequestBody MovieDTO movie, @PathVariable("id") Long id){
        MovieEntity movieUpdated = movieService.save(movieMapper.updateMovieFromDTO(movie, movieService.findById(id)));
        return new ResponseEntity<>(movieMapper.movieToMovieDTO(movieUpdated), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteMovieById(@PathVariable("id") Long id) {
        movieService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
