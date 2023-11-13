package com.healthcaresystem.service;

import com.healthcaresystem.model.*;

import java.util.ArrayList;

public interface IClinic {
    public User login(String username, String password);
    //public int MFA(int code);
    //public void Logout();

    public LabTest getLabTestById(int patientId, int testId);
    public ArrayList<LabTest> getLabTestsForPatient(int patientId, String testType, String date);

    public VisitRecord getVisitRecordById(int patientId, int visitId);
    public ArrayList<VisitRecord> getVisitRecordsForPatient(int patientId);

    public ArrayList<Physician> getPhysiciansForPatient(int patientId);
    public ArrayList<Patient> getPatientsForPhysician(int physicianId);
}
