package edu.aitu.oop.clinic.domain;

import java.time.LocalDateTime;

public class Appointment {
    private final int id;
    private final Doctor doctor;
    private final Patient patient;
    private final LocalDateTime time;
    private final String status; // BOOKED or CANCELLED

    public Appointment(int id, Doctor doctor, Patient patient, LocalDateTime time, String status) {
        this.id = id;
        this.doctor = doctor;
        this.patient = patient;
        this.time = time;
        this.status = status;
    }

    public int getId() { return id; }
    public Doctor getDoctor() { return doctor; }
    public Patient getPatient() { return patient; }
    public LocalDateTime getTime() { return time; }
    public String getStatus() { return status; }

    @Override
    public String toString() {
        return "Appointment{id=" + id +
                ", doctor=" + doctor.getName() +
                ", patient=" + patient.getName() +
                ", time=" + time +
                ", status=" + status + '}';
    }
}
