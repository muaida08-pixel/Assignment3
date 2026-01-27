package edu.aitu.oop.clinic.repository;

import edu.aitu.oop.clinic.domain.Patient;
import java.util.List;

public interface PatientRepository {
    Patient findById(int id);
    List<Patient> findAll();

    void save(Patient patient);
}
