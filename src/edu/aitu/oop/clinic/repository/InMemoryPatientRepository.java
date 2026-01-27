package edu.aitu.oop.clinic.repository;

import edu.aitu.oop.clinic.domain.Patient;
import java.util.ArrayList;
import java.util.List;

public class InMemoryPatientRepository implements PatientRepository {
    private final List<Patient> patients = new ArrayList<>();

    public InMemoryPatientRepository(List<Patient> seed) { patients.addAll(seed); }

    @Override
    public Patient findById(int id) {
        return patients.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
    }

    @Override
    public List<Patient> findAll() { return new ArrayList<>(patients); }

    @Override
    public void save(Patient patient) {

    }
}
