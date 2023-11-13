package com.healthcaresystem.service;

import com.healthcaresystem.model.*;

import java.util.ArrayList;

public interface IClinic {
    public User login(String username, String password);
    public User mfa(String username, int code);

    public LabTest getLabTestById(String userType, int patientId, int testId);
    public ArrayList<LabTest> getLabTestsForPatient(String userType, int patientId, String testType, String date);

    public VisitRecord getVisitRecordById(String userType, int patientId, int visitId);
    public ArrayList<VisitRecord> getVisitRecordsForPatient(String userType, int patientId);

    public ArrayList<Physician> getPhysiciansForPatient(String userType, int patientId);
    public ArrayList<Patient> getPatientsForPhysician(String userType, int physicianId);
}
