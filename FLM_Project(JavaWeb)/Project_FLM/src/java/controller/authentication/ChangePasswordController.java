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
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import model.User;
import util.BCryptCoder;
import util.Constants;

/**
 *
 * @author trinh
 */
@WebServlet(name = Constants.URL_CHANGEPASS, urlPatterns = {Constants.URL_CHANGEPASS})
public class ChangePasswordController extends HttpServlet {

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
        authorization(req, resp);
        req.getRequestDispatcher("/view/common/changepassword.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        authorization(req, resp);
        if (req.getParameter("change") != null) {
            String oldPass = req.getParameter("oldpass");
            String pass = req.getParameter("Password");
            HttpSession session = req.getSession();
            User user = (User) session.getAttribute("user");
            UserDao userDao = new UserDao();
            String mess = "";
            if (user != null) {
                    String pass1 = userDao.getPassword(user.getUserId());
                    String pass2 = BCryptCoder.encoder(oldPass);
                    if (pass1.equals(pass2)) {
                        if (pass.equals(oldPass)) {
                            mess = "Duplicate pass old and pass new please input again!";
                            session.setAttribute("messFail", mess);
                        } else {

                                    boolean checkPass = userDao.changePassWord(user, pass);
                                    if (checkPass) {
                                        mess = "Change PassWord succes please login again!";
                                        session.setAttribute("messSuccess", mess);
                                        
                                    }
                                
                            }
                        }else{
                        mess = "Wrong old password please input correct";
                        session.setAttribute("messFail", mess);
                    }
                    }else {
                mess = "Please login!!!";
                        session.setAttribute("messFail", mess);
            }
                    req.getRequestDispatcher("/view/common/changepassword.jsp").forward(req, resp);
        }
                }
           } 
        
      


