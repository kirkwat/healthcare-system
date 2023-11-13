package com.healthcaresystem;

import java.io.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import com.google.gson.Gson;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Set the response content type to JSON
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // Create an object to hold the response data
        MessageData data = new MessageData();
        data.setMessage(message);

        // Convert the object to JSON and write it to the response
        PrintWriter out = response.getWriter();
        Gson gson = new Gson();
        out.print(gson.toJson(data));
        out.flush();
    }

    public void destroy() {
    }

    // Inner class to represent the message data in a way that can be easily converted to JSON
    static class MessageData {
        private String message;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
