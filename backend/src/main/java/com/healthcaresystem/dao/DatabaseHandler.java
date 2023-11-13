package com.healthcaresystem.dao;

import com.healthcaresystem.model.*;

import java.sql.*;
import java.util.ArrayList;

public class DatabaseHandler implements IDatabaseHandler {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/HealthCareSystem";
    private static final String USER = "root";
    private static final String PASS = "password";
    private Connection conn = null;

    public DatabaseHandler() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC Driver not found");
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void closeConnection() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public User login(String username, String password) {
        User user = null;

        StringBuilder query = new StringBuilder("SELECT * FROM User WHERE username = ? AND password = ?");

        try {
            PreparedStatement pstmt = conn.prepareStatement(query.toString());
            pstmt.setString(1,  username);
            pstmt.setString(2, password);


            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int rsUid = rs.getInt("uid");
                String rsName = rs.getString("name");
                String rsType = rs.getString("type");
                user = new User(rsUid, rsName, rsType);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    public LabTest getLabTestByID(int patientID, int testID){
        LabTest labTest = null;

        StringBuilder query = new StringBuilder("SELECT * FROM LabTest WHERE patient_id = ? AND test_id = ? LIMIT 1");

        try {
            PreparedStatement pstmt = conn.prepareStatement(query.toString());
            pstmt.setInt(1,  patientID);
            pstmt.setInt(2, testID);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int rsTestID = rs.getInt("test_id");
                int rsPatientID = rs.getInt("patient_id");
                String rsTestType = rs.getString("test_type");
                String rsDate = rs.getString("date");
                String rsResult = rs.getString("result");
                labTest = new LabTest(rsTestID, rsPatientID, rsTestType, rsDate, rsResult);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return labTest;
    }

    public ArrayList<LabTest> getLabTestsForPatient(int patientID, String testType, String date){
        ArrayList<LabTest> labTests = new ArrayList<>();

        StringBuilder query = new StringBuilder("SELECT * FROM LabTest WHERE patient_id = ?");

        if(testType != null) {
            query.append(" AND test_type = ?");
        }
        if(date != null){
            query.append(" AND date = ?");
        }

        try {
            PreparedStatement pstmt = conn.prepareStatement(query.toString());
            pstmt.setInt(1,  patientID);

            int index = 2;
            if(testType != null) {
                pstmt.setString(index++, testType);
            }
            if(date != null) {
                pstmt.setString(index, date);
            }

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int rsTestID = rs.getInt("test_id");
                int rsPatientID = rs.getInt("patient_id");
                String rsTestType = rs.getString("test_type");
                String rsDate = rs.getString("date");
                String rsResult = rs.getString("result");
                labTests.add(new LabTest(rsTestID, rsPatientID, rsTestType, rsDate, rsResult));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return labTests;
    }

    public VisitRecord getVisitRecordByID(int patientID, int visitID){
        VisitRecord visitRecord = null;

        StringBuilder query = new StringBuilder("SELECT v.*, p.name AS patient_name, u.name AS physician_name " +
                "FROM VisitRecord v " +
                "INNER JOIN Patient p On v.patient_id = p.patient_id " +
                "INNER JOIN User u ON v.physician_id = u.uid " +
                "WHERE v.patient_id = ? AND v.visit_id = ?" +
                "LIMIT 1");
        try {
            PreparedStatement pstmt = conn.prepareStatement(query.toString());
            pstmt.setInt(1,  patientID);
            pstmt.setInt(2, visitID);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int rsVisitID = rs.getInt("visit_id");
                int rsPatientID = rs.getInt("patient_id");
                String rsPatientName = rs.getString("patient_name");
                int rsPhysicianID = rs.getInt("physician_id");
                String rsPhysicianName = rs.getString("physician_name");
                String rsDate = rs.getString("date");
                String rsTime = rs.getString("time");
                String rsLocation = rs.getString("location");
                String rsNotes = rs.getString("notes");
                visitRecord = new VisitRecord(rsVisitID, rsPatientID, rsPatientName, rsPhysicianID, rsPhysicianName, rsDate, rsTime, rsLocation, rsNotes);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return visitRecord;
    }

    public ArrayList<VisitRecord> getVisitRecordsForPatient(int patientID){
        ArrayList<VisitRecord> visitRecords = new ArrayList<>();

        StringBuilder query = new StringBuilder("SELECT v.*, p.name AS patient_name, u.name AS physician_name " +
                "FROM VisitRecord v " +
                "INNER JOIN Patient p On v.patient_id = p.patient_id " +
                "INNER JOIN User u ON v.physician_id = u.uid " +
                "WHERE v.patient_id = ?");

        try {
            PreparedStatement pstmt = conn.prepareStatement(query.toString());
            pstmt.setInt(1,  patientID);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int rsVisitID = rs.getInt("visit_id");
                int rsPatientID = rs.getInt("patient_id");
                String rsPatientName = rs.getString("patient_name");
                int rsPhysicianID = rs.getInt("physician_id");
                String rsPhysicianName = rs.getString("physician_name");
                String rsDate = rs.getString("date");
                String rsTime = rs.getString("time");
                String rsLocation = rs.getString("location");
                String rsNotes = rs.getString("notes");
                visitRecords.add(new VisitRecord(rsVisitID, rsPatientID, rsPatientName, rsPhysicianID, rsPhysicianName, rsDate, rsTime, rsLocation, rsNotes));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return visitRecords;
    }

    public ArrayList<Physician> getPhysiciansForPatient(int patientID){
        ArrayList<Physician> physicians = new ArrayList<>();

        StringBuilder query = new StringBuilder("SELECT u.uid, u.name, u.type " +
                "FROM User u " +
                "INNER JOIN PhysicianPatient pp ON u.uid = pp.physician_id " +
                "WHERE pp.patient_id = ? AND u.type = 'physician'");

        try {
            PreparedStatement pstmt = conn.prepareStatement(query.toString());
            pstmt.setInt(1,  patientID);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int rsUid = rs.getInt("uid");
                String rsName = rs.getString("name");
                String rsType = rs.getString("type");

                StringBuilder query2 = new StringBuilder("SELECT patient_id FROM PhysicianPatient WHERE physician_id = ?");

                PreparedStatement pstmt2 = conn.prepareStatement(query2.toString());
                pstmt2.setInt(1,  rsUid);

                ResultSet rs2 = pstmt2.executeQuery();

                ArrayList<Integer> patientIDs = new ArrayList<>();
                while (rs2.next()) {
                    patientIDs.add(rs2.getInt("patient_id"));
                }

                physicians.add(new Physician(rsUid, rsName, rsType, patientIDs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return physicians;
    }

    public ArrayList<Patient> getPatientsForPhysician(int physicianID){
        ArrayList<Patient> patients = new ArrayList<>();

        StringBuilder query = new StringBuilder("SELECT p.* " +
                "FROM Patient p " +
                "INNER JOIN PhysicianPatient pp ON p.patient_id = pp.patient_id " +
                "WHERE pp.physician_id = ?");

        try {
            PreparedStatement pstmt = conn.prepareStatement(query.toString());
            pstmt.setInt(1,  physicianID);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int rsPatientID = rs.getInt("patient_id");
                String rsName = rs.getString("name");
                patients.add(new Patient(rsPatientID, rsName));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return patients;
    }
}

