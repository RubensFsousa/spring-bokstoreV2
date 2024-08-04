package com.study.spring.bookstore.rents.domain.entities;


import com.study.spring.base.shared.models.BaseEntity;
import com.study.spring.bookstore.books.domain.entities.BookEntity;
import com.study.spring.bookstore.renters.domain.entities.RenterEntity;
import com.study.spring.bookstore.rents.domain.enums.RentStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Entity
@Table(name = "rents_tb")
@Getter
@Setter
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class RentEntity extends BaseEntity {

    @Column(name = "delivered_date")
    private LocalDate devolutionDate;
    @Column(name = "deadline_date", nullable = false)
    private LocalDate deadLineDate;
    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private RentStatus status;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "id")
    private RenterEntity renter;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "id")
    private BookEntity book;
}
