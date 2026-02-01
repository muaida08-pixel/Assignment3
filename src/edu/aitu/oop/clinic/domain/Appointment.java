package edu.aitu.oop.clinic.domain;

import java.time.LocalDate;
import java.time.LocalTime;

public class Appointment {
    private final Long id;
    private final Doctor doctor;
    private final Patient patient;
    private final LocalDate date;
    private final LocalTime time;
    private final String status;

    private Appointment(Builder builder) {
        this.id = builder.id;
        this.doctor = builder.doctor;
        this.patient = builder.patient;
        this.date = builder.date;
        this.time = builder.time;
        this.status = builder.status;
    }

    public Long getId() { return id; }
    public Doctor getDoctor() { return doctor; }
    public Patient getPatient() { return patient; }
    public LocalDate getDate() { return date; }
    public LocalTime getTime() { return time; }
    public String getStatus() { return status; }

    public static class Builder {
        private Long id;
        private Doctor doctor;
        private Patient patient;
        private LocalDate date;
        private LocalTime time;
        private String status;

        public Builder id(Long id) { this.id = id; return this; }
        public Builder doctor(Doctor doctor) { this.doctor = doctor; return this; }
        public Builder patient(Patient patient) { this.patient = patient; return this; }
        public Builder date(LocalDate date) { this.date = date; return this; }
        public Builder time(LocalTime time) { this.time = time; return this; }
        public Builder status(String status) { this.status = status; return this; }

        public Appointment build() {
            if (doctor == null || patient == null || date == null || time == null || status == null) {
                throw new IllegalStateException("Appointment fields must not be null");
            }
            return new Appointment(this);
        }
    }
}
