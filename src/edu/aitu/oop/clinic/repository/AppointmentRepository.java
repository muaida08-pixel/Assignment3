package edu.aitu.oop.clinic.repository;

import edu.aitu.oop.clinic.domain.Appointment;

import java.util.List;
import java.util.Optional;

public interface AppointmentRepository {

    void save(Appointment appointment);

    Optional<Appointment> findById(Long id);

    List<Appointment> findByDoctorId(Long doctorId);

    List<Appointment> findByPatientId(Long patientId);
}