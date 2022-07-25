package com.alkemy.disney.repository;

import com.alkemy.disney.entity.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface MovieRepository extends JpaRepository<MovieEntity, Long> {

    Optional<MovieEntity> findById(Long id);

    Set<MovieEntity> findByTitle(String title);

    Set<MovieEntity> findByGenresId(Long genreId);

    Set<MovieEntity> findAllByOrderByCreationDateAsc();

    Set<MovieEntity> findAllByOrderByCreationDateDesc();
}
