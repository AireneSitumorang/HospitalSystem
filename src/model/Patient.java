package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Patient extends Person {
    private String medicalHistory;
    private List<Appointment> appointments = new ArrayList<>();

    // 🔹 Constructor kosong
    public Patient() {}

    // 🔹 Constructor utama
    public Patient(String name, String phone, LocalDate birthDate, String medicalHistory) {
        super(name, phone, birthDate);
        this.medicalHistory = medicalHistory;
    }

    // 🔹 Getter & Setter
    public String getMedicalHistory() { return medicalHistory; }
    public void setMedicalHistory(String medicalHistory) { this.medicalHistory = medicalHistory; }

    public List<Appointment> getAppointments() { return appointments; }

    // 🔹 Tambahan biar bisa relasi
    public void addAppointment(Appointment appointment) {
        this.appointments.add(appointment);
    }

    // 🔹 Override dari Person
    @Override
    public String getRole() {
        return "Patient";
    }

    // 🔥 Biar output rapi
    @Override
    public String toString() {
        return getId() + " | " + getName() + " | " + getMedicalHistory();
    }
}