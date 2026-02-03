package edu.aitu.oop.clinic.domain;

public class UserFactory {

    public static User createUser(String type, Long id, String name, String extraInfo) {
        if (type.equalsIgnoreCase("doctor")) {
            return new Doctor(id, name, extraInfo);

        } else if (type.equalsIgnoreCase("patient")) {
            return new Patient(id, name, extraInfo);

        } else {
            throw new IllegalArgumentException("Unknown user type: " + type);
        }
    }
}
