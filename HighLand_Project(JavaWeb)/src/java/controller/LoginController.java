/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dal.DAO;
import model.Staff;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import model.Category;
import model.Product;

/**
 *
 * @author hp
 */
public class LoginController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = resp.getWriter()) {
            //Nhận thông tin
            String account = req.getParameter("account");
            String password = req.getParameter("password");
            String r = req.getParameter("rem");
            if (password.isEmpty() || account.isEmpty()) {
                req.setAttribute("mess", "Account or Password Not Empty");
                req.getRequestDispatcher("Login.jsp").forward(req, resp);
            } else {
                DAO dao = new DAO();
                Staff s = dao.login(account, password);
                if (s == null) {
                    req.setAttribute("mess", "Wrong Account or Password");
                    req.getRequestDispatcher("Login.jsp").forward(req, resp);
                } else {
                    //req.getRequestDispatcher("home").forward(req, resp);
                    HttpSession session = req.getSession();
                    session.setAttribute("acc", s);
                    //session.setMaxInactiveInterval(100);
                    Cookie cu = new Cookie("account", account);
                    Cookie cp = new Cookie("password", password);
                    Cookie cr = new Cookie("rem", r);
                    if (r == null) {
                        cu.setMaxAge(0);
                        cp.setMaxAge(0);
                        cr.setMaxAge(0);
                    } else {
                        cu.setMaxAge(60 * 60 * 24);
                        cp.setMaxAge(60 * 60 * 24);
                        cr.setMaxAge(60 * 60 * 24);
                    }
                    resp.addCookie(cr);
                    resp.addCookie(cu);
                    resp.addCookie(cp);
                    resp.sendRedirect("home");
                }
                //}
//            //xử lý
//            Staff s = new Staff(account, password);
//            boolean check = s.checkStaff();
//
//            //Trả kêts quả
//            if (check) {
//                req.getRequestDispatcher("Home.jsp").forward(req, resp);
//            } else {
//                req.getRequestDispatcher("Login.jsp").forward(req, resp);
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("Login.jsp").forward(req, resp);
    }

}
