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

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/view/common/userprofile.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("save") != null) {
            //lay user name tren session ve 
             HttpSession session = req.getSession();
            User user = (User) session.getAttribute("user");
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
            UserDao userDao = new UserDao();
            User u = userDao.getUserById(user);
            req.setAttribute("user", u);
            String mess = "";            
            if (fullName == null || userName == null || email == null || company == null || title == null || mobile == null) {
                mess = "Please input full data ";
                req.setAttribute("mess", mess);
                req.getRequestDispatcher("/view/common/userprofile.jsp").forward(req, resp);
                return;
            } else {
                boolean update = userDao.updateUserProfile(user);
                if (update) {
                    mess = "Update success";
                } else {
                    mess = "Update fall pls update agin";
                }
            }
            req.setAttribute("mess", mess);
            req.getRequestDispatcher("/view/common/userprofile.jsp").forward(req, resp);

        }
    }

}
