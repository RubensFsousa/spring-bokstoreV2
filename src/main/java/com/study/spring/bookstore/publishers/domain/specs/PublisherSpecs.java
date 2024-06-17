package com.study.spring.bookstore.publishers.domain.specs;

import com.study.spring.bookstore.publishers.domain.entities.PublisherEntity;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

public class PublisherSpecs {

    public static Specification<PublisherEntity> containsTextInAllColumns(String text) {
        return (root, query, criteriaBuilder) -> {
            if (text == null || text.isEmpty()) {
                return criteriaBuilder.conjunction();
            }

            var likePattern = "%" + text.toLowerCase() + "%";
            Predicate namePredicate = criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), likePattern);

            var predicates = new Predicate[] {
                    namePredicate
            };

            return criteriaBuilder.or(predicates);
        };
    }

}
