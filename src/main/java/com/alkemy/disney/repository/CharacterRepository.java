package com.alkemy.disney.repository;

import com.alkemy.disney.entity.CharacterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CharacterRepository extends JpaRepository<CharacterEntity, Long> {

    Optional<CharacterEntity> findById(Long id);

    List<CharacterEntity> findByName(String name);

    List<CharacterEntity> findByAge(Integer age);

    List<CharacterEntity> findByMoviesId(Long id);
}
