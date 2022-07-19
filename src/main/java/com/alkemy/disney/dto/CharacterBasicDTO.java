package com.alkemy.disney.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CharacterBasicDTO {

    private Long id;

    private String image;

    private String name;

    private Integer age;

    private double weight;

    private String history;
}
