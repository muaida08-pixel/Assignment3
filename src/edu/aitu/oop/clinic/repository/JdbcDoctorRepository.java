package edu.aitu.oop.clinic.repository;

import edu.aitu.oop.clinic.db.DatabaseConnection;
import edu.aitu.oop.clinic.domain.Doctor;

import java.sql.*;

public class JdbcDoctorRepository implements DoctorRepository {

    @Override
    public void save(Doctor doctor) {
        String sql = "INSERT INTO doctors (name, specialization) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, doctor.getName());
            stmt.setString(2, doctor.getSpecialization());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Doctor findById(Long id) {
        String sql = "SELECT * FROM doctors WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Doctor(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getString("specialization")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}