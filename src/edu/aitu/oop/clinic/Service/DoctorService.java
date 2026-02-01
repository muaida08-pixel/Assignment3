package edu.aitu.oop.clinic.Service;

import edu.aitu.oop.clinic.domain.Doctor;
import edu.aitu.oop.clinic.repository.DoctorRepository;

import java.util.List;

public class DoctorService {
    private final DoctorRepository doctorRepo;

    public DoctorService(DoctorRepository doctorRepo) {
        this.doctorRepo = doctorRepo;
    }

    public void addDoctor(Doctor doctor) {
        doctorRepo.save(doctor);
    }

    public Doctor getDoctorById(long id) {
        return doctorRepo.findById(id);
    }

    public List<Doctor> getAllDoctors() {
        return doctorRepo.findAll();
    }
}
