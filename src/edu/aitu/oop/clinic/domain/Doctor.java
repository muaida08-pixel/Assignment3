package edu.aitu.oop.clinic.domain;

import java.time.LocalDateTime;
import java.util.List;

public class Doctor {
    private final Long id;
    private final String name;
    private final String specialization;

    public Doctor(Long id, String name, String specialization) {
        this.id = id;
        this.name = name;
        this.specialization = specialization;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getSpecialization() { return specialization; }

    @Override
    public String toString() {
        return "Doctor{id=" + id + ", name='" + name + "', specialization='" + specialization + "'}";
    }

    public boolean isAvailableAt(LocalDateTime time) {
        return false;
    }

    public List<LocalDateTime> getAvailableSlots() {
        return List.of();
    }
}
