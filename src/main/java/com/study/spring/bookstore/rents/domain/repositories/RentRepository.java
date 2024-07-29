package com.study.spring.bookstore.rents.domain.repositories;

import com.study.spring.bookstore.books.domain.entities.BookEntity;
import com.study.spring.bookstore.rents.api.controllers.models.DTOs.GetMostRentedBookResponseDTO;
import com.study.spring.bookstore.rents.domain.entities.RentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RentRepository extends JpaRepository<RentEntity, Integer>, JpaSpecificationExecutor<RentEntity> {
    @Query("SELECT r.book FROM RentEntity r GROUP BY r.book ORDER BY COUNT(r.book) DESC")
    List<BookEntity> findMostRentedBook();
}
