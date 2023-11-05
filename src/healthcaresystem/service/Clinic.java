package healthcaresystem.service;

import healthcaresystem.dao.DatabaseHandler;
import healthcaresystem.model.*;
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
        User user = dbHandler.login(username, password);
        return user;
    }

    public LabTest getLabTestByID(int patientID, int testID) {
        LabTest labTest = dbHandler.getLabTestByID(patientID, testID);
        return labTest;
    }

    public ArrayList<LabTest> getLabTestsForPatient(int patientID, String testType, String date){
        ArrayList<LabTest> labTests = dbHandler.getLabTestsForPatient(patientID, testType, date);
        return labTests;
    }

    public VisitRecord getVisitRecordByID(int patientID, int visitID) {
        VisitRecord visitRecord = dbHandler.getVisitRecordByID(patientID, visitID);
        return visitRecord;

    }
    public ArrayList<VisitRecord> getVisitRecordsForPatient(int patientID){
        ArrayList<VisitRecord> visitRecords = dbHandler.getVisitRecordsForPatient(patientID);
        return visitRecords;

    }

    public ArrayList<Physician> getPhysiciansForPatient(int patientID){
        ArrayList<Physician> physicians = dbHandler.getPhysiciansForPatient(patientID);
        return physicians;
    }

    public ArrayList<Patient> getPatientsForPhysician(int physicianID){
        ArrayList<Patient> patients = dbHandler.getPatientsForPhysician(physicianID);
        return patients;
    }

}
