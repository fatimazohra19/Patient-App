package com.example.demo.Patient.web;

import com.example.demo.Patient.Service.PatientService;
import com.example.demo.Patient.dto.DetailPatientDto;
import com.example.demo.Patient.dto.PatientDto;
import com.example.demo.Patient.entity.Patient;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/Patients")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @GetMapping
    public Page<Patient> Search(Pageable pageable,@RequestParam(required = false)Boolean all){
        //Cette condition vérifie si le paramètre all est vrai.
        // Si c'est le cas (all est true), cela signifie qu'on veut retourner tous les résultats sans pagination.
        if(Objects.equals(all,false)){
            //crée un Pageable non paginé avec le même critère de tri que pageable.
            // Ainsi, si all est vrai, pageable sera remplacé par un Pageable non paginé.
            pageable=Pageable.unpaged(pageable.getSort());
        }

    return  patientService.Search(pageable) ;
    }

    @GetMapping("/{id}")
    public DetailPatientDto getById(@PathVariable Long id){

        return  patientService.findbyId(id).orElseThrow();
    }

    @GetMapping("/by-gender")
    public Page<Patient> findByGender(Patient.Gender gender,Pageable pageable) {
        return patientService.findByGender(gender,pageable);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DetailPatientDto create(@Valid @RequestBody PatientDto patient){
        return patientService.Create(patient);

    }

    @PutMapping("/{id}")
    public Patient update(@PathVariable Long id, @Valid @RequestBody Patient patient){
        return patientService.update(id,patient);
    }



    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)

    public void remove(@PathVariable Long id){

        patientService.remove(id);
    }

    @GetMapping("/{id}/revisions")
    public Object findRevisions(@PathVariable Long id, Pageable pageable) {
        return patientService.findRevisions(id, pageable);
    }

    @GetMapping("/{id}/revisions/{revision}")
    public Object getRevision(@PathVariable Long id, @PathVariable Long revision) {
        return patientService.findRevision(id, revision);
    }
}
