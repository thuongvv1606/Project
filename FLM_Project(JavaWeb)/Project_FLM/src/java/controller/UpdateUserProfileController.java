/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.UserDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import model.User;
import util.Constants;

/**
 *
 * @author trinh
 */
@WebServlet(name = Constants.URL_UPDATEUSERPROFILE, urlPatterns = {Constants.URL_UPDATEUSERPROFILE})
public class UpdateUserProfileController extends HttpServlet {

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
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        UserDao userDao = new UserDao();
        User u = userDao.getUserById(user);
        req.setAttribute("user", u);
        req.getRequestDispatcher("/view/common/userprofile.jsp").forward(req, resp);
        authorization(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        authorization(req, resp);
        if (req.getParameter("save") != null) {
            //lay user name tren session ve 
            HttpSession session = req.getSession();
            User user = (User) session.getAttribute("user");
            UserDao userDao = new UserDao();
            String fullName = req.getParameter("fullname");
            String userName = req.getParameter("username");
            String email = req.getParameter("email");
            String company = req.getParameter("company");
            String title = req.getParameter("title");
            String mobile = req.getParameter("phone");
            user.setFullName(fullName);
            user.setUserName(userName);
            user.setEmail(email);
            user.setCompany(company);
            user.setTitle(title);
            user.setMobile(mobile);
            //upadate profile 

            String mess = "";
            if (fullName == "" || userName == "" || email == "" || company == "" || title == "" || mobile == "") {
                mess = "Please input full data ";
                session.setAttribute("messFail", mess);
                req.getRequestDispatcher("/view/common/userprofile.jsp").forward(req, resp);
                return;
            } else {
                boolean update = userDao.updateUserProfile(user);
                if (update) {
                    User u = userDao.getUserById(user);
                    req.setAttribute("user", u);
                    mess = "Update success!";
                    session.setAttribute("messSuccess", mess);
                    req.getRequestDispatcher("/view/common/userprofile.jsp").forward(req, resp);
                } else {
                    mess = "Update fall please update agin";
                    session.setAttribute("messFail", mess);
                    req.getRequestDispatcher("/view/common/userprofile.jsp").forward(req, resp);
                }
            }
            

        }
    }

}
