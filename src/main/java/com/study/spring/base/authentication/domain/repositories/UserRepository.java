package com.study.spring.base.authentication.domain.repositories;

import com.study.spring.base.authentication.domain.models.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
}
