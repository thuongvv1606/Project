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
import java.time.LocalDate;
import java.util.ArrayList;
import model.Cart;
import model.Item;
import model.ListCart;
import model.Orders;
import model.Product;

/**
 *
 * @author hp
 */
public class DayBillController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        HttpSession session = req.getSession();
        if (session.getAttribute("acc") == null) {
            resp.setContentType("text/html;charset=UTF-8");
            try ( PrintWriter out = resp.getWriter()) {
                out.print("You need to login again");
            }
        }
        String day = req.getParameter("date");
        LocalDate currentDate = LocalDate.now();
        LocalDate otherDate = LocalDate.parse(day);

        if (day.isEmpty()) {
            resp.sendRedirect("ListOrderDay.jsp");
        } else {
            if (otherDate.isAfter(currentDate)) {
                req.setAttribute("mess", "Ngày sau ngày hiện tại rồi");
            } else {
                if (req.getParameter("search") != null) {
                    DAO dao = new DAO();
                    ArrayList<Cart> listCart = new ArrayList<>();
                    ArrayList<Orders> listO = dao.getListOrdersByDay(day);

                    int oIdF = dao.getOrderfirst(day);
                    int oIdE = dao.getOrderlast(day);
                    for (int i = oIdF; i <= oIdE; i++) {
                        listCart.add(dao.getCart(i));
                    }
                    req.setAttribute("day", day);
                    req.setAttribute("listCart", listCart);
                    req.setAttribute("listO", listO);
                }
            }
        }
        req.getRequestDispatcher("ListOrderDay.jsp").forward(req, resp);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        HttpSession session = req.getSession();
        if (session.getAttribute("acc") == null) {
            resp.setContentType("text/html;charset=UTF-8");
            try ( PrintWriter out = resp.getWriter()) {
                out.print("You need to login again");
            }
        }
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
//        DAO dao = new DAO();
//        Cart cart = new Cart();
//        ArrayList<Cart> listCart = new ArrayList<>();
//        ArrayList<Orders> listO = dao.getListOrdersByDay("2023-03-17");
//        // Cart[] listcart = new Cart[listO.size()];
//        int oId;
//        oId = dao.getOrderfirst("2023-03-17");
//        for (int i = oId; i < 11; i++) {
//            listCart.add(dao.getCart(i));
//
////
////            //for (Orders orders : listO) {
////            out.print(oId + "<br>");
////            ArrayList<Product> listP = dao.getListProductByDay(i);
////           
////
////            for (Product product : listP) {
////                Item t = new Item(product, product.getCategoryID(), product.getUnitPrice());
////                cart.addItem(t);
////
////            }
////            listCart.addListCart(cart);
//        }
//        for (Cart cart1 : listCart) {
//            out.print(cart1.getTotalMoney() + "<br>");
//        }
//
//        req.setAttribute("listCart", listCart);
//        req.setAttribute("listO", listO);
//        req.getRequestDispatcher("ListOrderDay.jsp").forward(req, resp);
        //listcart[i] = cart;
        // }
        resp.sendRedirect("ListOrderDay.jsp");
//        String day = "2023-03-17";
//
//        DAO dao = new DAO();
//        ArrayList<Cart> listCart = new ArrayList<>();
//        ArrayList<Orders> listO = dao.getListOrdersByDay(day);
//
//        int oIdF = dao.getOrderfirst(day);
//        int oIdE = dao.getOrderlast(day);
//        out.print(oIdF);
//        out.print(oIdE);
//        out.print(listO.size());
//
//        for (int i = oIdF; i <= oIdE; i++) {
//            listCart.add(dao.getCart(i));
//        }
//        req.setAttribute("listCart", listCart);
//        req.setAttribute("listO", listO);
//        //req.getRequestDispatcher("dayBill").forward(req, resp);
    }

}
