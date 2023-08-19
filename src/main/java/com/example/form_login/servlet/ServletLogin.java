package com.example.form_login.servlet;

import com.example.form_login.db.UserDAL;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "loginServlet", value = "do-login")
public class ServletLogin extends HttpServlet {
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        System.out.println("EMAIL");
        System.out.println("email");
        System.out.println("PASSWORD");
        UserDAL userDal = new UserDAL();
        PrintWriter out = resp.getWriter();
        resp.setContentType("text/html");
        if (userDal.getUserByEmailAndPassword(email, password) != null) {
            out.println("Login success");
        } else {
            out.println("Login failed");
        }
    }
}
