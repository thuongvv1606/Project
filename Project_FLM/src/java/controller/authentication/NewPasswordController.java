/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.authentication;

import dao.UserDao;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.User;
import util.Constants;

/**
 *
 * @author hp
 */
@WebServlet(name = Constants.URL_NEWPASSWORD, urlPatterns = {Constants.URL_NEWPASSWORD})
public class NewPasswordController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/view/common/newPassword.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         String status = (String) request.getSession().getAttribute("status");
        String email = request.getParameter("email");
        String pass = request.getParameter("pass");
        String repass = request.getParameter("repass");
        if (pass.isEmpty() || repass.isEmpty()) {
            request.setAttribute("messFail", "Account or Password Not Empty");
            request.getRequestDispatcher("/view/common/newPassword.jsp").forward(request, response);
        } else {
            if (!pass.equals(repass)) {
                request.setAttribute("messFail", "Password or Re-Password Not Equal");
                request.getRequestDispatcher("/view/common/newPassword.jsp").forward(request, response);
            } else {
                if(status.equalsIgnoreCase("resetG")){
                UserDao dao = new UserDao();
                User u = dao.getUserByEmail(email);

                int id = u.getUserId();
                dao.resetPassword(id, pass);
                request.setAttribute("messSuccess", "Sucessfully");
                request.getRequestDispatcher("/view/common/newPassword.jsp").forward(request, response);
                } else {
                    UserDao dao = new UserDao();
                    String phone = "0" + email.substring(3, 12);
                User u = dao.getUserByPhone(phone);

                int id = u.getUserId();
                dao.resetPassword(id, pass);
                request.setAttribute("messSuccess", "Sucessfully");
                request.getRequestDispatcher("/view/common/newPassword.jsp").forward(request, response);
                }
            }
        }
    }

}
