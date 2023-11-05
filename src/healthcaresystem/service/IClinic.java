package healthcaresystem.service;

import healthcaresystem.model.*;
import java.util.ArrayList;

public interface IClinic {
    public User login(String username, String password);
    //public int MFA(int code);
    //public void Logout();

    public LabTest getLabTestByID(int patientID, int testID);
    public ArrayList<LabTest> getLabTestsForPatient(int patientID, String testType, String date);

    public VisitRecord getVisitRecordByID(int patientID, int visitID);
    public ArrayList<VisitRecord> getVisitRecordsForPatient(int patientID);

    public ArrayList<Physician> getPhysiciansForPatient(int patientID);
    public ArrayList<Patient> getPatientsForPhysician(int physicianID);
}
