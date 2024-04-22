package com.example.demo.Patient.dto;

import com.example.demo.Patient.entity.Patient;

import lombok.Builder;

@Builder
public record PatientNameDto (Long id, String name, Patient.Gender gender) {
   // public record PatientNameDto (Long id, String name) {

}