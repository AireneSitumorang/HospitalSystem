package service;

import mapper.*;
import model.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class HospitalService {

    private PatientMapper patientMapper = new PatientMapper();
    private DoctorMapper doctorMapper = new DoctorMapper();
    private AppointmentMapper appointmentMapper = new AppointmentMapper();

    // 🔹 TAMBAH PASIEN
    public void addPatient(String name, String phone, LocalDate birthDate, String medicalHistory) {
        Patient patient = new Patient(name, phone, birthDate, medicalHistory);
        patientMapper.insert(patient);

        if (patient.getId() > 0) {
            System.out.println("✅ Pasien berhasil ditambahkan! ID: " + patient.getId());
        } else {
            System.out.println("❌ Gagal menambahkan pasien!");
        }
    }

    // 🔹 TAMBAH DOKTER
    public void addDoctor(String name, String phone, LocalDate birthDate, String specialization) {
        Doctor doctor = new Doctor(name, phone, birthDate, specialization);
        doctorMapper.insert(doctor);

        if (doctor.getId() > 0) {
            System.out.println("✅ Dokter berhasil ditambahkan! ID: " + doctor.getId());
        } else {
            System.out.println("❌ Gagal menambahkan dokter!");
        }
    }

    // 🔹 BUAT JANJI TEMU
    public void scheduleAppointment(int patientId, int doctorId, LocalDateTime appointmentTime) {

        // 🔥 VALIDASI (BIAR ADA ALUR JELAS)
        Patient p = patientMapper.findById(patientId);
        Doctor d = doctorMapper.findById(doctorId);

        if (p == null) {
            System.out.println("❌ Pasien tidak ditemukan!");
            return;
        }

        if (d == null) {
            System.out.println("❌ Dokter tidak ditemukan!");
            return;
        }

        Appointment app = new Appointment(patientId, doctorId, appointmentTime, "Scheduled");
        appointmentMapper.insert(app);

        System.out.println("✅ Janji temu berhasil dibuat!");
        System.out.println("👤 Pasien: " + p.getName());
        System.out.println("👨‍⚕️ Dokter: " + d.getName());
        System.out.println("⏰ Waktu: " + appointmentTime);
    }

    // 🔹 TAMPILKAN PASIEN
    public void showAllPatients() {
        List<Patient> patients = patientMapper.findAll();

        System.out.println("\n=== DAFTAR PASIEN ===");

        if (patients.isEmpty()) {
            System.out.println("⚠️ Tidak ada data pasien!");
        }

        for (Patient p : patients) {
            System.out.println(p); // 🔥 pakai toString()
        }
    }

    // 🔹 TAMPILKAN DOKTER
    public void showAllDoctors() {
        List<Doctor> doctors = doctorMapper.findAll();

        System.out.println("\n=== DAFTAR DOKTER ===");

        if (doctors.isEmpty()) {
            System.out.println("⚠️ Tidak ada data dokter!");
        }

        for (Doctor d : doctors) {
            System.out.println(d); // 🔥 pakai toString()
        }
    }
}