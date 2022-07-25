package com.alkemy.disney.repository;

import com.alkemy.disney.entity.CharacterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface CharacterRepository extends JpaRepository<CharacterEntity, Long> {

    Optional <CharacterEntity> findById(Long id);

    Set<CharacterEntity> findByName(String name);

    Set<CharacterEntity> findByAge(Integer age);

    Set<CharacterEntity> findByMoviesId(Long id);


}
