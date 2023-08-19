package com.example.form_login.servlet;

import com.example.form_login.db.JdbcConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name="loginServlet", value = "/do-login")
public class LoginServlet extends HttpServlet {
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        System.out.println("EMAIL");
        System.out.println("email");
        System.out.println("PASSWORD");
        JdbcConnection JdbcConnection = new JdbcConnection();
        PrintWriter out = resp.getWriter();
        resp.setContentType("text/html");
        if (JdbcConnection.getUserByEmailAndPassword(email, password) != null) {
            out.println("Login success");
        } else {
            out.println("Login failed");
        }
    }
}
