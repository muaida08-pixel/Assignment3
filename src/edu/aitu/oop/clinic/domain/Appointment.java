package edu.aitu.oop.clinic.domain;

import java.time.LocalDateTime;

public class Appointment {
    private Long id;
    private Doctor doctor;
    private Patient patient;
    private LocalDateTime time;
    private String status; // "BOOKED", "CANCELLED"

    public Appointment() {}

    public Appointment(Long id, Doctor doctor, Patient patient, LocalDateTime time, String status) {
        this.id = id;
        this.doctor = doctor;
        this.patient = patient;
        this.time = time;
        this.status = status;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Doctor getDoctor() { return doctor; }
    public void setDoctor(Doctor doctor) { this.doctor = doctor; }

    public Patient getPatient() { return patient; }
    public void setPatient(Patient patient) { this.patient = patient; }

    public LocalDateTime getTime() { return time; }
    public void setTime(LocalDateTime time) { this.time = time; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
