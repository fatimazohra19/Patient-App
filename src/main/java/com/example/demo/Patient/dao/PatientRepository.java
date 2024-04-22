package com.example.demo.Patient.dao;

import com.example.demo.Patient.entity.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;

import java.util.List;

public interface PatientRepository extends JpaRepository<Patient, Long> ,
        RevisionRepository<Patient,Long ,Long> {

    /*Cette déclaration indique que PatientRepository est une interface qui étend JpaRepository,
    une interface de Spring Data JPA permettant d'effectuer des opérations de base de données
    CRUD (Create, Read, Update, Delete) sur l'entité Patient.
Patient : Spécifie le type de l'entité gérée par ce repository. Dans ce cas, il s'agit de l'entité Patient.
Long : Spécifie le type de l'identifiant (ID) de l'entité Patient.
Les méthodes disponibles dans JpaRepository incluent des opérations telles que save(), findById(),
findAll(), deleteById(), etc., qui peuvent être utilisées pour interagir avec la base de données
pour l'entité Patient.
En plus d'étendre JpaRepository, cette interface PatientRepository étend également RevisionRepository,
qui est utilisé pour gérer les révisions (ou les versions) des entités auditées.
Patient : Spécifie le type de l'entité pour laquelle les révisions sont suivies.
Long : Spécifie le type de l'identifiant (ID) de l'entité Patient.
Long : Spécifie le type de l'identifiant (ID) de la révision.
Avec RevisionRepository, vous pouvez accéder aux méthodes pour récupérer les révisions d'une entité,
par exemple, findRevisions(), findRevision(), etc.
Ces fonctionnalités sont utilisées pour suivre les modifications apportées à une entité et
récupérer les différentes versions de cette entité à différents moments.*/
    List<Patient> findByGender(Patient.Gender gender);
    Page<Patient> findByGender(Patient.Gender gender, Pageable pageable);

    List<Patient> findByProfessionContainsIgnoreCase(String profession);
}