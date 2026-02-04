package edu.aitu.oop.clinic.repository;

import edu.aitu.oop.clinic.config.PostgresDB;
import edu.aitu.oop.clinic.domain.Patient;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcPatientRepository implements PatientRepository {

    @Override
    public void save(Patient patient) {
        String sql = "INSERT INTO patients (name, email) VALUES (?, ?)";
        try (Connection conn = PostgresDB.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, patient.getName());
            stmt.setString(2, patient.getEmail());
            stmt.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    @Override
    public Patient findById(Long id) {
        String sql = "SELECT * FROM patients WHERE id = ?";
        try (Connection conn = PostgresDB.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Patient(rs.getLong("id"), rs.getString("name"), rs.getString("email"));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    @Override
    public List<Patient> findAll() {
        List<Patient> patients = new ArrayList<>();
        String sql = "SELECT * FROM patients";
        try (Connection conn = PostgresDB.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                patients.add(new Patient(rs.getLong("id"), rs.getString("name"), rs.getString("email")));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return patients;
    }
}