package com.study.spring.base.shared.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    protected UUID id;

    @Column(name = "created_at", nullable = false)
    protected OffsetDateTime createdAT;

    @Column(name = "updated_at")
    protected OffsetDateTime updatedAt;

    @PrePersist
    private void onCreate() {
        createdAT = updatedAt = OffsetDateTime.now();
    }

    @PreUpdate
    private void onUpdate() {
        updatedAt = OffsetDateTime.now();
    }

}
