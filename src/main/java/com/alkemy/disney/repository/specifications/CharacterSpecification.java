package com.alkemy.disney.repository.specifications;

import com.alkemy.disney.dto.CharacterFiltersDTO;
import com.alkemy.disney.entity.CharacterEntity;
import com.alkemy.disney.entity.MovieEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import java.util.ArrayList;
import java.util.List;

@Component
public class CharacterSpecification{

    public Specification<CharacterEntity> getByFilters(CharacterFiltersDTO filtersDTO) {
        return(root, query, criteriaBuilder) -> {

            //lista de predicados para consultas dinámicas
            List<Predicate> predicates = new ArrayList<>();

            //nombre
            if(StringUtils.hasLength(filtersDTO.getName())) {
                predicates.add(
                        criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("name")),
                                "%" + filtersDTO.getName().toLowerCase() + "%")
                );
            }

            //año
            if(filtersDTO.getAge() != null) {
                predicates.add(
                        criteriaBuilder.like(
                                root.get("age").as(String.class),
                                "%" + filtersDTO.getAge() + "%"
                        )
                );
            }

            //peso
            if(filtersDTO.getWeight() != 0) {
                predicates.add(
                        criteriaBuilder.like(
                                root.get("weight").as(String.class),
                                "%" + filtersDTO.getWeight() + "%"
                        )
                );
            }

            //lista de películas
            if(!CollectionUtils.isEmpty(filtersDTO.getMovies())){
                Join<MovieEntity, CharacterEntity> join = root.join("movies", JoinType.INNER);
                Expression<String> moviesId = join.get("id");
                predicates.add(moviesId.in(filtersDTO.getMovies()));
            }

            //removiendo duplicados
            query.distinct(true);

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

}
