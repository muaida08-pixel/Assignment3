package edu.aitu.oop.clinic.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Doctor extends User {
    private String specialization;

    public Doctor() {}

    public Doctor(Long id, String name, String specialization) {
        super(id, name);
        this.specialization = specialization;
    }

    public String getSpecialization() { return specialization; }
    public void setSpecialization(String specialization) { this.specialization = specialization; }

    public boolean isAvailableAt(LocalDateTime time) { return true; }
    public List<LocalDateTime> getAvailableSlots() { return new ArrayList<>(); }
}