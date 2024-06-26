package com.example.demo.Patient.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@SuperBuilder
@Data
@RequiredArgsConstructor
public class DetailPatientDto extends PatientDto{

    private LocalDateTime createdDate;

}
