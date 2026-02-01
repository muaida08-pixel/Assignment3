package edu.aitu.oop.clinic.repository;

import edu.aitu.oop.clinic.db.DatabaseConnection;
import edu.aitu.oop.clinic.domain.Doctor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcDoctorRepository implements DoctorRepository {

    @Override
    public Doctor findById(Long id) {
        String sql = "SELECT id, name, specialization FROM doctors WHERE id = ?";
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

    @Override
    public List<Doctor> findAll() {
        List<Doctor> doctors = new ArrayList<>();
        String sql = "SELECT id, name, specialization FROM doctors";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                doctors.add(new Doctor(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getString("specialization")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return doctors;
    }

    @Override
    public void save(Doctor doctor) {

    }
}
