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
import java.io.PrintWriter;
import java.sql.Array;
import java.util.ArrayList;
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

    public void authorization(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        jakarta.servlet.http.HttpSession session = req.getSession();
        if (session.getAttribute("user") == null) {
            resp.setContentType("text/html;charset=UTF-8");
            try ( PrintWriter out = resp.getWriter()) {
                out.print("<!DOCTYPE html>");
                out.print("<html>");
                out.print("<head>");
                out.print("</head>");
                out.print("<body>");
                out.print("<br><br><br><br>");
                
                out.print("<p style=\"text-align: center\"><img style=\"width: 300px;\" src=\"assets/img/error1.jpg\" alt=\"alt\"/> </p>");
                out.print("<h1 style=\"text-align: center\">YOU HAVEN'T SIGN IN!!!</h1>");
                out.print("<h1><a href=\"home\">Home</a></h1>");
                out.print("</body>");
                out.print("</html>");
            }
        }else{
            resp.setContentType("text/html;charset=UTF-8");
            User user = (User) session.getAttribute("user");
            if(user.getRoleFunction() != 2){
                try (PrintWriter out = resp.getWriter()) {
                out.print("<!DOCTYPE html>");
                out.print("<html>");
                out.print("<head>");
                out.print("</head>");
                out.print("<body>");
                out.print("<br><br><br><br>");
                
                out.print("<p style=\"text-align: center\"><img style=\"width: 300px;\" src=\"assets/img/error1.jpg\" alt=\"alt\"/> </p>");
                out.print("<h1 style=\"text-align: center\">YOU DON'T HAVE AUTHORIZATION TO ACCESS THIS SCREEN!!!</h1>");
                out.print("<h1><a href=\"home\">Home</a></h1>");
                out.print("</body>");
                out.print("</html>");
            }
            }
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        authorization(req, resp);
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
                messFail += "Duplicate Phone <br>";
            }
            if (email.trim().equals("")) {
                messFail += "Empty Email <br>";
            }
            if (username.trim().equals("")) {
                messFail += "Empty Username <br>";
            }
            if (phone.trim().equals("")) {
                messFail += "Empty Phone <br>";
            }
            if (uDao.checkDuplicateEmail(email, Integer.parseInt(userId)) && uDao.checkDuplicateUsername(username, Integer.parseInt(userId))
                    && uDao.checkDuplicatePhone(phone, Integer.parseInt(userId)) && !email.trim().equals("") && !username.trim().equals("")
                    && !phone.trim().equals("")) {
                User u = new User(Integer.parseInt(userId), fullname, username, email,
                        phone, null, true, title, company, null, null);
                boolean check = uDao.updateUserProfile(u);
                if (role != null) {
                    uDao.deleteUserRole(Integer.parseInt(userId));
                    for (String role1 : role) {
                        uDao.updateUserRole(Integer.parseInt(userId), Integer.parseInt(role1));
                    }
                }
                req.setAttribute("messSuccess", "Update successfully new account has id = " + Integer.parseInt(userId));
                String indexPage = req.getParameter("index");
                if (indexPage == null) {
                    indexPage = "1";
                }
                int index = Integer.parseInt(indexPage);
                List<User> accList = uDao.getAllUser(index);
                int count = uDao.getNumberOfUser();
                req.setAttribute("count", count);
                int endPage = count / 5;
                if (count % 5 != 0) {
                    endPage++;
                }
                req.setAttribute("accList", accList);
                req.setAttribute("end", endPage);
                req.getRequestDispatcher("/view/admin/accountList.jsp").forward(req, resp);
            } else {
                req.setAttribute("messFail", messFail);
                req.getRequestDispatcher("/view/admin/accountEdit.jsp").forward(req, resp);
            }
        } else {
            req.getRequestDispatcher("/view/admin/accountEdit.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        authorization(req, resp);
        HttpSession session = req.getSession();
        String userId = (String) session.getAttribute("userId");
        UserDao uDao = new UserDao();
        String id = req.getParameter("id");
        User acc = uDao.getUser(Integer.parseInt(id));
        req.setAttribute("acc", acc);
        req.getRequestDispatcher("/view/admin/accountEdit.jsp").forward(req, resp);
    }

}
