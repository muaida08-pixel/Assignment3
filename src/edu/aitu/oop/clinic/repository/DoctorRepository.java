package edu.aitu.oop.clinic.repository;

import edu.aitu.oop.clinic.domain.Doctor;

public interface DoctorRepository {
    void save(Doctor doctor);
    Doctor findById(Long id);
}
