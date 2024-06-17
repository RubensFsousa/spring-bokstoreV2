package com.study.spring.bookstore.books.domain.specs;

import com.study.spring.bookstore.books.domain.entities.BookEntity;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class BookSpecs {

    public static Specification<BookEntity> containsTextInAllColumns(String text) {
        return (root, query, criteriaBuilder) -> {
            if (text == null || text.isEmpty()) {
                return criteriaBuilder.conjunction();
            }

            var likePattern = "%" + text.toLowerCase() + "%";
            Predicate namePredicate = criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), likePattern);
            Predicate authorPredicate = criteriaBuilder.like(criteriaBuilder.lower(root.get("author")), likePattern);

            var predicates = new Predicate[] {
                    namePredicate,
                    authorPredicate
            };

            return criteriaBuilder.or(predicates);
        };
    }

    public static Specification<BookEntity> availableQuantityEquals(Integer quantity) {
        return (root, query, criteriaBuilder) -> {
            if (quantity == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("availableQuantity"), quantity);
        };
    }

    public static Specification<BookEntity> launchDateEquals(LocalDate date) {
        return (root, query, criteriaBuilder) -> {
            if (date == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("launchDate"), date);
        };
    }
}
