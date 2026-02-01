package edu.aitu.oop.clinic.repository;

import edu.aitu.oop.clinic.domain.Doctor;
import java.util.List;

public interface DoctorRepository {
    Doctor findById(Long id);
    List<Doctor> findAll();

    void save(Doctor doctor);
}
