package com.healthcaresystem.controller;

import com.healthcaresystem.model.*;
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
        HttpSession session = request.getSession(false);
        User user = (session != null) ? (User) session.getAttribute("user") : null;

        if (user == null) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "You must be logged in to access this resource.");
            return;
        }

        String userType = user.getType(); // Extract the user type from the session.

        String pathInfo = request.getPathInfo();

        try {
            switch (pathInfo) {
                case "/getLabTestById":
                    handleGetLabTestById(userType, request, response);
                    break;
                case "/getLabTestsForPatient":
                    handleGetLabTestsForPatient(userType, request, response);
                    break;
                case "/getVisitRecordById":
                    handleGetVisitRecordById(userType, request, response);
                    break;
                case "/getVisitRecordsForPatient":
                    handleGetVisitRecordsForPatient(userType, request, response);
                    break;
                case "/getPhysiciansForPatient":
                    handleGetPhysiciansForPatient(userType, request, response);
                    break;
                case "/getPatientsForPhysician":
                    handleGetPatientsForPhysician(userType, request, response);
                    break;
                default:
                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "Unknown action");
            }
        } catch (SecurityException e) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, e.getMessage());
        }
    }

    private void handleGetLabTestById(String userType, HttpServletRequest request, HttpServletResponse response) throws IOException {
        int patientId = Integer.parseInt(request.getParameter("patientId"));
        int testId = Integer.parseInt(request.getParameter("testId"));
        LabTest labTest = clinic.getLabTestById(userType, patientId, testId);
        writeResponse(response, labTest);
    }

    private void handleGetLabTestsForPatient(String userType, HttpServletRequest request, HttpServletResponse response) throws IOException {
        int patientId = Integer.parseInt(request.getParameter("patientId"));
        String testType = request.getParameter("testType");
        String date = request.getParameter("date");
        ArrayList<LabTest> labTests = clinic.getLabTestsForPatient(userType, patientId, testType, date);
        writeResponse(response, labTests);
    }

    private void handleGetVisitRecordById(String userType, HttpServletRequest request, HttpServletResponse response) throws IOException {
        int patientId = Integer.parseInt(request.getParameter("patientId"));
        int visitId = Integer.parseInt(request.getParameter("visitId"));
        VisitRecord visitRecord = clinic.getVisitRecordById(userType, patientId, visitId);
        writeResponse(response, visitRecord);
    }

    private void handleGetVisitRecordsForPatient(String userType, HttpServletRequest request, HttpServletResponse response) throws IOException {
        int patientId = Integer.parseInt(request.getParameter("patientId"));
        ArrayList<VisitRecord> visitRecords = clinic.getVisitRecordsForPatient(userType, patientId);
        writeResponse(response, visitRecords);
    }

    private void handleGetPhysiciansForPatient(String userType, HttpServletRequest request, HttpServletResponse response) throws IOException {
        int patientId = Integer.parseInt(request.getParameter("patientId"));
        ArrayList<Physician> physicians = clinic.getPhysiciansForPatient(userType, patientId);
        writeResponse(response, physicians);
    }

    private void handleGetPatientsForPhysician(String userType, HttpServletRequest request, HttpServletResponse response) throws IOException {
        int physicianId = Integer.parseInt(request.getParameter("physicianId"));
        ArrayList<Patient> patients = clinic.getPatientsForPhysician(userType, physicianId);
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
