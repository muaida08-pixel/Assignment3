package edu.aitu.oop.clinic.repository;

import edu.aitu.oop.clinic.db.DatabaseConnection;
import edu.aitu.oop.clinic.domain.Appointment;
import edu.aitu.oop.clinic.domain.Doctor;
import edu.aitu.oop.clinic.domain.Patient;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class JdbcAppointmentRepository implements AppointmentRepository {

    @Override
    public Long nextId() {
        String sql = "SELECT nextval('appointments_id_seq')";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                return rs.getLong(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0L;
    }

    @Override
    public void save(Appointment appointment) {
        String sql = "INSERT INTO appointments (id, doctor_id, patient_id, appointment_time, status) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, appointment.getId());
            stmt.setLong(2, appointment.getDoctor().getId());
            stmt.setLong(3, appointment.getPatient().getId());
            stmt.setTimestamp(4, Timestamp.valueOf(appointment.getTime()));
            stmt.setString(5, appointment.getStatus());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Appointment appointment) {
        String sql = "UPDATE appointments SET status = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, appointment.getStatus());
            stmt.setLong(2, appointment.getId());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Appointment findById(Long id) {
        String sql = "SELECT a.id, a.appointment_time, a.status, " +
                "d.id as doc_id, d.name as doc_name, d.specialization as doc_spec, " +
                "p.id as pat_id, p.name as pat_name, p.email as pat_email " +
                "FROM appointments a " +
                "JOIN doctors d ON a.doctor_id = d.id " +
                "JOIN patients p ON a.patient_id = p.id " +
                "WHERE a.id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return mapRowToAppointment(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Appointment> findByDoctorId(Long doctorId) {
        List<Appointment> list = new ArrayList<>();
        String sql = "SELECT a.id, a.appointment_time, a.status, " +
                "d.id as doc_id, d.name as doc_name, d.specialization as doc_spec, " +
                "p.id as pat_id, p.name as pat_name, p.email as pat_email " +
                "FROM appointments a " +
                "JOIN doctors d ON a.doctor_id = d.id " +
                "JOIN patients p ON a.patient_id = p.id " +
                "WHERE a.doctor_id = ? ORDER BY a.appointment_time";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, doctorId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                list.add(mapRowToAppointment(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Appointment> findByPatientId(Long patientId) {
        List<Appointment> list = new ArrayList<>();
        String sql = "SELECT a.id, a.appointment_time, a.status, " +
                "d.id as doc_id, d.name as doc_name, d.specialization as doc_spec, " +
                "p.id as pat_id, p.name as pat_name, p.email as pat_email " +
                "FROM appointments a " +
                "JOIN doctors d ON a.doctor_id = d.id " +
                "JOIN patients p ON a.patient_id = p.id " +
                "WHERE a.patient_id = ? ORDER BY a.appointment_time";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, patientId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                list.add(mapRowToAppointment(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public boolean existsByDoctorAndTime(Long doctorId, LocalDateTime time) {
        String sql = "SELECT count(*) FROM appointments WHERE doctor_id = ? AND appointment_time = ? AND status = 'BOOKED'";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, doctorId);
            stmt.setTimestamp(2, Timestamp.valueOf(time));
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private Appointment mapRowToAppointment(ResultSet rs) throws SQLException {
        Doctor doctor = new Doctor(
                rs.getLong("doc_id"),
                rs.getString("doc_name"),
                rs.getString("doc_spec")
        );
        Patient patient = new Patient(
                rs.getLong("pat_id"),
                rs.getString("pat_name"),
                rs.getString("pat_email")
        );
        return new Appointment(
                rs.getLong("id"),
                doctor,
                patient,
                rs.getTimestamp("appointment_time").toLocalDateTime(),
                rs.getString("status")
        );
    }
}
