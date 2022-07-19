package com.alkemy.disney.repository;

import com.alkemy.disney.entity.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<MovieEntity, Long> {

    Optional<MovieEntity> findById(Long id);

    List<MovieEntity> findByTitle(String title);

    List<MovieEntity> findByGenresId(Long genreId);

    List<MovieEntity> findAllByOrderByCreationDateAsc();

    List<MovieEntity> findAllByOrderByCreationDateDesc();
}
