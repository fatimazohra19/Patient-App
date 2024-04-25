package com.example.demo.iam.entity;

import com.example.demo.commons.jpa.entity.CustomAbstractAuditable;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "groups")
@SuperBuilder
@ToString
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class Group extends CustomAbstractAuditable<Long> {
    @Column(length = 100,unique = true,nullable = false)
    private String groupName;
    private String description;

    @ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name = "group_id"))
    private Set<Authority> authorities;


}
