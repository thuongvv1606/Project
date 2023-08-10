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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //authorization(request, response);
        request.getRequestDispatcher("/view/common/newPassword.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //authorization(request, response);
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
                request.setAttribute("messSuccess", "Sucessfully reset password");
                request.getRequestDispatcher("/view/common/login.jsp").forward(request, response);
                } else {
                    UserDao dao = new UserDao();
                    String phone = "0" + email.substring(3, 12);
                User u = dao.getUserByPhone(phone);

                int id = u.getUserId();
                dao.resetPassword(id, pass);
                request.setAttribute("messSuccess", "Sucessfully reset password");
                request.getRequestDispatcher("/view/common/login.jsp").forward(request, response);
                }
            }
        }
    }

}
