package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Doctor extends Person {
    private String specialization;
    private List<Appointment> appointments = new ArrayList<>();

    // 🔹 Constructor kosong
    public Doctor() {}

    // 🔹 Constructor utama
    public Doctor(String name, String phone, LocalDate birthDate, String specialization) {
        super(name, phone, birthDate);
        this.specialization = specialization;
    }

    // 🔹 Getter & Setter
    public String getSpecialization() { return specialization; }
    public void setSpecialization(String specialization) { this.specialization = specialization; }

    public List<Appointment> getAppointments() { return appointments; }

    public void addAppointment(Appointment appointment) {
        this.appointments.add(appointment);
    }

    // 🔹 Override method dari Person
    @Override
    public String getRole() {
        return "Doctor";
    }

    // 🔥 Tambahan biar gampang ditampilkan
    @Override
    public String toString() {
        return getId() + " | " + getName() + " | " + specialization;
    }
}