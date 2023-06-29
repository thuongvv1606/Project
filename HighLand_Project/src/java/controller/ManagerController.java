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
import model.Category;
import model.Product;

/**
 *
 * @author hp
 */
public class ManagerController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
        String indexPage = req.getParameter("index");
        
        if(indexPage == null) {
            indexPage = "1";
        }
        int index = Integer.parseInt(indexPage);
        DAO dao = new DAO();
        int count = dao.getTotalProduct();
        int endPage = count / 20;
        if (count % 3 != 0) {
            endPage++;
        }
        ArrayList<Product> listA = dao.pagingProduct(index);
        req.setAttribute("listA", listA);
        req.setAttribute("count", count);
        req.setAttribute("endp", endPage);
        //DAO dao = new DAO();
        ArrayList<Product> list = dao.getListProduct();
        ArrayList<Category> listC = dao.getListCategory();
        req.setAttribute("listC", listC);
        
        req.setAttribute("listP", list);
        req.getRequestDispatcher("ManagerProduct.jsp").forward(req, resp);

    }

}
