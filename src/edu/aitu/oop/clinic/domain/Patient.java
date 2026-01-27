package edu.aitu.oop.clinic.domain;

public class Patient {
    private final int id;
    private final String name;

    public Patient(int id, String name, String s) {
        this.id = id;
        this.name = name;
    }

    public int getId() { return id; }
    public String getName() { return name; }

    @Override
    public String toString() {
        return "Patient{id=" + id + ", name='" + name + "'}";
    }
}
