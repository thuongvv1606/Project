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
import java.util.ArrayList;
import java.util.List;
import model.Cart;
import model.Category;
import model.Item;
import model.Product;

/**
 *
 * @author hp
 */
public class ProcessCotroller extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(true);
        if (session.getAttribute("acc") == null) {
            resp.setContentType("text/html;charset=UTF-8");
            try ( PrintWriter out = resp.getWriter()) {
                out.print("You need to login again");
            }
        }
        Cart cart = null;
        Object o = session.getAttribute("cart");
        //co roi
        if (o != null) {
            cart = (Cart) o;
        } else {
            cart = new Cart();
        }
        String id = req.getParameter("id");
        cart.removeItem(id);

        List<Item> list = cart.getItems();

        session.setAttribute("cart", cart);
        session.setAttribute("size", list.size());
        DAO dao = new DAO();
        ArrayList<Product> listP = dao.BanChay();
        ArrayList<Category> listC = dao.getListCategory();
        req.setAttribute("listP", listP);
        req.setAttribute("listC", listC);
        req.getRequestDispatcher("Home.jsp").forward(req, resp);

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
        //HttpSession session = req.getSession(true);
        Cart cart = null;
        Object o = session.getAttribute("cart");
        //co roi
        if (o != null) {
            cart = (Cart) o;
        } else {
            cart = new Cart();
        }
        String id = req.getParameter("id");
        String tnum = req.getParameter("num").trim();
        int num;
        try {
            num = Integer.parseInt(tnum);
            if ((num == -1) && (cart.getQuantityByID(id) <= 1)) {
                cart.removeItem(id);
            } else {
                DAO dao = new DAO();
                Product p = dao.getProductByID(id);
                double price = p.getUnitPrice();
                Item t = new Item(p, num, price);
                cart.addItem(t);
            }
        } catch (NumberFormatException e) {
            System.out.println(e);
        }
        List<Item> list = cart.getItems();

        session.setAttribute("cart", cart);
        session.setAttribute("size", list.size());
        DAO dao = new DAO();
        ArrayList<Product> listP = dao.BanChay();
        ArrayList<Category> listC = dao.getListCategory();
        req.setAttribute("listP", listP);
        req.setAttribute("listC", listC);
        req.getRequestDispatcher("Home.jsp").forward(req, resp);
    }

}
