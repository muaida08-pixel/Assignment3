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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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

    // ===== Booking with Builder Pattern =====
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

        // ✅ Use Builder instead of constructor
        Appointment appointment = new Appointment.Builder()
                .id(id.longValue())
                .doctor(doctor)
                .patient(patient)
                .date(time.toLocalDate())
                .time(time.toLocalTime())
                .status("BOOKED")
                .build();

        appointmentRepo.save(appointment);
        return appointment;
    }

    // ===== Cancel Appointment =====
    public void cancelAppointment(Long appointmentId) {
        Appointment appointment = appointmentRepo.findById(appointmentId);
        if (appointment == null) throw new AppointmentNotFoundException("Appointment with id " + appointmentId + " not found.");

        Appointment cancelled = new Appointment.Builder()
                .id(appointment.getId())
                .doctor(appointment.getDoctor())
                .patient(appointment.getPatient())
                .date(appointment.getDate())
                .time(appointment.getTime())
                .status("CANCELLED")
                .build();

        appointmentRepo.update(cancelled);
    }

    // ===== Existing Methods =====
    public List<Appointment> getPatientUpcomingAppointments(Long patientId) {
        return appointmentRepo.findByPatientId(patientId);
    }

    public List<Appointment> getDoctorSchedule(Long doctorId) {
        return appointmentRepo.findByDoctorId(doctorId);
    }

    // ===== New Lambdas & Streams =====
    // Filter today's appointments
    public List<Appointment> getTodaysAppointments() {
        return appointmentRepo.findAll()
                .stream()
                .filter(a -> a.getDate().equals(LocalDate.now()))
                .collect(Collectors.toList());
    }

    // Sort doctors by name
    public List<Doctor> sortDoctorsByName() {
        return doctorRepo.findAll()
                .stream()
                .sorted(Comparator.comparing(Doctor::getName))
                .collect(Collectors.toList());
    }
}
