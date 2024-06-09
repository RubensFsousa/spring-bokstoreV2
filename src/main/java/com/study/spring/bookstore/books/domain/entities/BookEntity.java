package com.study.spring.bookstore.books.domain.entities;

import com.study.spring.base.shared.models.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Entity
@Table(name = "books_tb")
@Getter
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class BookEntity extends BaseEntity {
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "author", nullable = false)
    private String author;
    @Column(name = "available_quantity", nullable = false)
    private int availableQuantity;
    @Column(name = "launch_date", nullable = false)
    private LocalDate launchDate;

//TODO: add pub entity
//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "publisher_id", referencedColumnName = "id")
//    private String publisher;

}
