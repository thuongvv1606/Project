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
public class BuyController extends HttpServlet{

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
        if(o!=null) {
            cart = (Cart) o;
        } else {
            cart = new Cart();
        }
        String productID = req.getParameter("productID");
        int num = 1;
        
        try {
            DAO dao = new DAO();
            Product p = dao.getProductByID(productID);
            double price = p.getUnitPrice();
            Item t = new Item(p, num, price);
            cart.addItem(t);
        } catch (NumberFormatException e) {
            System.out.println();
        }
        List<Item> list = cart.getItems();
        session.setAttribute("cart", cart);
        session.setAttribute("size", list.size());
         DAO dao = new DAO();
        if (productID == null) {
            ArrayList<Product> listP = dao.BanChay();
            req.setAttribute("listP", listP);
        } else {
            String categoryID = dao.getCatgoryID(productID);
            ArrayList<Product> listP = dao.getListProductByCID(categoryID);
            req.setAttribute("listP", listP);
        }
        ArrayList<Category> listC = dao.getListCategory();
        req.setAttribute("listC", listC);
        req.getRequestDispatcher("Home.jsp").forward(req, resp);
    }
    
}
