package com.example.demo.Patient.Service;

import com.example.demo.Patient.dao.PatientRepository;
import com.example.demo.Patient.dto.DetailPatientDto;
import com.example.demo.Patient.dto.PatientDto;
import com.example.demo.Patient.dto.PatientNameDto;
import com.example.demo.Patient.entity.Patient;
import com.example.demo.Patient.mapper.PatientMapper;
import com.example.demo.commons.exeptions.IncoherentException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import java.util.*;

@Service
@RequiredArgsConstructor
public class PatientService {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;


    public Page<Patient> Search(Pageable pageable) {
//        System.out.println("page : "+pageable.getPageNumber());
//        System.out.println("size : "+pageable.getPageSize());

        log.trace("Search patient");
        //Activer un tri par default
        Sort sort = pageable.getSortOr(Sort.by("name"));
        if (pageable.isPaged()) {
            log.debug("page:{}",pageable.getPageNumber());
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
    public DetailPatientDto Create(PatientDto patient) {
        if(patient.getName().length()<=2){
            String reason = "Le nom doit avoir min 3 caractere";
            log.warn(reason);
            throw new IncoherentException(reason);
        }
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