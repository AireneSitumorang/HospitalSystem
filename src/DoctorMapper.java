package mapper;

import model.Doctor;
import repository.DatabaseConnection;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DoctorMapper {

    // 🔹 INSERT DOCTOR
    public void insert(Doctor doctor) {
        String sql = "INSERT INTO doctors (name, phone, birth_date, specialization) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, doctor.getName());
            stmt.setString(2, doctor.getPhone());

            if (doctor.getBirthDate() != null) {
                stmt.setString(3, doctor.getBirthDate().toString());
            } else {
                stmt.setNull(3, Types.VARCHAR);
            }

            stmt.setString(4, doctor.getSpecialization());

            int rows = stmt.executeUpdate();

            if (rows > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    doctor.setId(rs.getInt(1));
                    System.out.println("✅ Dokter berhasil disimpan (ID: " + doctor.getId() + ")");
                }
            } else {
                System.out.println("❌ Gagal menyimpan dokter!");
            }

        } catch (SQLException e) {
            System.out.println("❌ ERROR SQL (Insert Doctor): " + e.getMessage());
        }
    }

    // 🔹 FIND BY ID
    public Doctor findById(int id) {
        String sql = "SELECT * FROM doctors WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Doctor d = new Doctor();
                d.setId(rs.getInt("id"));
                d.setName(rs.getString("name"));
                d.setPhone(rs.getString("phone"));

                String dateStr = rs.getString("birth_date");
                if (dateStr != null && !dateStr.isEmpty()) {
                    d.setBirthDate(LocalDate.parse(dateStr));
                }

                d.setSpecialization(rs.getString("specialization"));
                return d;
            }

        } catch (SQLException e) {
            System.out.println("❌ ERROR SQL (Find Doctor): " + e.getMessage());
        }

        return null;
    }

    // 🔹 FIND ALL
    public List<Doctor> findAll() {
        List<Doctor> doctors = new ArrayList<>();
        String sql = "SELECT * FROM doctors";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Doctor d = new Doctor();

                d.setId(rs.getInt("id"));
                d.setName(rs.getString("name"));
                d.setPhone(rs.getString("phone"));

                String dateStr = rs.getString("birth_date");
                if (dateStr != null && !dateStr.isEmpty()) {
                    try {
                        d.setBirthDate(LocalDate.parse(dateStr));
                    } catch (Exception ex) {
                        System.out.println("⚠️ Format tanggal salah untuk dokter ID: " + d.getId());
                    }
                }

                d.setSpecialization(rs.getString("specialization"));
                doctors.add(d);
            }

            // 🔥 DEBUG: cek jumlah data
            System.out.println("📊 Jumlah dokter di database: " + doctors.size());

        } catch (SQLException e) {
            System.out.println("❌ ERROR SQL (Find All Doctors): " + e.getMessage());
        }

        return doctors;
    }
}