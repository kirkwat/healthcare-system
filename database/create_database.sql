CREATE DATABASE HealthCareSystem;

USE HealthCareSystem;

CREATE TABLE Patient (
    patient_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE User (
    uid INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    name VARCHAR(255) NOT NULL,
    type ENUM('physician', 'nurse', 'receptionist') NOT NULL
);

CREATE TABLE PhysicianNurse (
    id INT PRIMARY KEY AUTO_INCREMENT,
    physician_id INT,
    nurse_id INT,
    FOREIGN KEY (physician_id) REFERENCES User(uid),
    FOREIGN KEY (nurse_id) REFERENCES User(uid)
);

CREATE TABLE PhysicianPatient (
    id INT PRIMARY KEY AUTO_INCREMENT,
    patient_id INT,
    physician_id INT,
    FOREIGN KEY (patient_id) REFERENCES Patient(patient_id),
    FOREIGN KEY (physician_id) REFERENCES User(uid)
);

CREATE TABLE LabTest (
    test_id INT PRIMARY KEY AUTO_INCREMENT,
    patient_id INT,
    test_type VARCHAR(255) NOT NULL,
    date DATE NOT NULL,
    result VARCHAR(255) NOT NULL,
    FOREIGN KEY (patient_id) REFERENCES Patient(patient_id)
);

CREATE TABLE VisitRecord (
    visit_id INT PRIMARY KEY AUTO_INCREMENT,
    patient_id INT,
    physician_id INT,
    date DATE NOT NULL,
    time VARCHAR(255) NOT NULL,
    location VARCHAR(255) NOT NULL,
    notes TEXT,
    FOREIGN KEY (physician_id) REFERENCES User(uid),
    FOREIGN KEY (patient_id) REFERENCES Patient(patient_id)
);