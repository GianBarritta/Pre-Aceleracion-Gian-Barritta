package com.alkemy.disney.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "movie")
@Getter
@Setter
public class MovieEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String image;

    private String title;

    @Column(name = "creation_date")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private LocalDateTime creationDate;

    private Integer score;

    @ManyToMany(mappedBy = "movies")
    private Set<Character> characters = new HashSet<>();


    //nombre asignado a la tabla intermedia
    @JoinTable(name = "movie_genre",
            //join del lado de: movie
            joinColumns = @JoinColumn(name = "movie_id"),
            //join del lado de: genre
            inverseJoinColumns = @JoinColumn(name = "genre_id"))
    @ManyToMany
    private Set<GenreEntity> associatedGenres = new HashSet<>();
}
