package edu.aitu.oop.clinic.repository;

import edu.aitu.oop.clinic.domain.Doctor;
import java.util.ArrayList;
import java.util.List;

public class InMemoryDoctorRepository implements DoctorRepository {
    private final List<Doctor> doctors = new ArrayList<>();

    public InMemoryDoctorRepository(List<Doctor> seed) { doctors.addAll(seed); }

    @Override
    public Doctor findById(int id) {
        return doctors.stream().filter(d -> d.getId() == id).findFirst().orElse(null);
    }

    @Override
    public List<Doctor> findAll() { return new ArrayList<>(doctors); }

    @Override
    public void save(Doctor doctor) {

    }
}
