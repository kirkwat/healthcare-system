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

    public LabTest getLabTestById(int patientId, int testId) {
        return dbHandler.getLabTestById(patientId, testId);
    }

    public ArrayList<LabTest> getLabTestsForPatient(int patientId, String testType, String date){
        return dbHandler.getLabTestsForPatient(patientId, testType, date);
    }

    public VisitRecord getVisitRecordById(int patientId, int visitId) {
        return dbHandler.getVisitRecordById(patientId, visitId);

    }
    public ArrayList<VisitRecord> getVisitRecordsForPatient(int patientId){
        return dbHandler.getVisitRecordsForPatient(patientId);

    }

    public ArrayList<Physician> getPhysiciansForPatient(int patientId){
        return dbHandler.getPhysiciansForPatient(patientId);
    }

    public ArrayList<Patient> getPatientsForPhysician(int physicianId){
        return dbHandler.getPatientsForPhysician(physicianId);
    }
}
