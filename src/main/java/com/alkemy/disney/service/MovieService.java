package com.alkemy.disney.service;

import com.alkemy.disney.dto.MovieBasicDTO;
import com.alkemy.disney.dto.MovieDTO;
import java.util.List;

public interface MovieService {

     MovieDTO save(MovieDTO dto);

     List<MovieBasicDTO> getByFilters(String name, Long genreId, String order);

     MovieDTO getMovieDTOById(Long id);

     MovieDTO updateMovie(Long id, MovieDTO movieDTO);

     void delete(Long id);

     MovieDTO addCharacter(Long movieId, Long characterId);

     MovieDTO removeCharacter(Long movieId, Long characterId);
}
