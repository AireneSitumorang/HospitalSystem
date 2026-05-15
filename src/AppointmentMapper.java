package mapper;

import model.Appointment;
import repository.DatabaseConnection;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AppointmentMapper {

    // 🔹 INSERT APPOINTMENT
    public void insert(Appointment appointment) {
        String sql = "INSERT INTO appointments (patient_id, doctor_id, appointment_time, status, notes) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, appointment.getPatientId());
            stmt.setInt(2, appointment.getDoctorId());

            if (appointment.getAppointmentTime() != null) {
                stmt.setString(3, appointment.getAppointmentTime().toString());
            } else {
                stmt.setNull(3, Types.VARCHAR);
            }

            stmt.setString(4, appointment.getStatus());

            if (appointment.getNotes() != null) {
                stmt.setString(5, appointment.getNotes());
            } else {
                stmt.setNull(5, Types.VARCHAR);
            }

            int rows = stmt.executeUpdate();

            if (rows > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    appointment.setId(rs.getInt(1));
                    System.out.println("✅ Appointment berhasil disimpan (ID: " + appointment.getId() + ")");
                }
            } else {
                System.out.println("❌ Gagal menyimpan appointment!");
            }

        } catch (SQLException e) {
            System.out.println("❌ ERROR SQL (Insert Appointment): " + e.getMessage());
        }
    }

    // 🔹 FIND ALL APPOINTMENTS
    public List<Appointment> findAll() {
        List<Appointment> appointments = new ArrayList<>();
        String sql = "SELECT * FROM appointments";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Appointment a = new Appointment();

                a.setId(rs.getInt("id"));
                a.setPatientId(rs.getInt("patient_id"));
                a.setDoctorId(rs.getInt("doctor_id"));

                String timeStr = rs.getString("appointment_time");
                if (timeStr != null && !timeStr.isEmpty()) {
                    try {
                        a.setAppointmentTime(LocalDateTime.parse(timeStr));
                    } catch (Exception ex) {
                        System.out.println("⚠️ Format waktu salah untuk appointment ID: " + a.getId());
                    }
                }

                a.setStatus(rs.getString("status"));
                a.setNotes(rs.getString("notes"));

                appointments.add(a);
            }

            // 🔥 DEBUG
            System.out.println("📊 Jumlah appointment: " + appointments.size());

        } catch (SQLException e) {
            System.out.println("❌ ERROR SQL (Find All Appointment): " + e.getMessage());
        }

        return appointments;
    }
}