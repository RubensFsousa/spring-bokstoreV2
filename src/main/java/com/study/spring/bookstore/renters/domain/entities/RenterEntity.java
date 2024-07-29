package com.study.spring.bookstore.renters.domain.entities;

import com.study.spring.base.shared.models.BaseEntity;
import com.study.spring.bookstore.rents.domain.entities.RentEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "renters_tb")
@Getter
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class RenterEntity extends BaseEntity {
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "telephone", nullable = false)
    private String telephone;
    @Column(name = "address", nullable = false)
    private String address;
    @Column(name = "cpf")
    private String cpf;
    @Column(name = "is_deleted", nullable = false)
    private boolean isDeleted;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "renter")
    private Set<RentEntity> rents = new HashSet<>();
}
