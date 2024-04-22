package com.example.demo.Patient.dto;

import com.example.demo.Patient.entity.Mutual;
import com.example.demo.Patient.entity.Patient;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.boot.autoconfigure.amqp.RabbitConnectionDetails;

import java.time.LocalDate;

@SuperBuilder
@Data
@RequiredArgsConstructor
public class PatientDto {
    private Long id;
    @NotBlank
    @Size(max = 100)
    private String name;
    private LocalDate birthdate;
    private Patient.Gender gender;
    private Patient.FamilyStatus status;
    private String profession;
    private Mutual mutual;
    //private Address address;

}