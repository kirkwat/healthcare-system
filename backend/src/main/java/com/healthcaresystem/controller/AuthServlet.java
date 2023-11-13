package com.healthcaresystem.controller;

import com.healthcaresystem.model.User;
import com.healthcaresystem.service.Clinic;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "AuthServlet", value = "/auth/*")
public class AuthServlet extends HttpServlet {

    private final Clinic clinic = new Clinic();
    private final Gson gson = new Gson();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();

        switch (pathInfo) {
            case "/login":
                handleLogin(request, response);
                break;
            case "/mfa":
                handleMFA(request, response);
                break;
            case "/logout":
                handleLogout(request, response);
                break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Unknown action");
        }
    }

    private void handleLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = clinic.login(username, password);
        if (user != null) {
            writeResponse(response, new UserLoginResponse(user.getUsername(), user.getName()));
        } else {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().print(gson.toJson("Invalid username or password"));
        }
    }
    private void handleMFA(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        int code = Integer.parseInt(request.getParameter("code"));

        User user = clinic.mfa(username, code);
        if (user != null) {
            request.getSession().setAttribute("user", user);
            writeResponse(response, user);
        } else {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().print(gson.toJson("Invalid MFA code"));
         }
    }

    private void handleLogout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().println("Logged out successfully");
    }

    private void writeResponse(HttpServletResponse response, Object object) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        out.print(gson.toJson(object));
        out.flush();
    }

    private static class UserLoginResponse {
        private final String username;
        private final String name;

        public UserLoginResponse(String username, String name) {
            this.username = username;
            this.name = name;
        }
    }
}
