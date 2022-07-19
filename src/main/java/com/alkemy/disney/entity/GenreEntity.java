package com.alkemy.disney.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "genre")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SQLDelete(sql = "UPDATE genre SET deleted = true WHERE id=?")
@Where(clause = "deleted = false")
public class GenreEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String image;

    private String name;

    private boolean deleted = Boolean.FALSE;

    @OneToMany(mappedBy = "genre")
    private List<MovieEntity> movies;

}
