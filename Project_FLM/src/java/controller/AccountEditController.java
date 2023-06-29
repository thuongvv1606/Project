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
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import model.Role;
import model.User;
import util.Constants;

/**
 *
 * @author TT
 */
@WebServlet(name = Constants.URL_ACCOUNTEDIT, urlPatterns = {Constants.URL_ACCOUNTEDIT})
public class AccountEditController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String userId = (String) session.getAttribute("userId");
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
            if (!uDao.checkDuplicateEmail(email, Integer.parseInt(userId))) {
                messFail += "Duplicate Email <br>";
            } 
            if (!uDao.checkDuplicateUsername(username, Integer.parseInt(userId))) {
                messFail += "Duplicate Username <br>";
            }
            if (!uDao.checkDuplicatePhone(phone, Integer.parseInt(userId))) {
                messFail += "Duplicate Phone";
            }
            if (uDao.checkDuplicateEmail(email, Integer.parseInt(userId)) && uDao.checkDuplicateUsername(username, Integer.parseInt(userId))
                    && uDao.checkDuplicatePhone(phone, Integer.parseInt(userId))) {
                User u = new User(Integer.parseInt(userId), fullname, username, email,
                        phone, null, true, title, company, null, null);
                boolean check = uDao.updateUserProfile(u);
                if (role != null) {
                    uDao.deleteUserRole(Integer.parseInt(userId));
                    for (String role1 : role) {
                        uDao.updateUserRole(Integer.parseInt(userId), Integer.parseInt(role1));
                    }
                }
                if (check) {
                    req.setAttribute("messSuccess", "Update successfully!");
                } else {
                    req.setAttribute("mess", "Update failed!");
                }
            }
            req.setAttribute("messFail", messFail);
        }
        req.getRequestDispatcher("/view/admin/accountEdit.jsp").forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String userId = (String) session.getAttribute("userId");
        UserDao uDao = new UserDao();
        String id = req.getParameter("id");
        User acc = uDao.getUser(Integer.parseInt(id));
        req.setAttribute("acc", acc);
        req.getRequestDispatcher("/view/admin/accountEdit.jsp").forward(req, resp);
    }

}
