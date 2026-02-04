
package edu.aitu.oop.clinic.demo;

import edu.aitu.oop.clinic.Service.AppointmentService;
import edu.aitu.oop.clinic.domain.Appointment;
import edu.aitu.oop.clinic.domain.Doctor;

import edu.aitu.oop.clinic.repository.*;

import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        try {
            DoctorRepository docRepo = new JdbcDoctorRepository();
            PatientRepository patRepo = new JdbcPatientRepository();
            AppointmentRepository appRepo = new JdbcAppointmentRepository();

            AppointmentService service = new AppointmentService(appRepo, docRepo, patRepo);

            Scanner scanner = new Scanner(System.in);
            boolean running = true;

            System.out.println("=== Medical Clinic System (JDBC Version) ===");

            while (running) {
                System.out.println("\nSelect an option:");
                System.out.println("1. Book an Appointment");
                System.out.println("2. Cancel an Appointment");
                System.out.println("3. Show Doctor's Schedule");
                System.out.println("4. Show Patient's Appointments");
                System.out.println("5. Sort Doctors A-Z");   // ✅
                System.out.println("0. Exit");
                System.out.print("Enter choice: ");

                if (scanner.hasNextInt()) {
                    int choice = scanner.nextInt();

                    switch (choice) {
                        case 1:
                            System.out.print("Enter Doctor ID: ");
                            Long docId = scanner.nextLong();
                            System.out.print("Enter Patient ID: ");
                            Long patId = scanner.nextLong();

                            LocalDateTime time = LocalDateTime.now().plusDays(1).withHour(10).withMinute(0).withSecond(0);

                            try {
                                Appointment newApp = service.bookAppointment(docId, patId, time);
                                System.out.println("Success! Appointment ID: " + newApp.getId());
                            } catch (Exception e) {
                                System.out.println("Booking failed: " + e.getMessage());
                            }
                            break;

                        case 2:
                            System.out.print("Enter Appointment ID to cancel: ");
                            Long appId = scanner.nextLong();
                            try {
                                service.cancelAppointment(appId);
                                System.out.println("Appointment cancelled.");
                            } catch (Exception e) {
                                System.out.println("Error: " + e.getMessage());
                            }
                            break;

                        case 3:
                            System.out.print("Enter Doctor ID: ");
                            Long dId = scanner.nextLong();
                            List<Appointment> docApps = service.getDoctorSchedule(dId);
                            if (docApps.isEmpty()) {
                                System.out.println("No appointments found.");
                            } else {
                                docApps.forEach(a -> System.out.println("Time: " + a.getTime() + " | Status: " + a.getStatus())); //тут
                            }
                            break;

                        case 4:
                            System.out.print("Enter Patient ID: ");
                            Long pId = scanner.nextLong();
                            List<Appointment> patApps = service.getPatientUpcomingAppointments(pId);
                            patApps.forEach(a -> System.out.println("Dr. " + a.getDoctor().getName() + " at " + a.getTime()));
                            break;

                        case 5:
                            System.out.println("=== Doctors Sorted A–Z ===");
                            List<Doctor> sortedDocs = service.sortDoctorsByName();
                            if (sortedDocs.isEmpty()) {
                                System.out.println("No doctors found.");
                            } else {
                                sortedDocs.forEach(d ->
                                        System.out.println(d.getId() + " - " + d.getName() + " (" + d.getSpecialization() + ")"));
                            }
                            break;

                        case 0:
                            running = false;
                            break;

                        default:
                            System.out.println("Invalid option.");
                    }
                } else {
                    scanner.next();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
