package com.study.spring.base.authentication.domain.specs;

import com.study.spring.bookstore.books.domain.entities.BookEntity;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

public class UserSpecs {

    public static Specification<BookEntity> containsTextInAllColumns(String text) {
        return (root, query, criteriaBuilder) -> {
            if (text == null || text.isEmpty()) {
                return criteriaBuilder.conjunction();
            }

            var likePattern = "%" + text.toLowerCase() + "%";
            Predicate namePredicate = criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), likePattern);
            Predicate rolePredicate = criteriaBuilder.like(criteriaBuilder.lower(root.get("role")), likePattern);

            var predicates = new Predicate[] {
                    namePredicate,
                    rolePredicate
            };

            return criteriaBuilder.or(predicates);
        };
    }

}
