/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.authentication;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.*;
import util.*;
import dao.*;

/**
 *
 * @author LanChau
 */
@WebServlet(name = Constants.URL_REGISTER, urlPatterns = {Constants.URL_REGISTER})
public class RegisterController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        request.getRequestDispatcher(Constants.convertURL(Constants.URL_REGISTER, "register")).forward(request, response);
        request.getRequestDispatcher("/view/common/register.jsp").forward(request, response);
//        Constants.getURL(Constants.URL_REGISTER, "view/common/register", request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User user = new User();
        user.setFullName(request.getParameter("FName"));
        user.setUserName(request.getParameter("UName"));
        user.setEmail(request.getParameter("Email"));
        user.setMobile(request.getParameter("Phone"));
        user.setPassword(request.getParameter("Password"));
        UserDao userDAO = new UserDao();

        if (userDAO.checkUserExit(user)) {
            request.setAttribute("user", user);
            request.setAttribute("messFail", "Username or Email exists");
        } else {
            request.setAttribute("messOK", "Sign Up Success");
            int idNewUser = userDAO.registerUser(user);
            if (idNewUser != -1) {
                new RoleDao().addUserRole(idNewUser, 7);
            }

        }
        request.getRequestDispatcher("/view/common/register.jsp").forward(request, response);

//        Constants.getURL(Constants.URL_REGISTER, "register", request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
