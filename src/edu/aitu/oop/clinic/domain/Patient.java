package edu.aitu.oop.clinic.domain;

public class Patient extends User {
    private String email;

    public Patient() {}

    public Patient(Long id, String name, String email) {
        super(id, name);
        this.email = email;
    }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}