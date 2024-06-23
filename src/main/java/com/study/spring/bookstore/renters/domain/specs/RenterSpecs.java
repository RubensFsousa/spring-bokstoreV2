package com.study.spring.bookstore.renters.domain.specs;

import com.study.spring.bookstore.renters.domain.entities.RenterEntity;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

public class RenterSpecs {

    public static Specification<RenterEntity> containsTextInAllColumns(String text) {
        return (root, query, criteriaBuilder) -> {
            if (text == null || text.isEmpty()) {
                return criteriaBuilder.conjunction();
            }

            var likePattern = "%" + text.toLowerCase() + "%";
            Predicate namePredicate = criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), likePattern);

            var predicates = new Predicate[]{
                    namePredicate
            };

            return criteriaBuilder.or(predicates);
        };
    }

    public static Specification<RenterEntity> isDeleted(boolean isDeleted) {
        return (root, query, cb) -> cb.equal(root.get("isDeleted"), isDeleted);
    }
}
