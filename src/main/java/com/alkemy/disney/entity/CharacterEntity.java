package com.alkemy.disney.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "characters")
@Getter
@Setter
public class CharacterEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String image;

    private String name;

    private Integer age;

    private Float weight;

    private String history;

    //nombre asignado a la tabla intermedia
    @JoinTable(name = "character_movie",
            //join del lado: character
            joinColumns = @JoinColumn(name = "character_id"),
            //join del lado: movie
            inverseJoinColumns = @JoinColumn(name = "movie_id"))
    @ManyToMany
    private Set<MovieEntity> associatedMovies = new HashSet<>();
}
