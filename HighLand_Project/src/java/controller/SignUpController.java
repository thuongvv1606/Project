/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dal.DAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Staff;

/**
 *
 * @author hp
 */
public class SignUpController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String account = req.getParameter("account");
        String password = req.getParameter("password");
        String repassword = req.getParameter("repassword");
        String name = req.getParameter("name");
        String phone = req.getParameter("phone");
        Boolean gender = Boolean.parseBoolean(req.getParameter("gender"));
        String address = req.getParameter("address");
        String dob = req.getParameter("dob");

        DAO dao = new DAO();
        if (req.getParameter("sign") != null) {
            //Gọi add vào đi
            if (account.isEmpty() || password.isEmpty() || name.isEmpty() || phone.isEmpty() || address.isEmpty()) {
                req.setAttribute("check1", "Information you fill in missing");
                req.getRequestDispatcher("SignUp.jsp").forward(req, resp);
            } else {
                if (repassword.equals(password)) {
                    boolean check = dao.checkDuplicatetStaff(account);
                    if (check) {
                        String ms = "Account already exists!!!!";
                        req.setAttribute("check1", ms);
                        req.getRequestDispatcher("SignUp.jsp").forward(req, resp);
                    } else {
                        dao.addStaff(account, password, name, phone, address, gender, dob);
                        Staff s = dao.getStaff(account);
                        if (s == null) {
                            String ms = "Registration failed!!!";
                            req.setAttribute("check1", ms);
                            req.getRequestDispatcher("SignUp.jsp").forward(req, resp);
                        } else {
                            String ms = "Sign up successfully!";
                            req.setAttribute("check1", ms);
                            req.setAttribute("s1", s);
                            req.getRequestDispatcher("SignUp.jsp").forward(req, resp);
                        }
                    }
                } else {
                    req.setAttribute("check1", "Password and Repassword are not the same!");
                    req.getRequestDispatcher("SignUp.jsp").forward(req, resp);
                }
            }
        }

        if (req.getParameter("home") != null) {
            req.getRequestDispatcher("Login.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("SignUp.jsp").forward(req, resp);
    }

}
