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

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();

        if (pathInfo.equals("/session")) {
            handleCheckSession(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Unknown action");
        }
    }

    private void handleLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = clinic.login(username, password);
        if (user != null) {
            ServletUtils.writeResponse(response, new UserLoginResponse(user.getUsername(), user.getName()));
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
            ServletUtils.writeResponse(response, user);
        } else {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().print(gson.toJson("Invalid MFA code"));
         }
    }

    private void handleCheckSession(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("user") != null) {
            User user = (User) session.getAttribute("user");
            ServletUtils.writeResponse(response, user);
        } else {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().print(gson.toJson("No active session"));
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

    private static class UserLoginResponse {
        private final String username;
        private final String name;

        public UserLoginResponse(String username, String name) {
            this.username = username;
            this.name = name;
        }
    }
}
