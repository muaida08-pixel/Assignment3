package edu.aitu.oop.clinic.repository;

import edu.aitu.oop.clinic.domain.Appointment;
import java.time.LocalDateTime;
import java.util.List;

public interface AppointmentRepository extends IRepository<Appointment> {

    void update(Appointment appointment);

    List<Appointment> findByDoctorId(Long doctorId);
    List<Appointment> findByPatientId(Long patientId);

    boolean existsByDoctorAndTime(Long doctorId, LocalDateTime time);

    Long nextId();
}