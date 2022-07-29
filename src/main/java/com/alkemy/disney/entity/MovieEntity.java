package com.alkemy.disney.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "movie")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SQLDelete(sql = "UPDATE movie SET deleted = true WHERE id=?")
@Where(clause = "deleted = false")
public class MovieEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String image;

    private String title;

    @Column(name = "creation_date")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private LocalDate creationDate;

    @Min(value = 1, message = "El minimo es 1")
    @Max(value = 5, message = "El maximo es 5")
    private Integer score;

    private boolean deleted = Boolean.FALSE;

    @ManyToMany(
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "character_movie", // nombre asignado a la tabla intermedia
            // join del lado: movie
            joinColumns = @JoinColumn(name = "movie_id"),
            // join del lado: character
            inverseJoinColumns = @JoinColumn(name = "character_id"))
    private Set<CharacterEntity> characters = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "genre_id")
    private GenreEntity genre;

    @Column(name = "genre_id", nullable = false)
    private Long genreId;

    public void addCharacter(CharacterEntity character) {
        this.getCharacters().add(character);
    }

    public  void removeCharacter(CharacterEntity character) {
        this.getCharacters().remove(character);
    }
}
