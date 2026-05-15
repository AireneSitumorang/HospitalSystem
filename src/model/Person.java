package model;

import java.time.LocalDate;

public abstract class Person {
    protected int id;
    protected String name;
    protected String phone;
    protected LocalDate birthDate;

    // 🔹 Constructor kosong
    public Person() {}

    // 🔹 Constructor utama
    public Person(String name, String phone, LocalDate birthDate) {
        this.name = name;
        this.phone = phone;
        this.birthDate = birthDate;
    }

    // 🔹 Getter & Setter
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public LocalDate getBirthDate() { return birthDate; }
    public void setBirthDate(LocalDate birthDate) { this.birthDate = birthDate; }

    // 🔹 Method abstract (wajib di-override)
    public abstract String getRole();

    // 🔥 Tambahan penting (biar reusable)
    @Override
    public String toString() {
        return id + " | " + name + " | " + phone;
    }
}