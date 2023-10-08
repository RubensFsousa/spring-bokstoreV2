package com.study.spring.studyV1.users.models;

import com.study.spring.base.shared.models.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users_tb")
public class UserEntity extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "password")
    private String password;

}
