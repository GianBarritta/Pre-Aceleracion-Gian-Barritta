package com.alkemy.disney.repository.specifications;

import com.alkemy.disney.dto.MovieFiltersDTO;
import com.alkemy.disney.entity.MovieEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Component
public class MovieSpecification {

    public Specification<MovieEntity> getByFilters(MovieFiltersDTO filtersDTO) {
        return (root, query, criteriaBuilder) -> {

            //lista de predicados para consultas dinámicas
            List<Predicate> predicates = new ArrayList<>();

            //nombre
            if (StringUtils.hasLength(filtersDTO.getName())) {
                predicates.add(
                        criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("name")),
                                "%" + filtersDTO.getName().toLowerCase() + "%"
                        )
                );
            }

            //generoId
            if(filtersDTO.getGenreId() != null)
            {
                predicates.add(
                        criteriaBuilder.like(
                                root.get("genreId").as(String.class),
                                "%" + filtersDTO.getGenreId() + "%"
                        )
                );
            }

            //removiendo duplicados
            query.distinct(true);

            //orden
            String orderByField = "creationDate";
            query.orderBy(
                    filtersDTO.isASC() ?
                            criteriaBuilder.asc(root.get(orderByField)) : criteriaBuilder.desc(root.get(orderByField))
            );

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));

        };


    }
}