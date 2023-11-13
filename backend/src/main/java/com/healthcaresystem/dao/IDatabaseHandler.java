package com.healthcaresystem.dao;

import com.healthcaresystem.model.*;

import java.util.ArrayList;

public interface IDatabaseHandler {

    public void closeConnection();

    public User login(String username, String password);
    public LabTest getLabTestById(int patientId, int testId);
    public ArrayList<LabTest> getLabTestsForPatient(int patientId, String testType, String date);
    public VisitRecord getVisitRecordById(int patientId, int visitId);
    public ArrayList<VisitRecord> getVisitRecordsForPatient(int patientId);
    public ArrayList<Physician> getPhysiciansForPatient(int patientId);
    public ArrayList<Patient> getPatientsForPhysician(int physicianId);
}
