package edu.aitu.oop.clinic.edu.aitu.oop.clinic.demo;

import edu.aitu.oop.clinic.Service.AppointmentService;
import edu.aitu.oop.clinic.domain.Appointment;
import edu.aitu.oop.clinic.domain.Doctor;
import edu.aitu.oop.clinic.domain.Patient;
import edu.aitu.oop.clinic.repository.*;

import java.time.LocalDateTime;
import java.util.List;

public class DemoMain {
    public static void main(String[] args) {

        Doctor doctor = new Doctor(1, "Dr. Ali", "Cardiology",
                List.of(LocalDateTime.now().plusHours(1)));

        Patient patient = new Patient(1, "Sara", "555-123");

        DoctorRepository doctorRepo =
                new InMemoryDoctorRepository(List.of(doctor));
        PatientRepository patientRepo =
                new InMemoryPatientRepository(List.of(patient));
        AppointmentRepository appointmentRepo =
                new InMemoryAppointmentRepository();

        AppointmentService service =
                new AppointmentService(appointmentRepo, doctorRepo, patientRepo);

        Appointment appt =
                service.bookAppointment(1, 1, doctor.getAvailableSlots().get(0));

        System.out.println(appt);
    }
}
