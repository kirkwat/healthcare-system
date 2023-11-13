package com.healthcaresystem.controller;

import com.healthcaresystem.model.LabTest;
import com.healthcaresystem.service.Clinic;
import com.google.gson.Gson;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ClinicServlet", value = "/clinic")
public class ClinicServlet extends HttpServlet {

    private Clinic clinicService = new Clinic(); // Make sure Clinic has a no-arg constructor.

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Parse the patientId and testId from request parameters.
        int patientId = Integer.parseInt(request.getParameter("patientId"));
        int testId = Integer.parseInt(request.getParameter("testId"));

        // Use the clinic service to get the LabTest object.
        LabTest labTest = clinicService.getLabTestByID(patientId, testId);

        // Convert the LabTest object to JSON using Gson.
        Gson gson = new Gson();
        String labTestJson = gson.toJson(labTest);

        // Set the response content type to JSON.
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // Write the JSON to the response.
        PrintWriter out = response.getWriter();
        out.print(labTestJson);
        out.flush();
    }

    // You can also implement doPost, doPut, doDelete as needed.
}
