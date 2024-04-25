package com.example.demo.Patient.entity;

//import com.example.demo.commons.dto.Address;

//import io.hypersistence.utils.hibernate.type.json.JsonType;

//import com.example.demo.commons.jpa.entity.CustomAbstractFullAuditable;
import com.example.demo.commons.jpa.entity.CustomAbstractFullAuditable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Type;
import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;

import java.time.LocalDate;

//@Audited
@Entity
@SuperBuilder
@ToString
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Audited
public class Patient extends CustomAbstractFullAuditable<Long> {

    @Column(length=100)
    @NotBlank
    @Length(max=100)
    private String name;
    @Column(name = "birth_date")
    private LocalDate birthdate;
   /* @Column(length = 100);
    @NotBlank @Size(max = 100);*/
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private Gender gender;
    @Enumerated(EnumType.STRING)
    @Column(length = 60)
    private FamilyStatus familyStatus;
    @Column(length = 120)
    private String profession;
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private Mutual mutual;

//    @Type(JsonType.class)
//    @Column(columnDefinition ="jsonb")
//  private Address address;



    //Auditing

    public enum Gender {
        MALE, FEMALE
    }
    public enum FamilyStatus {
        SINGLE, MARRIED, DIVORCED, WIDOWED, SEPARATED
    }
}
