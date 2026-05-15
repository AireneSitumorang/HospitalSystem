-- 🔹 TABEL PATIENTS
CREATE TABLE IF NOT EXISTS patients (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    name TEXT NOT NULL,
    phone TEXT,
    birth_date TEXT,
    medical_history TEXT
);

-- 🔹 TABEL DOCTORS
CREATE TABLE IF NOT EXISTS doctors (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    name TEXT NOT NULL,
    phone TEXT,
    birth_date TEXT,
    specialization TEXT
);

-- 🔹 TABEL APPOINTMENTS (PAKAI RELASI)
CREATE TABLE IF NOT EXISTS appointments (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    patient_id INTEGER NOT NULL,
    doctor_id INTEGER NOT NULL,
    appointment_time TEXT,
    status TEXT DEFAULT 'Scheduled',
    notes TEXT,

    -- 🔥 RELASI (PENTING BANGET)
    FOREIGN KEY (patient_id) REFERENCES patients(id),
    FOREIGN KEY (doctor_id) REFERENCES doctors(id)
);