package com.study.spring.bookstore.books.domain.entities;

import com.study.spring.base.shared.models.BaseEntity;
import com.study.spring.bookstore.publishers.domain.entities.PublisherEntity;
import com.study.spring.bookstore.rents.domain.entities.RentEntity;
import com.study.spring.bookstore.rents.domain.enums.RentStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

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
    @Column(name = "total_quantity", nullable = false)
    private Integer totalQuantity;
    @Column(name = "launch_date", nullable = false)
    private LocalDate launchDate;
    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "publisher_id")
    private PublisherEntity publisher;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "book")
    private Set<RentEntity> rents = new HashSet<>();

    public Integer getAvailableQuantity() {
        return getTotalQuantity() - getRents().stream().filter(rent -> !rent.getStatus().equals(RentStatus.DELIVERED)).toList().size();
    }
}
