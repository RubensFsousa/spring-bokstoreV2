package com.study.spring.bookstore.rents.domain.specs;

import com.study.spring.bookstore.rents.domain.entities.RentEntity;
import com.study.spring.bookstore.rents.domain.enums.RentStatus;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

public class RentSpecs {

    public static Specification<RentEntity> containsTextInAllColumns(String text) {
        return (root, query, criteriaBuilder) -> {
            if (text == null || text.isEmpty()) {
                return criteriaBuilder.conjunction();
            }

            var likePattern = "%" + text.toLowerCase() + "%";
            var bookJoin = root.join("book", JoinType.LEFT);
            var publisherJoin = bookJoin.join("publisher", JoinType.LEFT);
            var renterJoin = root.join("renter", JoinType.LEFT);
            Predicate bookNamePredicate = criteriaBuilder.like(criteriaBuilder.lower(bookJoin.get("name")), likePattern);
            Predicate bookAuthorPredicate = criteriaBuilder.like(criteriaBuilder.lower(bookJoin.get("author")), likePattern);
            Predicate publisherNamePredicate = criteriaBuilder.like(criteriaBuilder.lower(publisherJoin.get("name")), likePattern);
            Predicate renterNamePredicate = criteriaBuilder.like(criteriaBuilder.lower(renterJoin.get("name")), likePattern);

            var predicates = new Predicate[] {
                    bookNamePredicate,
                    bookAuthorPredicate,
                    publisherNamePredicate,
                    renterNamePredicate
            };

            return criteriaBuilder.or(predicates);
        };
    }

    public static Specification<RentEntity> bookNameContains(String text) {
        return (root, query, criteriaBuilder) -> {
            if (text == null || text.isEmpty()) {
                return criteriaBuilder.conjunction();
            }
            var likePattern = "%" + text.toLowerCase() + "%";
            return criteriaBuilder.like(criteriaBuilder.lower(root.join("book", JoinType.LEFT).get("name")), likePattern);
        };
    }

    public static Specification<RentEntity> bookAuthorContains(String text) {
        return (root, query, criteriaBuilder) -> {
            if (text == null || text.isEmpty()) {
                return criteriaBuilder.conjunction();
            }
            var likePattern = "%" + text.toLowerCase() + "%";
            return criteriaBuilder.like(criteriaBuilder.lower(root.join("book", JoinType.LEFT).get("author")), likePattern);
        };
    }

    public static Specification<RentEntity> bookPublisherNameContains(String text) {
        return (root, query, criteriaBuilder) -> {
            if (text == null || text.isEmpty()) {
                return criteriaBuilder.conjunction();
            }
            var likePattern = "%" + text.toLowerCase() + "%";
            return criteriaBuilder.like(criteriaBuilder.lower(root.join("book", JoinType.LEFT)
                    .join("publisher", JoinType.LEFT).get("name")), likePattern);
        };
    }

    public static Specification<RentEntity> rentStatusEquals(RentStatus status) {
        return (root, query, criteriaBuilder) -> {
            if (status == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("status"), status);
        };
    }

    public static Specification<RentEntity> renterNameContains(String text) {
        return (root, query, criteriaBuilder) -> {
            if (text == null || text.isEmpty()) {
                return criteriaBuilder.conjunction();
            }
            var likePattern = "%" + text.toLowerCase() + "%";
            return criteriaBuilder.like(criteriaBuilder.lower(root.join("renter", JoinType.LEFT).get("name")), likePattern);
        };
    }

}
