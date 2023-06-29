/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.RoleDao;
import dao.UserDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import model.Role;
import util.Constants;

/**
 *
 * @author TT
 */
@WebServlet(name = Constants.URL_ACCOUNTADD, urlPatterns = {Constants.URL_ACCOUNTADD})
public class AccountAddController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDao uDao = new UserDao();
        RoleDao rDao = new RoleDao();
        List<Role> rList = rDao.getAll();
        req.setAttribute("rList", rList);
        if (req.getParameter("save") != null) {
            String fullname = req.getParameter("fullname");
            String username = req.getParameter("username");
            String company = req.getParameter("company");
            String[] role = req.getParameterValues("role");
            String title = req.getParameter("title");
            String email = req.getParameter("email");
            String phone = req.getParameter("phone");
            req.setAttribute("fullname", fullname);
            req.setAttribute("username", username);
            req.setAttribute("company", company);
            req.setAttribute("email", email);
            req.setAttribute("phone", phone);
            String messFail = "";
            if (!uDao.checkDuplicateEmail(email, -1)) {
                messFail += "Duplicate Email <br>";
            } 
            if (!uDao.checkDuplicateUsername(username, -1)) {
                messFail += "Duplicate Username <br>";
            }
            if (!uDao.checkDuplicatePhone(phone, -1)) {
                messFail += "Duplicate Phone";
            }
            if (uDao.checkDuplicateEmail(email, -1) && uDao.checkDuplicateUsername(username, -1)
                    && uDao.checkDuplicatePhone(phone, -1)) {
                int id = uDao.addUser(fullname, username, email, phone, title, company);
                if (role == null) {
                    uDao.updateUserRole(id, 7);
                } else {
                    for (String role1 : role) {
                        uDao.updateUserRole(id, Integer.parseInt(role1));
                    }
                }
                req.setAttribute("messSuccess", "Add successfully!");
                req.getRequestDispatcher("/view/admin/accountList.jsp").forward(req, resp);
            }
            req.setAttribute("messFail", messFail);
        }
        req.getRequestDispatcher("/view/admin/accountAdd.jsp").forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/view/admin/accountAdd.jsp").forward(req, resp);
    }

}
