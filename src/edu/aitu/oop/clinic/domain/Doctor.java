package edu.aitu.oop.clinic.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Doctor {
    private final int id;
    private final String name;
    private final List<LocalDateTime> availableSlots;

    public Doctor(int id, String name, String cardiology, List<LocalDateTime> availableSlots) {
        this.id = id;
        this.name = name;
        this.availableSlots = new ArrayList<>(availableSlots);
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public List<LocalDateTime> getAvailableSlots() { return new ArrayList<>(availableSlots); }

    public boolean isAvailableAt(LocalDateTime time) {
        return availableSlots.contains(time);
    }

    @Override
    public String toString() {
        return "Doctor{id=" + id + ", name='" + name + "'}";
    }
}
