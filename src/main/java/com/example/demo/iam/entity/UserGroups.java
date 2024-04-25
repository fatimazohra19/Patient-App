package com.example.demo.iam.entity;

import com.example.demo.commons.jpa.entity.CustomAbstractAuditable;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "group_members")
@SuperBuilder
@ToString
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class UserGroups extends CustomAbstractAuditable<Long> {
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;


}
