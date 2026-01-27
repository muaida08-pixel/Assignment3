package edu.aitu.oop.clinic.repository;

import edu.aitu.oop.clinic.domain.Appointment;
import java.time.LocalDateTime;
import java.util.List;

public interface AppointmentRepository {
    Appointment findById(int id);
    void save(Appointment appointment);
    void update(Appointment appointment);
    void delete(int id);
    boolean existsByDoctorAndTime(int doctorId, LocalDateTime time);
    List<Appointment> findByPatientId(int patientId);
    List<Appointment> findByDoctorId(int doctorId);
    int nextId();
}
