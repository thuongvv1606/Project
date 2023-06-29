/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dal.DAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author hp
 */
public class DeleteController extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(true);
        if (session.getAttribute("acc") == null) {
            resp.setContentType("text/html;charset=UTF-8");
            try ( PrintWriter out = resp.getWriter()) {
                out.print("You need to login again");
            }
        }
        resp.setContentType("text/html;charset=UTF-8");
        //HttpSession session = req.getSession();
//        if (session.getAttribute("acc") == null) {
//            resp.setContentType("text/html;charset=UTF-8");
//            try ( PrintWriter out = resp.getWriter()) {
//                out.print("You need to login again");
//            }
//        }
        String productID = req.getParameter("productID");
        DAO dao = new DAO();
        dao.deleteProduct(productID);
        resp.sendRedirect("manager");
    }
    
}
