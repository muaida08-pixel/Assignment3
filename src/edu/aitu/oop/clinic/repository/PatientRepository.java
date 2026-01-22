package edu.aitu.oop.clinic.repository;

import edu.aitu.oop.clinic.domain.Patient;

public interface PatientRepository {

    void save(Patient patient);

}