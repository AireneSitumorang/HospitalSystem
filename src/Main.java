import service.HospitalService;
import repository.DatabaseConnection;

import java.sql.Connection;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // 🔥 INIT DATABASE
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement()) {

            stmt.execute("PRAGMA foreign_keys = ON;");

            stmt.execute("CREATE TABLE IF NOT EXISTS patients (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "name TEXT NOT NULL," +
                    "phone TEXT," +
                    "birth_date TEXT," +
                    "medical_history TEXT)");3

            stmt.execute("CREATE TABLE IF NOT EXISTS doctors (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "name TEXT NOT NULL," +
                    "phone TEXT," +
                    "birth_date TEXT," +
                    "specialization TEXT)");

            stmt.execute("CREATE TABLE IF NOT EXISTS appointments (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "patient_id INTEGER NOT NULL," +
                    "doctor_id INTEGER NOT NULL," +
                    "appointment_time TEXT," +
                    "status TEXT DEFAULT 'Scheduled'," +
                    "notes TEXT," +
                    "FOREIGN KEY (patient_id) REFERENCES patients(id)," +
                    "FOREIGN KEY (doctor_id) REFERENCES doctors(id))");

        } catch (Exception e) {
            System.out.println("❌ Gagal setup DB: " + e.getMessage());
        }

        HospitalService service = new HospitalService();
        Scanner input = new Scanner(System.in);

        int pilihan;

        do {
            System.out.println("\n🏥 SISTEM MANAJEMEN RUMAH SAKIT");
            System.out.println("=================================");
            System.out.println("1. Registrasi Pasien");
            System.out.println("2. Registrasi Dokter");
            System.out.println("3. Buat Janji Temu");
            System.out.println("4. Lihat Data");
            System.out.println("0. Keluar");
            System.out.print("Pilih menu: ");

            pilihan = input.nextInt();
            input.nextLine(); // clear buffer

            switch (pilihan) {

                case 1:
                    System.out.println("\n📌 Registrasi Pasien");
                    System.out.print("Nama: ");
                    String namaP = input.nextLine();

                    System.out.print("No HP: ");
                    String hpP = input.nextLine();

                    System.out.print("Tahun lahir: ");
                    int th = input.nextInt();

                    System.out.print("Bulan: ");
                    int bl = input.nextInt();

                    System.out.print("Tanggal: ");
                    int tg = input.nextInt();
                    input.nextLine();

                    System.out.print("Riwayat penyakit: ");
                    String riwayat = input.nextLine();

                    service.addPatient(namaP, hpP,
                            LocalDate.of(th, bl, tg), riwayat);
                    break;

                case 2:
                    System.out.println("\n📌 Registrasi Dokter");
                    System.out.print("Nama: ");
                    String namaD = input.nextLine();

                    System.out.print("No HP: ");
                    String hpD = input.nextLine();

                    System.out.print("Tahun lahir: ");
                    int thD = input.nextInt();

                    System.out.print("Bulan: ");
                    int blD = input.nextInt();

                    System.out.print("Tanggal: ");
                    int tgD = input.nextInt();
                    input.nextLine();

                    System.out.print("Spesialis: ");
                    String spesialis = input.nextLine();

                    service.addDoctor(namaD, hpD,
                            LocalDate.of(thD, blD, tgD), spesialis);
                    break;

                case 3:
                    System.out.println("\n📌 Buat Janji Temu");
                    System.out.print("ID Pasien: ");
                    int idP = input.nextInt();

                    System.out.print("ID Dokter: ");
                    int idD = input.nextInt();

                    service.scheduleAppointment(idP, idD,
                            LocalDateTime.now().plusDays(1));
                    break;

                case 4:
                    System.out.println("\n📌 DATA RUMAH SAKIT");
                    service.showAllPatients();
                    service.showAllDoctors();
                    break;

                case 0:
                    System.out.println("👋 Keluar dari sistem...");
                    break;

                default:
                    System.out.println("❌ Pilihan tidak valid!");
            }

        } while (pilihan != 0);

        input.close();
    }
}