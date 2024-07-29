package com.study.spring.bookstore.renters.domain.repositories;

import com.study.spring.bookstore.renters.domain.entities.RenterEntity;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RenterRepository extends JpaRepository<RenterEntity, Integer>, JpaSpecificationExecutor<RenterEntity> {
    Optional<RenterEntity> findByName(String name);

    Optional<RenterEntity> findByCpf(String name);

    Optional<RenterEntity> findByTelephone(String telephone);

    Optional<RenterEntity> findByEmail(String email);
}
