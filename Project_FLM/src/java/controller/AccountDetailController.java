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
import model.User;
import util.Constants;

/**
 *
 * @author TT
 */
@WebServlet(name = Constants.URL_ACCOUNTDETAIL, urlPatterns = {Constants.URL_ACCOUNTDETAIL})
public class AccountDetailController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/view/admin/accountDetail.jsp").forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String userId = (String) session.getAttribute("userId");
        UserDao uDao = new UserDao();
        User acc = uDao.getUser(Integer.parseInt(userId));
        req.setAttribute("acc", acc);
        req.getRequestDispatcher("/view/admin/accountDetail.jsp").forward(req, resp);
    }
    
}
