package edu.aitu.oop.clinic.repository;

import edu.aitu.oop.clinic.domain.Patient;
import java.util.List;

public interface PatientRepository {
    Patient findById(Long id);
    List<Patient> findAll();   // ✅ added

    void save(Patient patient);
}
