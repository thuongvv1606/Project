/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.authentication;

import dao.UserDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import model.User;
import util.Constants;
import verify.RandomCode;
import verify.SendEmail;

/**
 *
 * @author hp
 */
@WebServlet(name = Constants.URL_RESETPASSWORDG, urlPatterns = {Constants.URL_RESETPASSWORDG})
public class ResetPasswordGController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        //activate 6-digit code
        RandomCode rc=new RandomCode();
        String verifyCode=rc.activateCode();
        
        if (email.isEmpty() || email == null) {
            req.setAttribute("mess", "not empty");
            req.getRequestDispatcher("/view/common/resetPassword.jsp").forward(req, resp);
        } else {
            UserDao dao = new UserDao();
            User u = dao.getUserByEmail(email);

            if (u == null) {
                req.setAttribute("mess", "Account does not exist");
                req.setAttribute("email", email);
                req.getRequestDispatcher("/view/common/resetPassword.jsp").forward(req, resp);
            } else {
                SendEmail se = new SendEmail();
                se.send(email, verifyCode);
                req.getSession().setAttribute("verifyCode", verifyCode);
                req.getSession().setAttribute("status", "resetG");
                req.getSession().setAttribute("email", email);
                req.getRequestDispatcher("/view/common/resetPassword.jsp").forward(req, resp);
            }
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/view/common/resetPassword.jsp").forward(req, resp);
    }
}
