/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dal.DAO;
import dal.OrderDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import model.Cart;
import model.Customers;

/**
 *
 * @author hp
 */
public class CheckOutController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        HttpSession session = req.getSession(true);
//        if (session.getAttribute("acc") == null) {
//            resp.setContentType("text/html;charset=UTF-8");
//            try ( PrintWriter out = resp.getWriter()) {
//                out.print("You need to login again");
//            }
//        }
//        Cart cart = null;
//        Object o = session.getAttribute("cart");
//        if (o != null) {
//            cart = (Cart) o;
//        } else {
//            cart = new Cart();
//        }
////        Customers cutomer = null;
////        Object a = session.getAttribute("customerID");
////        if (a != null) {
//            //cutomer = (Customers) a;
//            OrderDAO odb = new OrderDAO();
//            odb.addOrder(cart);
//            session.removeAttribute("cart");
//            session.setAttribute("size", 0);
//            session.removeAttribute("mang");
//            resp.sendRedirect("home");
//        } else {
//            resp.sendRedirect("home");
//        }
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
        if (o != null) {
            cart = (Cart) o;
            OrderDAO odb = new OrderDAO();
            odb.addOrder(cart);
            session.removeAttribute("cart");
            session.setAttribute("size", 0);
            session.removeAttribute("mang");
            resp.sendRedirect("home");
        } else {
            resp.sendRedirect("home");
        }
//        Customers cutomer = null;
//        Object a = session.getAttribute("customerID");
//        if (a != null) {
        //cutomer = (Customers) a;

//        } else {
//            resp.sendRedirect("home");
//        }ss
    }

}
