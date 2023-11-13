package com.healthcaresystem.service;

import com.healthcaresystem.dao.DatabaseHandler;
import com.healthcaresystem.model.*;

import java.util.ArrayList;

/**
 * @author Kirk
 *
 * TO ADD: login, logout, track current user, restrict functions by current user type
 * create exception for invalid user type
 */
public class Clinic implements IClinic {
    DatabaseHandler dbHandler = new DatabaseHandler();

    public User login(String username, String password) {
        return dbHandler.login(username, password);
    }

    public LabTest getLabTestByID(int patientID, int testID) {
        return dbHandler.getLabTestByID(patientID, testID);
    }

    public ArrayList<LabTest> getLabTestsForPatient(int patientID, String testType, String date){
        return dbHandler.getLabTestsForPatient(patientID, testType, date);
    }

    public VisitRecord getVisitRecordByID(int patientID, int visitID) {
        return dbHandler.getVisitRecordByID(patientID, visitID);

    }
    public ArrayList<VisitRecord> getVisitRecordsForPatient(int patientID){
        return dbHandler.getVisitRecordsForPatient(patientID);

    }

    public ArrayList<Physician> getPhysiciansForPatient(int patientID){
        return dbHandler.getPhysiciansForPatient(patientID);
    }

    public ArrayList<Patient> getPatientsForPhysician(int physicianID){
        return dbHandler.getPatientsForPhysician(physicianID);
    }

}
