package com.example.demo.iam.entity;

import com.example.demo.commons.jpa.entity.CustomAbstractAuditable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Entity
@Table(name = "users")
@SuperBuilder
@ToString
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class User extends CustomAbstractAuditable<UUID> {
    @Column(length = 120)
    private String username;
    private String password;
    private boolean enabled;

}
