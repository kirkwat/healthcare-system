package healthcaresystem.service;

import healthcaresystem.model.*;
import java.util.ArrayList;

public class TestClinic {
    public static void main(String[] args) {

        Clinic system = new Clinic();

        System.out.println("Getting lab tests for patient #10032");
        ArrayList<LabTest> results = system.getLabTestsForPatient(10032, null, null);
        results.forEach(i -> System.out.println("   " + i.getDate() + " " + i.getTestType() + " " + i.getResult()));

        System.out.println("\nGetting Calcium lab tests for patient #10032");
        results = system.getLabTestsForPatient(10032, "Calcium", null);
        results.forEach(i -> System.out.println("   " + i.getDate() + " " + i.getTestType() + " " + i.getResult()));

        System.out.println("\nGetting lab tests for patient #10032 from 2023-12-01");
        results = system.getLabTestsForPatient(10032, null, "2023-12-01");
        results.forEach(i -> System.out.println("   " + i.getDate() + " " + i.getTestType() + " " + i.getResult()));

        System.out.println("\nGetting lab test #5 for patient #10032");
        LabTest result = system.getLabTestByID(10032, 5);
        System.out.println("   " + result.getDate() + " " + result.getTestType() + " " + result.getResult());

        System.out.println("\nGetting visit records for patient #10032");
        ArrayList<VisitRecord> visitResults = system.getVisitRecordsForPatient(10032);
        visitResults.forEach(i -> System.out.println("   " + i.getVisitID() + " " + i.getPatientName() + " " + i.getNotes()));

        System.out.println("\nGetting visit record #2 for patient #10032");
        VisitRecord visitResult = system.getVisitRecordByID(10032, 2);
        System.out.println("   " + visitResult.getVisitID() + " " + visitResult.getPatientName() + " " + visitResult.getNotes());

        System.out.println("\nGetting physicians for patient #10032");
        ArrayList<Physician> physiciansResult = system.getPhysiciansForPatient(10032);
        physiciansResult.forEach(i -> System.out.println("   " + i.getName() + " " + i.getPatientIDs().size()));

        System.out.println("\nGetting patients for physician #1");
        ArrayList<Patient> patientsResult = system.getPatientsForPhysician(1);
        patientsResult.forEach(i -> System.out.println("   " + i.getName() + " " + i.getPatientID()));

        System.out.println("\nLogging in as Dr. Hossain");
        User user = system.login("mhossain", "drpassword123");
        if(user != null) {
            System.out.println("   " + user.getName() + " " + user.getType());
        }
        else {
            System.out.println("   Login failed");
        }
    }
}
