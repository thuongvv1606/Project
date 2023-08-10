/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import model.Cart;
import model.Staff;

/**
 *
 * @author hp
 */
public class BillController extends HttpServlet{

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
        Cart cart = null;
        Object o = session.getAttribute("cart");
        Staff acc =(Staff) session.getAttribute("acc");
        if (o != null) {
            cart = (Cart) o;
        } else {
            cart = new Cart();
        }
        Staff staff = null;
        if (acc != null) {
            staff =  acc;
        } else {
            staff = new Staff();
        }
        session.setAttribute("cart", cart);
        session.setAttribute("staff", staff);
        req.getRequestDispatcher("Bill.jsp").forward(req, resp);
    }
    
}
