package com.study.spring.bookstore.publishers.domain.repositories;

import com.study.spring.bookstore.publishers.domain.entities.PublisherEntity;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PublisherRepository extends JpaRepository<PublisherEntity, Integer>, JpaSpecificationExecutor<PublisherEntity> {
    Optional<PublisherEntity> findByName(String name);
}
