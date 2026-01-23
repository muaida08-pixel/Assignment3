package edu.aitu.oop.clinic.repository;

import edu.aitu.oop.clinic.domain.Appointment;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class InMemoryAppointmentRepository implements AppointmentRepository {
    private final Map<Integer, Appointment> storage = new HashMap<>();
    private int seq = 1;

    @Override
    public Appointment findById(int id) { return storage.get(id); }

    @Override
    public void save(Appointment appointment) { storage.put(appointment.getId(), appointment); }

    @Override
    public void update(Appointment appointment) { storage.put(appointment.getId(), appointment); }

    @Override
    public void delete(int id) { storage.remove(id); }

    @Override
    public boolean existsByDoctorAndTime(int doctorId, LocalDateTime time) {
        return storage.values().stream()
                .anyMatch(a -> a.getDoctor().getId() == doctorId && a.getTime().equals(time) && "BOOKED".equals(a.getStatus()));
    }

    @Override
    public List<Appointment> findByPatientId(int patientId) {
        return storage.values().stream()
                .filter(a -> a.getPatient().getId() == patientId && "BOOKED".equals(a.getStatus()))
                .sorted(Comparator.comparing(Appointment::getTime))
                .collect(Collectors.toList());
    }

    @Override
    public List<Appointment> findByDoctorId(int doctorId) {
        return storage.values().stream()
                .filter(a -> a.getDoctor().getId() == doctorId && "BOOKED".equals(a.getStatus()))
                .sorted(Comparator.comparing(Appointment::getTime))
                .collect(Collectors.toList());
    }

    @Override
    public int nextId() { return seq++; }
}
