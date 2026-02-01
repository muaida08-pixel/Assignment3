package edu.aitu.oop.clinic.repository;

import edu.aitu.oop.clinic.domain.Appointment;
import java.time.LocalDateTime;
import java.util.List;

public interface AppointmentRepository {
    Appointment findById(Long id);
    List<Appointment> findByDoctorId(Long doctorId);
    List<Appointment> findByPatientId(Long patientId);
    List<Appointment> findAll();   // ✅ added
    void save(Appointment appointment);
    void update(Appointment appointment);
    boolean existsByDoctorAndTime(Long doctorId, LocalDateTime time);
    Long nextId();
}
