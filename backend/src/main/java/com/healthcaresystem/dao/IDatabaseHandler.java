package com.healthcaresystem.dao;

import com.healthcaresystem.model.*;

import java.util.ArrayList;

public interface IDatabaseHandler {

    public void closeConnection();

    public User login(String username, String password);
    public LabTest getLabTestByID(int patientID, int testID);
    public ArrayList<LabTest> getLabTestsForPatient(int patientID, String testType, String date);
    public VisitRecord getVisitRecordByID(int patientID, int visitID);
    public ArrayList<VisitRecord> getVisitRecordsForPatient(int patientID);
    public ArrayList<Physician> getPhysiciansForPatient(int patientID);
    public ArrayList<Patient> getPatientsForPhysician(int physicianID);
}
