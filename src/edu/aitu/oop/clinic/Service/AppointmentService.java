package edu.aitu.oop.clinic.Service;

import edu.aitu.oop.clinic.Exception.AppointmentNotFoundException;
import edu.aitu.oop.clinic.Exception.DoctorUnavailableException;
import edu.aitu.oop.clinic.Exception.TimeSlotAlreadyBookedException;
import edu.aitu.oop.clinic.domain.Appointment;
import edu.aitu.oop.clinic.domain.Doctor;
import edu.aitu.oop.clinic.domain.Patient;
import edu.aitu.oop.clinic.repository.AppointmentRepository;
import edu.aitu.oop.clinic.repository.DoctorRepository;
import edu.aitu.oop.clinic.repository.PatientRepository;

import java.time.LocalDateTime;
import java.util.List;

public class AppointmentService {
    private final AppointmentRepository appointmentRepo;
    private final DoctorRepository doctorRepo;
    private final PatientRepository patientRepo;

    public AppointmentService(AppointmentRepository appointmentRepo,
                              DoctorRepository doctorRepo,
                              PatientRepository patientRepo) {
        this.appointmentRepo = appointmentRepo;
        this.doctorRepo = doctorRepo;
        this.patientRepo = patientRepo;
    }

    public Appointment bookAppointment(Long doctorId, Long patientId, LocalDateTime time) {
        Doctor doctor = doctorRepo.findById(doctorId);
        Patient patient = patientRepo.findById(patientId);

        if (doctor == null) throw new DoctorUnavailableException("Doctor not found.");
        if (patient == null) throw new AppointmentNotFoundException("Patient not found.");

        if (!doctor.isAvailableAt(time)) {
            throw new DoctorUnavailableException("Doctor is not available at " + time + ".");
        }

        if (appointmentRepo.existsByDoctorAndTime(doctorId, time)) {
            throw new TimeSlotAlreadyBookedException("Time slot " + time + " is already booked.");
        }

        Long id = appointmentRepo.nextId();

        Appointment appointment = new Appointment(id, doctor, patient, time, "BOOKED");
        appointmentRepo.save(appointment);
        return appointment;
    }

    public void cancelAppointment(Long appointmentId) {
        Appointment appointment = appointmentRepo.findById(appointmentId);
        if (appointment == null) throw new AppointmentNotFoundException("Appointment with id " + appointmentId + " not found.");

        Appointment cancelled = new Appointment(
                appointment.getId(),
                appointment.getDoctor(),
                appointment.getPatient(),
                appointment.getTime(),
                "CANCELLED"
        );
        appointmentRepo.update(cancelled);
    }

    public List<Appointment> getPatientUpcomingAppointments(Long patientId) {
        return appointmentRepo.findByPatientId(patientId);
    }

    public List<Appointment> getDoctorSchedule(Long doctorId) {
        return appointmentRepo.findByDoctorId(doctorId);
    }
}