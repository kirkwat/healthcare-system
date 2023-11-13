package com.healthcaresystem.controller;

import com.healthcaresystem.model.LabTest;
import com.healthcaresystem.model.Patient;
import com.healthcaresystem.model.Physician;
import com.healthcaresystem.model.VisitRecord;
import com.healthcaresystem.service.Clinic;
import com.google.gson.Gson;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name = "ClinicServlet", value = "/clinic/*")
public class ClinicServlet extends HttpServlet {

    private Gson gson = new Gson();
    private Clinic clinic = new Clinic();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        if (pathInfo == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing action");
            return;
        }

        switch (pathInfo) {
            case "/getLabTestById":
                handleGetLabTestById(request, response);
                break;
            case "/getLabTestsForPatient":
                handleGetLabTestsForPatient(request, response);
                break;
            case "/getVisitRecordById":
                handleGetVisitRecordById(request, response);
                break;
            case "/getVisitRecordsForPatient":
                handleGetVisitRecordsForPatient(request, response);
                break;
            case "/getPhysiciansForPatient":
                handleGetPhysiciansForPatient(request, response);
                break;
            case "/getPatientsForPhysician":
                handleGetPatientsForPhysician(request, response);
                break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Unknown action");
        }
    }

    private void handleGetLabTestById(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int patientId = Integer.parseInt(request.getParameter("patientId"));
        int testId = Integer.parseInt(request.getParameter("testId"));
        LabTest labTest = clinic.getLabTestById(patientId, testId);
        writeResponse(response, labTest);
    }

    private void handleGetLabTestsForPatient(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int patientId = Integer.parseInt(request.getParameter("patientId"));
        String testType = request.getParameter("testType");
        String date = request.getParameter("date");
        ArrayList<LabTest> labTests = clinic.getLabTestsForPatient(patientId, testType, date);
        writeResponse(response, labTests);
    }

    private void handleGetVisitRecordById(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int patientId = Integer.parseInt(request.getParameter("patientId"));
        int visitId = Integer.parseInt(request.getParameter("visitId"));
        VisitRecord visitRecord = clinic.getVisitRecordById(patientId, visitId);
        writeResponse(response, visitRecord);
    }

    private void handleGetVisitRecordsForPatient(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int patientId = Integer.parseInt(request.getParameter("patientId"));
        ArrayList<VisitRecord> visitRecords = clinic.getVisitRecordsForPatient(patientId);
        writeResponse(response, visitRecords);
    }

    private void handleGetPhysiciansForPatient(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int patientId = Integer.parseInt(request.getParameter("patientId"));
        ArrayList<Physician> physicians = clinic.getPhysiciansForPatient(patientId);
        writeResponse(response, physicians);
    }

    private void handleGetPatientsForPhysician(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int physicianId = Integer.parseInt(request.getParameter("physicianId"));
        ArrayList<Patient> patients = clinic.getPatientsForPhysician(physicianId);
        writeResponse(response, patients);
    }

    private void writeResponse(HttpServletResponse response, Object object) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        out.print(gson.toJson(object));
        out.flush();
    }
}
