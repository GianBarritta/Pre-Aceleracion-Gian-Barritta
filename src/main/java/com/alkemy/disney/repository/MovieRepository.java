package com.alkemy.disney.repository;

import com.alkemy.disney.entity.MovieEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface MovieRepository extends JpaRepository<MovieEntity, Long> {

    Set<MovieEntity> findAll(Specification<MovieEntity> spec);
}
