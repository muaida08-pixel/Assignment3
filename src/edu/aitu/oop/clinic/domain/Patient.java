package edu.aitu.oop.clinic.domain;

public class Patient {
    private final Long id;
    private final String name;
    private final String email;

    public Patient(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }

    @Override
    public String toString() {
        return "Patient{id=" + id + ", name='" + name + "', email='" + email + "'}";
    }
}
