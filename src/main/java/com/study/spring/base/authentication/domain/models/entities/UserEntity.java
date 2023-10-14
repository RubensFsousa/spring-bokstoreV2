package com.study.spring.base.authentication.domain.models.entities;

import com.study.spring.base.shared.models.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users_tb")
public class UserEntity extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "password")
    private String password;

}
