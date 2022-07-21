package com.alkemy.disney.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CharacterBasicDTO {

    private Long id;

    private String image;

    @NotNull
    private String name;

    private Integer age;

    private double weight;

    private String history;
}
