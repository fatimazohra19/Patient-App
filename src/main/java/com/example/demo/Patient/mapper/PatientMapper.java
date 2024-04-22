package com.example.demo.Patient.mapper;


import com.example.demo.Patient.dto.DetailPatientDto;
import com.example.demo.Patient.dto.PatientDto;
import com.example.demo.Patient.dto.PatientNameDto;
import com.example.demo.Patient.entity.Patient;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PatientMapper {
    @Mapping(source ="familyStatus",target = "status")
    DetailPatientDto toDto(Patient source);

    @Mapping(source ="status",target = "familyStatus")

    Patient fromDto(PatientDto source);

    List<PatientDto> PatientToPationDTOList(List<Patient> patients);

    Patient fromPatienNameDto(PatientNameDto dto);

    PatientNameDto TOPatientNameDto(Patient entity);
/**/
    Patient toEntity(PatientDto patientDto);
}
