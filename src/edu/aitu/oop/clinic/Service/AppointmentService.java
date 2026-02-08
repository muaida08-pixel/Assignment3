package edu.aitu.oop.clinic.Service;

import edu.aitu.oop.clinic.Exception.AppointmentNotFoundException;
import edu.aitu.oop.clinic.domain.Appointment;
import edu.aitu.oop.clinic.domain.Doctor;
import edu.aitu.oop.clinic.domain.Patient;
import edu.aitu.oop.clinic.domain.Result;
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

    // ===== Booking with Builder Pattern & Result Wrapper (Milestone 2) =====
    public Result<Appointment> bookAppointment(Long doctorId, Long patientId, LocalDateTime time) {
        try {
            Doctor doctor = doctorRepo.findById(doctorId);
            Patient patient = patientRepo.findById(patientId);

            // Вместо выброса исключений используем Result.failure для стабильности API
            if (doctor == null) return Result.failure("Doctor not found.");
            if (patient == null) return Result.failure("Patient not found.");

            // Проверка доступности врача (бизнес-логика) [cite: 49]
            if (!doctor.isAvailableAt(time)) {
                return Result.failure("Doctor is not available at " + time);
            }

            // Проверка на конфликт времени в базе данных [cite: 49]
            if (appointmentRepo.existsByDoctorAndTime(doctorId, time)) {
                return Result.failure("Time slot " + time + " is already booked.");
            }

            Long id = appointmentRepo.nextId();

            // Использование паттерна Builder для создания объекта
            Appointment appointment = new Appointment.Builder()
                    .id(id)
                    .doctor(doctor)
                    .patient(patient)
                    .date(time.toLocalDate())
                    .time(time.toLocalTime())
                    .status("BOOKED")
                    .build();

            appointmentRepo.save(appointment);
            return Result.success(appointment, "Appointment booked successfully!");

        } catch (Exception e) {
            return Result.failure("System error: " + e.getMessage());
        }
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

    // ===== New Lambdas & Streams (Java Features) =====
    public List<Appointment> getTodaysAppointments() {
        return appointmentRepo.findAll()
                .stream()
                .filter(a -> a.getDate().equals(LocalDate.now()))
                .collect(Collectors.toList()); // Использование Stream API
    }

    public List<Doctor> sortDoctorsByName() {
        return doctorRepo.findAll()
                .stream()
                .sorted(Comparator.comparing(Doctor::getName)) // Сортировка через лямбда-выражение
                .collect(Collectors.toList());
    }
}