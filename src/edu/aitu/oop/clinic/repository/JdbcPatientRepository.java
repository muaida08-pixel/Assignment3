package edu.aitu.oop.clinic.repository;

import edu.aitu.oop.clinic.db.DatabaseConnection;
import edu.aitu.oop.clinic.domain.Patient;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcPatientRepository implements PatientRepository {

    @Override
    public Patient findById(Long id) {
        String sql = "SELECT id, name, email FROM patients WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Patient(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getString("email")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Patient> findAll() {
        List<Patient> patients = new ArrayList<>();
        String sql = "SELECT id, name, email FROM patients";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                patients.add(new Patient(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getString("email")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return patients;
    }

    @Override
    public void save(Patient patient) {

    }
}
// ✅ Added: save() method