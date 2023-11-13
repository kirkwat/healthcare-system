package com.healthcaresystem.service;

import com.healthcaresystem.dao.DatabaseHandler;
import com.healthcaresystem.model.*;

import java.util.ArrayList;

public class Clinic implements IClinic {
    private DatabaseHandler dbHandler = new DatabaseHandler();

    public User login(String username, String password) {
        return dbHandler.login(username, password);
    }

    public User mfa(String username, int code){
        return dbHandler.mfa(username, code);
    }

    public LabTest getLabTestById(String userType, int patientId, int testId) {
        if (!isPhysicianOrNurse(userType)) {
            throw new SecurityException("Unauthorized access.");
        }
        return dbHandler.getLabTestById(patientId, testId);
    }

    public ArrayList<LabTest> getLabTestsForPatient(String userType, int patientId, String testType, String date){
        if (!isPhysicianOrNurse(userType)) {
            throw new SecurityException("Unauthorized access.");
        }
        return dbHandler.getLabTestsForPatient(patientId, testType, date);
    }

    public VisitRecord getVisitRecordById(String userType, int patientId, int visitId) {
        if (!isPhysicianOrNurse(userType)) {
            throw new SecurityException("Unauthorized access.");
        }
        return dbHandler.getVisitRecordById(patientId, visitId);
    }

    public ArrayList<VisitRecord> getVisitRecordsForPatient(String userType, int patientId){
        if (!isPhysicianOrNurse(userType)) {
            throw new SecurityException("Unauthorized access.");
        }
        return dbHandler.getVisitRecordsForPatient(patientId);
    }

    public ArrayList<Physician> getPhysiciansForPatient(String userType, int patientId){
        if (!isReceptionist(userType)) {
            throw new SecurityException("Unauthorized access.");
        }
        return dbHandler.getPhysiciansForPatient(patientId);
    }

    public ArrayList<Patient> getPatientsForPhysician(String userType, int physicianId){
        if (!isReceptionist(userType)) {
            throw new SecurityException("Unauthorized access.");
        }
        return dbHandler.getPatientsForPhysician(physicianId);
    }

    private boolean isPhysicianOrNurse(String userType) {
        return "physician".equals(userType) || "nurse".equals(userType);
    }

    private boolean isReceptionist(String userType) {
        return "receptionist".equals(userType);
    }
}
