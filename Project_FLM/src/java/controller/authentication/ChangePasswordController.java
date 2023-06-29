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

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/view/common/changepassword.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("change") != null) {
            String oldPass = req.getParameter("oldpass");
            String pass = req.getParameter("Password");
            HttpSession session = req.getSession();
            User user = (User) session.getAttribute("user");
//            PrintWriter a = resp.getWriter();
//            a.print(pass);
            UserDao userDao = new UserDao();
            String mess = "";
            if (user != null) {
                    String pass1 = userDao.getPassword(user.getUserId());
                    String pass2 = BCryptCoder.encoder(oldPass);
                    if (pass1.equals(pass2)) {
                        if (pass.equals(oldPass)) {
                            mess = "Duplicate password";
                        } else {

                                    boolean checkPass = userDao.changePassWord(user, pass);
                                    if (checkPass) {
                                        mess = "Suscess";
                                    }
                                
                            }
                        }else{
                        mess = "Wrong old password please input correct";
                    }
                    }
                    req.setAttribute("messs", mess);
                    req.getRequestDispatcher("/view/common/changepassword.jsp").forward(req, resp);
        }
                }
           } 
        
      


