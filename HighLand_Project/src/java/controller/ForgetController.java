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
import java.io.IOException;
import model.Staff;

/**
 *
 * @author hp
 */
public class ForgetController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String account = req.getParameter("account");
        String password = req.getParameter("password");
        String name = req.getParameter("name");
        String phone = req.getParameter("phone");
        Boolean gender = Boolean.parseBoolean(req.getParameter("gender"));
        String address = req.getParameter("address");
        String dob = req.getParameter("dob");
        DAO dao = new DAO();
        if (req.getParameter("forgot") != null) {
            //Gọi add vào đi
            if (account.isEmpty() || name.isEmpty() || phone.isEmpty() || address.isEmpty()) {
                String ms = "Information you fill in missing";
                req.setAttribute("check1", ms);
                req.getRequestDispatcher("ForgetPassword.jsp").forward(req, resp);
            } else {
                Staff s = dao.getStaff(account);
                if (s != null) {
                    String ms = "Password successfully!";
                    req.setAttribute("check1", ms);
                    req.setAttribute("s1", s);
                    req.getRequestDispatcher("ForgetPassword.jsp").forward(req, resp);
                } else {
                    String ms = "PassWord not successfully!";
                    req.setAttribute("check1", ms);
                    req.getRequestDispatcher("ForgetPassword.jsp").forward(req, resp);
                }
            }
        }
        if (req.getParameter("home") != null) {
            req.getRequestDispatcher("Login.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("ForgetPassword.jsp").forward(req, resp);
    }

}
