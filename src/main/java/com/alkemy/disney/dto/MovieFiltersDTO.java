package com.alkemy.disney.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MovieFiltersDTO {

    private String name;

    private Long genreId;

    private String order;

    public boolean isASC(){
        return order.compareToIgnoreCase("ASC") == 0;
    }

    public boolean isDESC(){
        return order.compareToIgnoreCase("DESC") == 0;
    }


}