package com.example.demo.Patient.Service;

import com.example.demo.Patient.dao.PatientRepository;
import com.example.demo.Patient.dto.DetailPatientDto;
import com.example.demo.Patient.dto.PatientDto;
import com.example.demo.Patient.dto.PatientNameDto;
import com.example.demo.Patient.entity.Patient;
import com.example.demo.Patient.mapper.PatientMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import java.util.*;

@Service
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;


    public Page<Patient> Search(Pageable pageable) {
//        System.out.println("page : "+pageable.getPageNumber());
//        System.out.println("size : "+pageable.getPageSize());

        //Activer un tri par default
        Sort sort = pageable.getSortOr(Sort.by("name"));
        if (pageable.isPaged()) {
            pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);
        } else {
            //bug framework ne supporte pas unpaged avec un tri
            pageable = Pageable.unpaged(sort);
        }


        return patientRepository.findAll(pageable);
    }




    public Optional<DetailPatientDto> findbyId(Long id) {
        return patientRepository.findById(id).map(patientMapper::toDto);


    }
/**/
    public DetailPatientDto Create(@Valid PatientDto patient) {
        return patientMapper.toDto(patientRepository.save(patientMapper.fromDto(patient))) ;

    }


    public Patient update(Long id, Patient patient) {
        patient.setId(id);
        return patientRepository.save(patient);

    }

    public void remove(Long id) {
        patientRepository.deleteAllById(Collections.singleton(id));
    }

    public Page<Patient> findByGender(Patient.Gender gender, Pageable pageable) {

        return patientRepository.findByGender(gender, pageable);
    }
    public Object findRevisions(Long id,Pageable pageable){
        return  patientRepository.findRevisions(id,pageable);

    }

    public Object findRevision(Long id,Long RevisionNumber){
       return   patientRepository.findRevision(id,RevisionNumber);

    }


}