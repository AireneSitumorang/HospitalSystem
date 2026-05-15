package mapper;

import model.Patient;
import repository.DatabaseConnection;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PatientMapper {

    // 🔹 INSERT PATIENT
    public void insert(Patient patient) {
        String sql = "INSERT INTO patients (name, phone, birth_date, medical_history) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, patient.getName());
            stmt.setString(2, patient.getPhone());

            if (patient.getBirthDate() != null) {
                stmt.setString(3, patient.getBirthDate().toString());
            } else {
                stmt.setNull(3, Types.VARCHAR);
            }

            stmt.setString(4, patient.getMedicalHistory());

            int rows = stmt.executeUpdate();

            if (rows > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    patient.setId(rs.getInt(1));
                    System.out.println("✅ Pasien berhasil disimpan (ID: " + patient.getId() + ")");
                }
            } else {
                System.out.println("❌ Gagal menyimpan pasien!");
            }

        } catch (SQLException e) {
            System.out.println("❌ ERROR SQL (Insert Patient): " + e.getMessage());
        }
    }

    // 🔹 FIND BY ID
    public Patient findById(int id) {
        String sql = "SELECT * FROM patients WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Patient p = new Patient();

                p.setId(rs.getInt("id"));
                p.setName(rs.getString("name"));
                p.setPhone(rs.getString("phone"));

                String dateStr = rs.getString("birth_date");
                if (dateStr != null && !dateStr.isEmpty()) {
                    try {
                        p.setBirthDate(LocalDate.parse(dateStr));
                    } catch (Exception ex) {
                        System.out.println("⚠️ Format tanggal salah untuk pasien ID: " + p.getId());
                    }
                }

                p.setMedicalHistory(rs.getString("medical_history"));

                return p;
            }

        } catch (SQLException e) {
            System.out.println("❌ ERROR SQL (Find Patient): " + e.getMessage());
        }

        return null;
    }

    // 🔹 FIND ALL
    public List<Patient> findAll() {
        List<Patient> patients = new ArrayList<>();
        String sql = "SELECT * FROM patients";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Patient p = new Patient();

                p.setId(rs.getInt("id"));
                p.setName(rs.getString("name"));
                p.setPhone(rs.getString("phone"));

                String dateStr = rs.getString("birth_date");
                if (dateStr != null && !dateStr.isEmpty()) {
                    try {
                        p.setBirthDate(LocalDate.parse(dateStr));
                    } catch (Exception ex) {
                        System.out.println("⚠️ Format tanggal salah untuk pasien ID: " + p.getId());
                    }
                }

                p.setMedicalHistory(rs.getString("medical_history"));

                patients.add(p);
            }

            // 🔥 DEBUG
            System.out.println("📊 Jumlah pasien: " + patients.size());

        } catch (SQLException e) {
            System.out.println("❌ ERROR SQL (Find All Patient): " + e.getMessage());
        }

        return patients;
    }
}