package edu.aitu.oop.clinic.Service;

import edu.aitu.oop.clinic.domain.Patient;
import edu.aitu.oop.clinic.repository.PatientRepository;

import java.util.List;

public class PatientService {
    private final PatientRepository patientRepo;

    public PatientService(PatientRepository patientRepo) {
        this.patientRepo = patientRepo;
    }

    public void addPatient(Patient patient) {
        patientRepo.save(patient);
    }

    public Patient getPatientById( long id) {
        return patientRepo.findById(id);
    }

    public List<Patient> getAllPatients() {
        return patientRepo.findAll();
    }
}
