package edu.aitu.oop.clinic.domain;

import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;

public class Doctor {
    private Long id;
    private String name;
    private String specialization; // Мы договорились использовать это название

    public Doctor() {}

    public Doctor(Long id, String name, String specialization) {
        this.id = id;
        this.name = name;
        this.specialization = specialization;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getSpecialization() { return specialization; } // Вот этот метод, который искала Java!
    public void setSpecialization(String specialization) { this.specialization = specialization; }

    // Заглушки для совместимости с кодом партнера
    public boolean isAvailableAt(LocalDateTime time) {
        return true;
    }
    public List<LocalDateTime> getAvailableSlots() {
        return new ArrayList<>();
    }
}