package com.example.demo.iam.entity;

import com.example.demo.commons.jpa.entity.CustomAbstractAuditable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "authorities")
@SuperBuilder
@ToString
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class Authority extends CustomAbstractAuditable<Long> {
    @Column(length = 100,unique = true)
    private String authorityName;


}
