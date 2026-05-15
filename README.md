# 🏥 Mini Project: HospitalSystem

## 📖 Definisi Proyek

**HospitalSystem** adalah aplikasi berbasis console untuk mengelola data rumah sakit secara interaktif. Sistem ini memungkinkan pengguna untuk menambahkan data pasien, data dokter, serta membuat janji temu (appointment) antara pasien dan dokter. Seluruh data disimpan ke dalam database menggunakan **SQLite** melalui **JDBC (Java Database Connectivity)**.

Aplikasi ini dirancang untuk menerapkan konsep-konsep utama dalam **Object-Oriented Programming (OOP)**, yaitu:

- **Inheritance**
- **Java Collection Framework (JCF)**
- **JDBC**
- **ORM dengan Data Mapper Pattern**

Dengan menerapkan konsep-konsep tersebut, kode program menjadi lebih terstruktur, modular, dan mudah dikembangkan.

---

## 🛠️ Implementasi Materi Object-Oriented Programming

Proyek ini dibangun berdasarkan materi yang dipelajari pada mata kuliah Pemrograman Berorientasi Objek (PBO).

### 1. Inheritance
- `Person` merupakan superclass abstrak yang menyimpan atribut umum seperti `id`, `name`, dan `phone`.
- `Patient` dan `Doctor` merupakan subclass yang mewarisi `Person`.
- `Patient` memiliki atribut tambahan `disease`.
- `Doctor` memiliki atribut tambahan `specialization`.

### 2. Java Collection Framework (JCF)
- Menggunakan `ArrayList` untuk menyimpan kumpulan objek seperti daftar pasien.
- Menggunakan `List<Patient>` sebagai representasi koleksi data hasil query database.

### 3. JDBC (Java Database Connectivity)
- `DBConnection` bertugas membuat koneksi ke database SQLite.
- `PreparedStatement` digunakan untuk menjalankan query SQL dengan aman.
- `ResultSet` digunakan untuk membaca hasil query dari database.

### 4. ORM (Object Relational Mapping) dengan Data Mapper
- `PatientRepository`, `DoctorRepository`, dan `AppointmentRepository` bertindak sebagai **Data Mapper**.
- Setiap repository bertugas memetakan data dari tabel database ke objek Java dan sebaliknya.
- Model (`Patient`, `Doctor`, `Appointment`) tidak berisi logika SQL.

### 5. Layered Architecture
- **Model**: Representasi objek bisnis.
- **Repository**: Interaksi dengan database.
- **Service**: Logika bisnis.
- **Util**: Utility seperti koneksi database.
- **Main**: Antarmuka pengguna berbasis console.

---

## 📂 Struktur Proyek

```text
HospitalSystem/
├── bin/
├── database/
│   └── hospital.db
├── libs/
│   └── sqlite-jdbc-3.46.1.0.jar
├── src/
│   ├── model/
│   │   ├── Person.java (abstract)
│   │   ├── Patient.java
│   │   ├── Doctor.java
│   │   └── Appointment.java
│   ├── repository/
│   │   ├── PatientRepository.java
│   │   ├── DoctorRepository.java
│   │   └── AppointmentRepository.java
│   ├── service/
│   │   ├── PatientService.java
│   │   ├── DoctorService.java
│   │   └── AppointmentService.java
│   ├── util/
│   │   └── DBConnection.java
│   └── Main.java
├── README.md
├── FAQ.md
├── Makefile
└── .gitignore
```

## 💻 Simulasi Input & Output
```
=================================
      SISTEM RUMAH SAKIT
=================================

=== MENU UTAMA ===
1. Tambah Pasien
2. Lihat Semua Pasien
0. Keluar
Pilih menu: 1

=== TAMBAH PASIEN ===
Nama Pasien   : Budi Santoso
Nomor Telepon : 08123456789
Penyakit      : Flu

✅ Data pasien berhasil disimpan!

=== MENU UTAMA ===
1. Tambah Pasien
2. Lihat Semua Pasien
0. Keluar
Pilih menu: 2

=== DAFTAR PASIEN ===
1 | Budi Santoso | Flu
```

## ✨ Fitur Utama
- Menambahkan data pasien ke database
- Menampilkan seluruh data pasien
- Menambahkan data dokter
- Membuat janji temu (appointment)
- Penyimpanan data menggunakan SQLite
- Arsitektur terpisah antara model, repository, service, dan main
- Menu interaktif berbasis console
- Implementasi inheritance, JCF, JDBC, dan Data Mapper

## 🗄️ Struktur Database
### Tabel patient
```bash
CREATE TABLE patient (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    name TEXT NOT NULL,
    phone TEXT NOT NULL,
    disease TEXT NOT NULL
);
```

### Tabel doctor
```bash
CREATE TABLE doctor (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    name TEXT NOT NULL,
    phone TEXT NOT NULL,
    specialization TEXT NOT NULL
);
```

### Tabel appointment
```bash
CREATE TABLE appointment (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    patient_id INTEGER NOT NULL,
    doctor_id INTEGER NOT NULL,
    date TEXT NOT NULL,
    FOREIGN KEY (patient_id) REFERENCES patient(id),
    FOREIGN KEY (doctor_id) REFERENCES doctor(id)
);
```

## ▶️ Cara Menjalankan Program
1. Compile Program
```bash
    javac -cp "lib/*" -d out src/model/*.java src/mapper/*.java src/repository/*.java src/service/*.java src/Main.java
```

2. Jalankan Program
```bash
    java -cp "out;lib/*" Main
```

## Referensi Materi
## 📝 Referensi Materi

```text
- **Simaremare, Mario. 13-01-Java Database Connectivity (JDBC). 
  Program Studi Sarjana Sistem Informasi, Institut Teknologi Del.
  Materi membahas konsep dasar JDBC, koneksi database, Statement, 
  PreparedStatement, ResultSet, dan pengelolaan resource JDBC.****

- **Simaremare, Mario. 13-02-Object/Relational Mapping (ORM). 
  Program Studi Sarjana Sistem Informasi, Institut Teknologi Del.
  Materi membahas konsep Object/Relational Mapping, Entity, 
  Data Mapper Pattern, Active Record Pattern, serta implementasi ORM 
  pada aplikasi Java berbasis object-oriented.**

- **Oracle. Java Database Connectivity (JDBC) API Documentation.
  https://docs.oracle.com/javase/8/docs/technotes/guides/jdbc/**

- **Oracle. Java Collections Framework Documentation.
  https://docs.oracle.com/javase/8/docs/technotes/guides/collections/**

- **Fowler, Martin. Patterns of Enterprise Application Architecture.
  Addison-Wesley Professional.**

- **SQLite Documentation.
  https://www.sqlite.org/docs.html**

- **Horstmann, Cay S. Core Java Volume I–Fundamentals.
  Pearson Education.**

- **Weisfeld, Matt. The Object-Oriented Thought Process.
  Addison-Wesley Professional.**