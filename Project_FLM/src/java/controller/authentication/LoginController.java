package controller.authentication;

import dao.UserDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import model.User;
import util.Constants;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author hp
 */
@WebServlet(name = Constants.URL_LOGIN, urlPatterns = {Constants.URL_LOGIN})
public class LoginController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        String userName = req.getParameter("userName");
        String password = req.getParameter("password");
        if (password.isEmpty() || userName.isEmpty()) {
            req.setAttribute("mess", "Account or Password Not Empty");
            req.setAttribute("userName", userName);
            req.setAttribute("password", password);
            req.getRequestDispatcher("/view/common/login.jsp").forward(req, resp);
        } else {
            UserDao userDao = new UserDao();
            User u = userDao.getUser(userName, password);
            if (u == null) {
                req.setAttribute("userName", userName);
                req.setAttribute("password", password);
                req.setAttribute("mess", "Wrong Account or Password");
                req.getRequestDispatcher("/view/common/login.jsp").forward(req, resp);
            } else {
                boolean check = userDao.checkStatus(u);
                if(check == false){
                    req.setAttribute("userName", userName);
                req.setAttribute("password", password);
                req.setAttribute("mess", "Account has been locked");
                req.getRequestDispatcher("/view/common/login.jsp").forward(req, resp);
                }
                HttpSession session = req.getSession();
                session.setAttribute("user", u);
                resp.sendRedirect(Constants.URL_PROJECT + Constants.URL_HOMEPAGE);
            }
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getRequestDispatcher("/view/common/login.jsp").forward(req, resp);
    }

}
